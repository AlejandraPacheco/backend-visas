package com.gestionvisas.backend_visas.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestionvisas.backend_visas.dao.jpa.Cita;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByFechaCitaBetween(LocalDate inicio, LocalDate fin);
}

