package com.gestionvisas.backend_visas.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginResponseDto {
    private boolean success;
    private String message;

    public LoginResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
