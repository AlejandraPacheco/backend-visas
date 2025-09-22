package com.gestionvisas.backend_visas.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    private int idSolicitud;
    private int idSolicitante;
    private LocalDate fechaSolicitud;
    private String apellidos;
    private String nombres;
    private LocalDate fechaNacimiento;
    private Integer idPaisDeNacimiento;
    private String nacionalidad;
    private Integer idSexo;
    private Integer idEstadoCivil;
    private String ci;
    private String numeroPasaporte;
    private LocalDate fechaExpedicionPasaporte;
    private LocalDate fechaVencimientoPasaporte;
    private Integer idPaisExpedicionPasaporte;
    private String profesion;
    private Integer idMotivo;
    private LocalDate fechaLlegadaSpain;
    private LocalDate fechaSalidaSpain;
    private String estado;
    private String fotografiaBase64;
}
