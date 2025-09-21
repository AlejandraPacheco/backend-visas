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
@Table(name = "solicitantes")
public class Solicitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitante")
    private int idSolicitante;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Column(name = "ci")
    private String ci;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "email")
    private String email;

    @Column(name = "celular")
    private String celular;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad idCiudad;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "status")
    private boolean status;

    // getters and setters
}
