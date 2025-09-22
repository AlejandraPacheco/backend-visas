package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.bl.SolicitudBl;
import com.gestionvisas.backend_visas.models.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestionvisas.backend_visas.dao.jpa.Solicitud;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudApi {

    private final SolicitudBl solicitudBl;

    public SolicitudApi(SolicitudBl solicitudBl) {
        this.solicitudBl = solicitudBl;
    }

    @GetMapping("/usuario/{id}")
    public List<DashboardSolicitanteDto> getSolicitudes(@PathVariable int id) {
        return solicitudBl.obtenerSolicitudesPorUsuario(id);
    }

    @PostMapping("/")
    public ResponseEntity<Solicitud> crearSolicitud(@RequestBody SolicitudDto solicitudDto) {
        Solicitud nuevaSolicitud = solicitudBl.crearSolicitud(solicitudDto);
        return ResponseEntity.ok(nuevaSolicitud);
    }

    @GetMapping("/{id}")
    public DetalleSolicitudDto getDetalleSolicitud(@PathVariable int id) {
        return solicitudBl.obtenerDetalleSolicitudPorId(id);
    }

    @GetMapping("/")
    public List<DetalleSolicitudDto> getTodasSolicitudes() {
        return solicitudBl.obtenerTodasSolicitudes();
    }

    @GetMapping("/funcionario/{id}")
    public SolicitudDto getDetalleSolicitudFuncionario(@PathVariable int id) {
        return solicitudBl.obtenerDetalleSolicitudFuncionario(id);
    }

    @GetMapping("/administrador/{id}")
    public SolicitudDto getDetalleSolicitudAdministrador(@PathVariable int id) {
        return solicitudBl.obtenerDetalleSolicitudAdministrador(id);
    }

    @PutMapping("/funcionario/{id}")
    public ResponseEntity<Solicitud> actualizarSolicitudFuncionario(
            @PathVariable int id,
            @RequestBody SolicitudDto solicitudDto) {
        Solicitud actualizada = solicitudBl.actualizarSolicitud(id, solicitudDto);
        return ResponseEntity.ok(actualizada);
    }

    @PutMapping("/administrador/{id}")
    public ResponseEntity<Solicitud> actualizarSolicitudAdministrador(
            @PathVariable int id,
            @RequestBody SolicitudDto solicitudDto) {
        Solicitud actualizada = solicitudBl.actualizarSolicitud(id, solicitudDto);
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/foto/{idSolicitud}")
    public ResponseEntity<byte[]> getFotoSolicitud(@PathVariable int idSolicitud) {
        Solicitud solicitud = solicitudBl.obtenerSolicitudPorId(idSolicitud);

        if (solicitud.getFotografia() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // o image/png según tu caso
                .body(solicitud.getFotografia());
    }

    // Reporte por mes
    @GetMapping("/reporte")
    public ReporteDto getReportePorMes(@RequestParam("mes") int mes, @RequestParam("anio") int anio) {
        return solicitudBl.obtenerReportePorMes(mes, anio);
    }

}

