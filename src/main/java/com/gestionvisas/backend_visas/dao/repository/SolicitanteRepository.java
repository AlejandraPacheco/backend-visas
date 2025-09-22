package com.gestionvisas.backend_visas.dao.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionvisas.backend_visas.dao.jpa.Solicitante;

import java.util.Optional;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Integer>{
    boolean existsByCi(String ci);

    Optional<Solicitante> findByIdUsuario_IdUsuario(int idUsuario);
}
