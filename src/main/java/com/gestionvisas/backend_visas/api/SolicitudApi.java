package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.bl.SolicitudBl;
import com.gestionvisas.backend_visas.models.DashboardSolicitanteDto;
import com.gestionvisas.backend_visas.models.ResponseDto;
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
}

