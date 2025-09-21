package com.gestionvisas.backend_visas.dao.jpa;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private int idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Solicitante idSolicitante;

    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Timestamp fechaSolicitud;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_pais_de_nacimiento")
    private Pais idPaisDeNacimiento;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @ManyToOne
    @JoinColumn(name = "id_sexo")
    private Sexo idSexo;

    @ManyToOne
    @JoinColumn(name = "id_estadocivil")
    private EstadoCivil idEstadoCivil;

    @Column(name = "ci")
    private String ci;

    @Column(name = "numero_pasaporte")
    private String numeroPasaporte;

    @Column(name = "fecha_expedicion_pasaporte")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionPasaporte;

    @Column(name = "fecha_vencimiento_pasaporte")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoPasaporte;

    @ManyToOne
    @JoinColumn(name = "id_pais_expedicion_pasaporte")
    private Pais idPaisExpedicionPasaporte;

    @Column(name = "profesion")
    private String profesion;

    @ManyToOne
    @JoinColumn(name = "id_motivo")
    private MotivosViaje idMotivo;

    @Column(name = "fecha_llegada_spain")
    @Temporal(TemporalType.DATE)
    private Date fechaLlegadaSpain;

    @Column(name = "fecha_salida_spain")
    @Temporal(TemporalType.DATE)
    private Date fechaSalidaSpain;

    @Column(name = "fotografia")
    @Lob
    private byte[] fotografia;

    @Column(name = "estado")
    private String estado;

    // getters and setters
}
