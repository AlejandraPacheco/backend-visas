package com.gestionvisas.backend_visas.dao.jpa;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int idCita;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    private Solicitud idSolicitud;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fechaCita;

    @Column(name = "hora")
    private Time horaCita;

    // getters and setters
}
