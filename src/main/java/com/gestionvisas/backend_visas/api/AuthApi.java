package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.bl.AuthService;
import com.gestionvisas.backend_visas.models.LoginRequestDto;
import com.gestionvisas.backend_visas.models.LoginResponseDto;
import com.gestionvisas.backend_visas.models.ResponseDto;
import com.gestionvisas.backend_visas.dao.jpa.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // tu Angular
public class AuthApi {

    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        Optional<Usuario> userOpt = authService.findByUsername(request.getUsername());
        if (userOpt.isPresent() && authService.login(request.getUsername(), request.getPassword())) {
            Usuario user = userOpt.get();
            String token = authService.generateToken(user.getUsername(), user.getIdRol().getNombreRol());
            return new ResponseDto<>("200", new LoginResponseDto(token, user.getUsername(), user.getIdRol().getNombreRol()), null);
        } else {
            return new ResponseDto<>("401", null, "Usuario o contraseña incorrectos");
        }
    }

}
