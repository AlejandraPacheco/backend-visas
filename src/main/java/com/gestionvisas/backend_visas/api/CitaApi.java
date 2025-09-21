package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.dao.jpa.Cita;
import com.gestionvisas.backend_visas.models.CitaDto;
import com.gestionvisas.backend_visas.bl.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Angular
public class CitaApi {

    private final CitaService citaService;

    @GetMapping("/semana")
    public List<Cita> listarCitasSemana(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return citaService.listarCitasPorSemana(fecha);
    }



    @PostMapping
    public Cita crearCita(@RequestBody CitaDto dto) {
        return citaService.crearCita(dto);
    }
}
