package com.gestionvisas.backend_visas.bl;

import com.gestionvisas.backend_visas.dao.repository.CiudadRepository;
import com.gestionvisas.backend_visas.dao.repository.RolRepository;
import com.gestionvisas.backend_visas.dao.repository.SolicitanteRepository;
import com.gestionvisas.backend_visas.dao.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestionvisas.backend_visas.dao.jpa.*;
import com.gestionvisas.backend_visas.models.SolicitanteRegistroDto;

import java.util.Date;

@Service
public class RegistroSolicitanteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registrarSolicitante(SolicitanteRegistroDto dto) throws Exception {
        // Verificar si el CI ya existe
        if (solicitanteRepository.existsByCi(dto.getCi())) {
            throw new Exception("El CI ya está registrado");
        }

        // Crear Usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsuario());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setStatus(true);
        usuario.setIdRol(rolRepository.findByNombreRol("Solicitante"));

        usuario = usuarioRepository.save(usuario); // Guardar y obtener idUsuario

        // Crear Solicitante
        Solicitante solicitante = new Solicitante();
        solicitante.setIdUsuario(usuario);
        solicitante.setCi(dto.getCi());
        solicitante.setNombres(dto.getNombres());
        solicitante.setApellidos(dto.getApellidos());
        solicitante.setEmail(dto.getEmail());
        solicitante.setCelular(dto.getCelular());
        solicitante.setFechaNacimiento(dto.getFechaNacimiento());
        solicitante.setIdCiudad(ciudadRepository.findByIdCiudad(dto.getCiudadId()));
        solicitante.setStatus(true);

        solicitanteRepository.save(solicitante);
    }

    // Método público para verificar CI
    public boolean ciExiste(String ci) {
        return solicitanteRepository.existsByCi(ci);
    }
}
