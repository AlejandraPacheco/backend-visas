package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.dao.jpa.Pais;
import com.gestionvisas.backend_visas.dao.repository.PaisRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/paises")
@CrossOrigin(origins = "http://localhost:4200")
public class PaisApi {

    private final PaisRepository paisRepository;

    public PaisApi(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @GetMapping
    public List<Pais> getPaises() {
        return paisRepository.findAll();
    }
}
