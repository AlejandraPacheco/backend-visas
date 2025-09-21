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
    private String role;

    public LoginResponseDto(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    // getters y setters
}

