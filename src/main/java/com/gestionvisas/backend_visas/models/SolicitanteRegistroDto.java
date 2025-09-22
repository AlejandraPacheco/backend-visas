package com.gestionvisas.backend_visas.models;

import lombok.Data;
import java.util.Date;

@Data
public class SolicitanteRegistroDto {
    private String nombres;
    private String apellidos;
    private String ci;
    private String email;
    private String usuario;
    private String password;
    private Integer ciudadId;
    private String celular;
    private Date fechaNacimiento;
}
