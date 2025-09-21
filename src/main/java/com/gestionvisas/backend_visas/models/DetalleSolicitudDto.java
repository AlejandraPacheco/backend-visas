package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleSolicitudDto {
    private int idSolicitud;
    private int idSolicitante;
    private LocalDate fechaSolicitud;
    private String apellidos;
    private String nombres;
    private String nacionalidad;
    private String ci;
    private String profesion;
    private int idMotivo;
    private LocalDate fechaLlegadaSpain;
    private LocalDate fechaSalidaSpain;
    private String estado;
}
