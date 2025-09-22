package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDto {
    private long solicitudesEnRevision;
    private long solicitudesAprobadas;
    private long solicitudesRechazadas;
    private long totalCitasAsignadas;
}
