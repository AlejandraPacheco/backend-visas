package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.bl.SolicitudBl;
import com.gestionvisas.backend_visas.models.DashboardSolicitanteDto;
import com.gestionvisas.backend_visas.models.DetalleSolicitudDto;
import com.gestionvisas.backend_visas.models.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestionvisas.backend_visas.dao.jpa.Solicitud;
import com.gestionvisas.backend_visas.models.SolicitudDto;

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

    @PutMapping("/funcionario/{id}")
    public ResponseEntity<Solicitud> actualizarSolicitudFuncionario(
            @PathVariable int id,
            @RequestBody SolicitudDto solicitudDto) {
        Solicitud actualizada = solicitudBl.actualizarSolicitud(id, solicitudDto);
        return ResponseEntity.ok(actualizada);
    }


}

