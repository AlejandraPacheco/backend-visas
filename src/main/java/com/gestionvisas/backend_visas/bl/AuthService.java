package com.gestionvisas.backend_visas.bl;

import com.gestionvisas.backend_visas.dao.jpa.Usuario;
import com.gestionvisas.backend_visas.dao.repository.UsuarioRepository;
import com.gestionvisas.backend_visas.utils.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Busca un usuario por su username
     */
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Valida que la contraseña ingresada coincida con la almacenada en la BDD
     */
    public boolean validatePassword(Usuario user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    /**
     * Genera un JWT con el username y el nombre del rol
     */
    public String generateToken(String username, String rolNombre) {
        return JwtUtils.generateToken(username, rolNombre);
    }

    /**
     * Método de login que valida usuario y contraseña
     * Retorna true si las credenciales son correctas
     */
    public boolean login(String username, String password) {
        Optional<Usuario> userOpt = findByUsername(username);
        return userOpt.map(user -> validatePassword(user, password)).orElse(false);
    }
}
