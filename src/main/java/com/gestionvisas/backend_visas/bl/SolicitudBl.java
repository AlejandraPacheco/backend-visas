package com.gestionvisas.backend_visas.bl;

import com.gestionvisas.backend_visas.dao.jpa.Solicitud;
import com.gestionvisas.backend_visas.dao.repository.SolicitudRepository;
import com.gestionvisas.backend_visas.models.DashboardSolicitanteDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudBl {

    private final SolicitudRepository solicitudRepository;

    public SolicitudBl(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public List<DashboardSolicitanteDto> obtenerSolicitudesPorUsuario(int idSolicitante) {
        List<Solicitud> solicitudes = solicitudRepository.findByIdSolicitante_IdSolicitante(idSolicitante);

        return solicitudes.stream()
                .map(s -> new DashboardSolicitanteDto(
                        s.getIdSolicitud(),
                        s.getFechaSolicitud(),
                        s.getEstado()
                ))
                .collect(Collectors.toList());
    }
}

