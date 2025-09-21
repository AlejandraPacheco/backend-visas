package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitanteDto {
    private int idSolicitante;
    private int idUsuario; // solo ID del usuario
    private String ci;
    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
    private int idCiudad; // solo ID de la ciudad
    private Date fechaNacimiento;
    private boolean status;
}
