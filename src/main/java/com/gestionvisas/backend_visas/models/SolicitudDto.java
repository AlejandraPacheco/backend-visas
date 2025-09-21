package com.gestionvisas.backend_visas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    private int idSolicitud;
    private int idSolicitante;
    private Date fechaSolicitud;
    private String apellidos;
    private String nombres;
    private Date fechaNacimiento;
    private int idPaisDeNacimiento;
    private String nacionalidad;
    private int idSexo;
    private int idEstadoCivil;
    private String ci;
    private String numeroPasaporte;
    private Date fechaExpedicionPasaporte;
    private Date fechaVencimientoPasaporte;
    private int idPaisExpedicionPasaporte;
    private String profesion;
    private int idMotivo;
    private Date fechaLlegadaSpain;
    private Date fechaSalidaSpain;
    private String estado;
    private String fotografiaBase64;
}
