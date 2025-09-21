package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.dao.jpa.MotivosViaje;
import com.gestionvisas.backend_visas.dao.repository.MotivosViajeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motivos")
@CrossOrigin(origins = "http://localhost:4200")
public class MotivoApi {

    private final MotivosViajeRepository motivosViajeRepository;

    public MotivoApi(MotivosViajeRepository motivosViajeRepository) {
        this.motivosViajeRepository = motivosViajeRepository;
    }

    @GetMapping
    public List<MotivosViaje> getMotivos() {
        return motivosViajeRepository.findAll();
    }
}

