package com.gestionvisas.backend_visas.bl;

import com.gestionvisas.backend_visas.dao.jpa.Solicitud;
import com.gestionvisas.backend_visas.dao.repository.SolicitudRepository;
import com.gestionvisas.backend_visas.models.DashboardSolicitanteDto;
import com.gestionvisas.backend_visas.models.DetalleSolicitudDto;
import com.gestionvisas.backend_visas.models.ReporteDto;
import com.gestionvisas.backend_visas.models.SolicitudDto;
import com.gestionvisas.backend_visas.dao.repository.SolicitanteRepository;
import com.gestionvisas.backend_visas.dao.repository.PaisRepository;
import com.gestionvisas.backend_visas.dao.repository.SexoRepository;
import com.gestionvisas.backend_visas.dao.repository.EstadoCivilRepository;
import com.gestionvisas.backend_visas.dao.repository.MotivosViajeRepository;
import com.gestionvisas.backend_visas.dao.repository.CitaRepository;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudBl {

    private final SolicitudRepository solicitudRepository;
    private final SolicitanteRepository solicitanteRepository;
    private final PaisRepository paisRepository;
    private final SexoRepository sexoRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final MotivosViajeRepository motivosViajeRepository;
    private final CitaRepository citaRepository;

    public SolicitudBl(SolicitudRepository solicitudRepository,
                       SolicitanteRepository solicitanteRepository,
                       PaisRepository paisRepository,
                       SexoRepository sexoRepository,
                       EstadoCivilRepository estadoCivilRepository,
                       MotivosViajeRepository motivosViajeRepository,
                       CitaRepository citaRepository) {
        this.solicitudRepository = solicitudRepository;
        this.solicitanteRepository = solicitanteRepository;
        this.paisRepository = paisRepository;
        this.sexoRepository = sexoRepository;
        this.estadoCivilRepository = estadoCivilRepository;
        this.motivosViajeRepository = motivosViajeRepository;
        this.citaRepository = citaRepository;
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

    public Solicitud crearSolicitud(SolicitudDto dto) {

        Solicitud s = new Solicitud();

        // Relaciones ManyToOne
        s.setIdSolicitante(
                solicitanteRepository.findById(dto.getIdSolicitante())
                        .orElseThrow(() -> new RuntimeException("Solicitante no encontrado"))
        );

        s.setIdPaisDeNacimiento(
                paisRepository.findById(dto.getIdPaisDeNacimiento()).orElse(null)
        );

        s.setIdPaisExpedicionPasaporte(
                paisRepository.findById(dto.getIdPaisExpedicionPasaporte()).orElse(null)
        );

        s.setIdSexo(
                sexoRepository.findById(dto.getIdSexo()).orElse(null)
        );

        s.setIdEstadoCivil(
                estadoCivilRepository.findById(dto.getIdEstadoCivil()).orElse(null)
        );

        s.setIdMotivo(
                motivosViajeRepository.findById(dto.getIdMotivo()).orElse(null)
        );

        // Campos simples
        s.setApellidos(dto.getApellidos());
        s.setNombres(dto.getNombres());
        s.setCi(dto.getCi());
        s.setNacionalidad(dto.getNacionalidad());
        s.setNumeroPasaporte(dto.getNumeroPasaporte());
        s.setProfesion(dto.getProfesion());
        s.setFechaNacimiento(dto.getFechaNacimiento());
        s.setFechaExpedicionPasaporte(dto.getFechaExpedicionPasaporte());
        s.setFechaVencimientoPasaporte(dto.getFechaVencimientoPasaporte());
        s.setFechaLlegadaSpain(dto.getFechaLlegadaSpain());
        s.setFechaSalidaSpain(dto.getFechaSalidaSpain());
        s.setFechaSolicitud( LocalDate.now());
        s.setEstado("En revisión");

        // Fotografía (si se envía base64)
        if (dto.getFotografiaBase64() != null) {
            s.setFotografia(Base64.getDecoder().decode(dto.getFotografiaBase64()));
        }

        return solicitudRepository.save(s);
    }

    public DetalleSolicitudDto obtenerDetalleSolicitudPorId(int idSolicitud) {
        Solicitud s = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        DetalleSolicitudDto dto = new DetalleSolicitudDto();
        dto.setIdSolicitud(s.getIdSolicitud());
        dto.setIdSolicitante(s.getIdSolicitante().getIdSolicitante());
        dto.setFechaSolicitud(s.getFechaSolicitud());
        dto.setApellidos(s.getApellidos());
        dto.setNombres(s.getNombres());
        dto.setNacionalidad(s.getNacionalidad());
        dto.setCi(s.getCi());
        dto.setProfesion(s.getProfesion());
        dto.setMotivoDescripcion(s.getIdMotivo() != null ? s.getIdMotivo().getDescripcion() : ""); // <-- aquí
        dto.setFechaLlegadaSpain(s.getFechaLlegadaSpain());
        dto.setFechaSalidaSpain(s.getFechaSalidaSpain());
        dto.setEstado(s.getEstado());

        return dto;
    }

    public List<DetalleSolicitudDto> obtenerTodasSolicitudes() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();

        return solicitudes.stream().map(s -> {
            DetalleSolicitudDto dto = new DetalleSolicitudDto();
            dto.setIdSolicitud(s.getIdSolicitud());
            dto.setIdSolicitante(s.getIdSolicitante().getIdSolicitante());
            dto.setFechaSolicitud(s.getFechaSolicitud());
            dto.setApellidos(s.getApellidos());
            dto.setNombres(s.getNombres());
            dto.setNacionalidad(s.getNacionalidad());
            dto.setCi(s.getCi());
            dto.setProfesion(s.getProfesion());
            dto.setMotivoDescripcion(
                    s.getIdMotivo() != null ? s.getIdMotivo().getDescripcion() : null
            );
            dto.setFechaLlegadaSpain(s.getFechaLlegadaSpain());
            dto.setFechaSalidaSpain(s.getFechaSalidaSpain());
            dto.setEstado(s.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    public SolicitudDto obtenerDetalleSolicitudFuncionario(int idSolicitud) {
        Solicitud s = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        SolicitudDto dto = new SolicitudDto();
        dto.setIdSolicitud(s.getIdSolicitud());
        dto.setIdSolicitante(s.getIdSolicitante().getIdSolicitante());
        dto.setFechaSolicitud(s.getFechaSolicitud());
        dto.setApellidos(s.getApellidos());
        dto.setNombres(s.getNombres());
        dto.setFechaNacimiento(s.getFechaNacimiento());
        dto.setIdPaisDeNacimiento(s.getIdPaisDeNacimiento() != null ? s.getIdPaisDeNacimiento().getIdPais() : null);
        dto.setNacionalidad(s.getNacionalidad());
        dto.setIdSexo(s.getIdSexo() != null ? s.getIdSexo().getIdSexo() : null);
        dto.setIdEstadoCivil(s.getIdEstadoCivil() != null ? s.getIdEstadoCivil().getIdEstadoCivil() : null);
        dto.setCi(s.getCi());
        dto.setNumeroPasaporte(s.getNumeroPasaporte());
        dto.setFechaExpedicionPasaporte(s.getFechaExpedicionPasaporte());
        dto.setFechaVencimientoPasaporte(s.getFechaVencimientoPasaporte());
        dto.setIdPaisExpedicionPasaporte(s.getIdPaisExpedicionPasaporte() != null ? s.getIdPaisExpedicionPasaporte().getIdPais() : null);
        dto.setProfesion(s.getProfesion());
        dto.setIdMotivo(s.getIdMotivo() != null ? s.getIdMotivo().getIdMotivo() : null);
        dto.setFechaLlegadaSpain(s.getFechaLlegadaSpain());
        dto.setFechaSalidaSpain(s.getFechaSalidaSpain());
        dto.setEstado(s.getEstado());

        // Fotografía a Base64
        if (s.getFotografia() != null) {
            dto.setFotografiaBase64(Base64.getEncoder().encodeToString(s.getFotografia()));
        }

        return dto;
    }

    public SolicitudDto obtenerDetalleSolicitudAdministrador(int idSolicitud) {
        Solicitud s = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        SolicitudDto dto = new SolicitudDto();
        dto.setIdSolicitud(s.getIdSolicitud());
        dto.setIdSolicitante(s.getIdSolicitante().getIdSolicitante());
        dto.setFechaSolicitud(s.getFechaSolicitud());
        dto.setApellidos(s.getApellidos());
        dto.setNombres(s.getNombres());
        dto.setFechaNacimiento(s.getFechaNacimiento());
        dto.setIdPaisDeNacimiento(s.getIdPaisDeNacimiento() != null ? s.getIdPaisDeNacimiento().getIdPais() : null);
        dto.setNacionalidad(s.getNacionalidad());
        dto.setIdSexo(s.getIdSexo() != null ? s.getIdSexo().getIdSexo() : null);
        dto.setIdEstadoCivil(s.getIdEstadoCivil() != null ? s.getIdEstadoCivil().getIdEstadoCivil() : null);
        dto.setCi(s.getCi());
        dto.setNumeroPasaporte(s.getNumeroPasaporte());
        dto.setFechaExpedicionPasaporte(s.getFechaExpedicionPasaporte());
        dto.setFechaVencimientoPasaporte(s.getFechaVencimientoPasaporte());
        dto.setIdPaisExpedicionPasaporte(s.getIdPaisExpedicionPasaporte() != null ? s.getIdPaisExpedicionPasaporte().getIdPais() : null);
        dto.setProfesion(s.getProfesion());
        dto.setIdMotivo(s.getIdMotivo() != null ? s.getIdMotivo().getIdMotivo() : null);
        dto.setFechaLlegadaSpain(s.getFechaLlegadaSpain());
        dto.setFechaSalidaSpain(s.getFechaSalidaSpain());
        dto.setEstado(s.getEstado());

        // Fotografía a Base64
        if (s.getFotografia() != null) {
            dto.setFotografiaBase64(Base64.getEncoder().encodeToString(s.getFotografia()));
        }

        return dto;
    }

    public Solicitud actualizarSolicitud(int idSolicitud, SolicitudDto dto) {
        Solicitud s = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // Relaciones ManyToOne
        s.setIdPaisDeNacimiento(
                dto.getIdPaisDeNacimiento() != null ? paisRepository.findById(dto.getIdPaisDeNacimiento()).orElse(null) : null
        );
        s.setIdPaisExpedicionPasaporte(
                dto.getIdPaisExpedicionPasaporte() != null ? paisRepository.findById(dto.getIdPaisExpedicionPasaporte()).orElse(null) : null
        );
        s.setIdSexo(
                dto.getIdSexo() != null ? sexoRepository.findById(dto.getIdSexo()).orElse(null) : null
        );
        s.setIdEstadoCivil(
                dto.getIdEstadoCivil() != null ? estadoCivilRepository.findById(dto.getIdEstadoCivil()).orElse(null) : null
        );
        s.setIdMotivo(
                dto.getIdMotivo() != null ? motivosViajeRepository.findById(dto.getIdMotivo()).orElse(null) : null
        );

        // Campos simples
        s.setApellidos(dto.getApellidos());
        s.setNombres(dto.getNombres());
        s.setCi(dto.getCi());
        s.setNacionalidad(dto.getNacionalidad());
        s.setNumeroPasaporte(dto.getNumeroPasaporte());
        s.setProfesion(dto.getProfesion());
        s.setFechaNacimiento(dto.getFechaNacimiento());
        s.setFechaExpedicionPasaporte(dto.getFechaExpedicionPasaporte());
        s.setFechaVencimientoPasaporte(dto.getFechaVencimientoPasaporte());
        s.setFechaLlegadaSpain(dto.getFechaLlegadaSpain());
        s.setFechaSalidaSpain(dto.getFechaSalidaSpain());
        s.setEstado(dto.getEstado());

        // Fotografía (Base64 a byte[])
        if (dto.getFotografiaBase64() != null) {
            String base64Image = dto.getFotografiaBase64();
            if (base64Image.contains(",")) {
                base64Image = base64Image.split(",")[1]; // tomamos solo la parte después de la coma
            }
            s.setFotografia(Base64.getDecoder().decode(base64Image));
        }


        return solicitudRepository.save(s);
    }

    public Solicitud obtenerSolicitudPorId(int idSolicitud) {
        return solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    /**
     * Retorna el reporte para un mes y año (ej: mes = 9, anio = 2025).
     */
    public ReporteDto obtenerReportePorMes(int mes, int anio) {
        LocalDate inicio = LocalDate.of(anio, mes, 1);
        LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());

        long enRevision = solicitudRepository.countByEstadoAndFechaSolicitudBetween("En revisión", inicio, fin);
        long aprobadas = solicitudRepository.countByEstadoAndFechaSolicitudBetween("Aprobada", inicio, fin);
        long rechazadas = solicitudRepository.countByEstadoAndFechaSolicitudBetween("Rechazada", inicio, fin);
        long totalCitas = citaRepository.countByFechaCitaBetween(inicio, fin);

        return new ReporteDto(enRevision, aprobadas, rechazadas, totalCitas);
    }

}

