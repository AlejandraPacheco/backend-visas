package com.gestionvisas.backend_visas.dao.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionvisas.backend_visas.dao.jpa.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer>{
}
