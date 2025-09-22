package com.gestionvisas.backend_visas.bl;

import com.gestionvisas.backend_visas.dao.jpa.Cita;
import com.gestionvisas.backend_visas.dao.jpa.Solicitud;
import com.gestionvisas.backend_visas.dao.repository.CitaRepository;
import com.gestionvisas.backend_visas.dao.repository.SolicitudRepository;
import com.gestionvisas.backend_visas.models.CitaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final SolicitudRepository solicitudRepository;

    public List<CitaDto> listarCitasPorSemana(LocalDate fechaReferencia) {
        // Ir al lunes de esa semana
        LocalDate inicio = fechaReferencia.with(java.time.DayOfWeek.MONDAY);
        // Viernes de esa semana
        LocalDate fin = inicio.plusDays(4);

        List<Cita> citas = citaRepository.findByFechaCitaBetween(inicio, fin);

        // Mapear a DTO incluyendo nombreCompleto desde Solicitud
        return citas.stream().map(c -> {
            CitaDto dto = new CitaDto();
            dto.setIdCita(c.getIdCita());
            dto.setIdSolicitud(c.getIdSolicitud().getIdSolicitud());
            dto.setFechaCita(c.getFechaCita());
            dto.setHoraCita(c.getHoraCita().toString().substring(0,5)); // HH:mm
            dto.setNombreCompleto(
                    c.getIdSolicitud().getNombres() + " " + c.getIdSolicitud().getApellidos()
            );
            return dto;
        }).collect(Collectors.toList());
    }

    public Cita crearCita(CitaDto dto) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(dto.getIdSolicitud());
        if (solicitud.isEmpty()) {
            throw new RuntimeException("Solicitud no encontrada con id: " + dto.getIdSolicitud());
        }

        Cita cita = new Cita();
        cita.setFechaCita(dto.getFechaCita());
        cita.setHoraCita(Time.valueOf(dto.getHoraCita() + ":00")); // convierte HH:mm → HH:mm:ss
        cita.setIdSolicitud(solicitud.get());

        return citaRepository.save(cita);
    }
}
