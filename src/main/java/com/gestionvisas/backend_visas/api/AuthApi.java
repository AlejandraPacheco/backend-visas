package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.bl.AuthService;
import com.gestionvisas.backend_visas.models.LoginRequestDto;
import com.gestionvisas.backend_visas.models.LoginResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // tu Angular
public class AuthApi {

    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        boolean success = authService.login(request.getUsername(), request.getPassword());
        if (success) {
            return new LoginResponseDto(true, "Login exitoso ✅");
        } else {
            return new LoginResponseDto(false, "Usuario o contraseña incorrectos ❌");
        }
    }
}
