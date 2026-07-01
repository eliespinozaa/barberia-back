package com.servicesnxs.service.administrative.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class CitaHistorialResponse {
    private UUID id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String servicioNombre;
    private String barberoNombre;
    private String estado;
    private String notas;

    public CitaHistorialResponse() {}

    public CitaHistorialResponse(UUID id, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
                                  String servicioNombre, String barberoNombre, String estado, String notas) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.servicioNombre = servicioNombre;
        this.barberoNombre = barberoNombre;
        this.estado = estado;
        this.notas = notas;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public String getServicioNombre() { return servicioNombre; }
    public void setServicioNombre(String servicioNombre) { this.servicioNombre = servicioNombre; }
    public String getBarberoNombre() { return barberoNombre; }
    public void setBarberoNombre(String barberoNombre) { this.barberoNombre = barberoNombre; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}