package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDto {
    private int idCita;
    private int idSolicitud; // Solo enviamos el ID
    private Date fechaCita;
    private String horaCita; // Formato HH:mm
}
