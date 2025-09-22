package com.gestionvisas.backend_visas.dao.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestionvisas.backend_visas.dao.jpa.Solicitud;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer>{
    List<Solicitud> findByIdSolicitante_IdSolicitante(int idSolicitante);

    // Contadores para el reporte
    long countByEstadoAndFechaSolicitudBetween(String estado, LocalDate inicio, LocalDate fin);

    long countByFechaSolicitudBetween(LocalDate inicio, LocalDate fin);
}
