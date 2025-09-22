package com.gestionvisas.backend_visas.api;

import com.gestionvisas.backend_visas.dao.jpa.Ciudad;
import com.gestionvisas.backend_visas.dao.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
@CrossOrigin(origins = "http://localhost:4200") // Angular
public class CiudadApi {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public List<Ciudad> listarCiudades() {
        return ciudadRepository.findAll();
    }
}
