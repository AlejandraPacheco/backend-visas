package com.gestionvisas.backend_visas.dao.jpa;

import jakarta.persistence.*;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private int idCiudad;

    @Column(name = "nombre_ciudad")
    private String nombreCiudad;

    @Column(name = "status")
    private boolean status;

    // getters and setters
}
