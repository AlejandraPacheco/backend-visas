package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private int idUsuario;
    private int idRol; // solo ID del rol
    private String username;
    // No se envia el password al frontend
    private boolean status;
}
