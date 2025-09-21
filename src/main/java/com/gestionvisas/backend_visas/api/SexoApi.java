package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.dao.jpa.Sexo;
import com.gestionvisas.backend_visas.dao.repository.SexoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sexos")
@CrossOrigin(origins = "http://localhost:4200")
public class SexoApi {

    private final SexoRepository sexoRepository;

    public SexoApi(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    @GetMapping
    public List<Sexo> getSexos() {
        return sexoRepository.findAll();
    }
}

