package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.dao.jpa.EstadoCivil;
import com.gestionvisas.backend_visas.dao.repository.EstadoCivilRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estados-civiles")
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoCivilApi {

    private final EstadoCivilRepository estadoCivilRepository;

    public EstadoCivilApi(EstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    @GetMapping
    public List<EstadoCivil> getEstadosCiviles() {
        return estadoCivilRepository.findAll();
    }
}

