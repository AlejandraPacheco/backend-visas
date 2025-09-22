package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaSemanaDto {
    private int idCita;
    private int idSolicitud;
    private LocalDate fechaCita;
    private String horaCita; // HH:mm
    private String nombreCompleto; // Solicitante
}
