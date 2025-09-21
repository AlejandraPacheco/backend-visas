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
@Table(name = "estadosciviles")
public class EstadoCivil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadocivil")
    private int idEstadoCivil;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "status")
    private boolean status;

    // getters and setters
}
