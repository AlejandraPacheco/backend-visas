package com.gestionvisas.backend_visas.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gestionvisas.backend_visas.models.SolicitanteRegistroDto;
import com.gestionvisas.backend_visas.bl.RegistroSolicitanteService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/solicitantes")
@CrossOrigin(origins = "http://localhost:4200") // tu frontend
public class RegistroSolicitanteApi{

    @Autowired
    private RegistroSolicitanteService registroService;

    @PostMapping
    public ResponseEntity<Map<String, String>> registrarSolicitante(@RequestBody SolicitanteRegistroDto dto) {
        Map<String, String> response = new HashMap<>();
        try {
            registroService.registrarSolicitante(dto);
            response.put("message", "Registro exitoso");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/ci/{ci}")
    public Map<String, Boolean> verificarCi(@PathVariable String ci) {
        boolean exists = registroService.ciExiste(ci);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }


}
