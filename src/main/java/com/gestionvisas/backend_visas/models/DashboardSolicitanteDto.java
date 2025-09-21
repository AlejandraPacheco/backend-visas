package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSolicitanteDto {
    private int idSolicitud;      // Número de solicitud
    private Date fechaSolicitud;  // Fecha de solicitud
    private String estado;        // Estado de la solicitud
}

