package com.gestionvisas.backend_visas.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginResponseDto {
    private String token;
    private String username;
    private String rol;
    private int idUsuario;
    private Integer idSolicitante; // puede ser null para otros roles

    public LoginResponseDto(String token, String username, String rol, int idUsuario, Integer idSolicitante) {
        this.token = token;
        this.username = username;
        this.rol = rol;
        this.idUsuario = idUsuario;
        this.idSolicitante = idSolicitante;
    }

    // getters y setters
}

