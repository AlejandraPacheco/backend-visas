package com.gestionvisas.backend_visas.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionvisas.backend_visas.dao.jpa.Cita;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByFechaCitaBetween(LocalDate inicio, LocalDate fin);

    // Contador de citas en rango
    long countByFechaCitaBetween(LocalDate inicio, LocalDate fin);
}

