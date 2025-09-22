package com.gestionvisas.backend_visas.bl;

import com.gestionvisas.backend_visas.dao.jpa.Usuario;
import com.gestionvisas.backend_visas.dao.repository.SolicitanteRepository;
import com.gestionvisas.backend_visas.dao.repository.UsuarioRepository;
import com.gestionvisas.backend_visas.utils.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestionvisas.backend_visas.dao.jpa.Solicitante;


import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SolicitanteRepository solicitanteRepository;

    public AuthService(UsuarioRepository usuarioRepository, SolicitanteRepository solicitanteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.solicitanteRepository = solicitanteRepository;
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

    public Integer getIdSolicitanteByUsuario(int idUsuario) {
        return solicitanteRepository.findByIdUsuario_IdUsuario(idUsuario)
                .map(Solicitante::getIdSolicitante)
                .orElse(null);
    }
}
