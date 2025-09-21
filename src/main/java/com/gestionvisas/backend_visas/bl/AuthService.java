package com.gestionvisas.backend_visas.bl;

import com.gestionvisas.backend_visas.dao.jpa.Usuario;
import com.gestionvisas.backend_visas.dao.repository.UsuarioRepository;
import com.gestionvisas.backend_visas.models.LoginRequestDto;
import com.gestionvisas.backend_visas.models.LoginResponseDto;
import com.gestionvisas.backend_visas.utils.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean login(String username, String password) {
        Optional<Usuario> userOpt = usuarioRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            // Compara la contraseña ingresada con la encriptada
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}
