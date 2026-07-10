package com.servicesnxs.service.administrative.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class CitaCrearRequest {
    private UUID idCliente;
    private UUID idBarbero;
    private UUID idBarberia;
    private UUID idServicio;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private Integer duracionMinutos;
private String notas;

public String getNotas() { return notas; }
public void setNotas(String notas) { this.notas = notas; }
    public UUID getIdCliente() { return idCliente; }
    public void setIdCliente(UUID idCliente) { this.idCliente = idCliente; }
    public UUID getIdBarbero() { return idBarbero; }
    public void setIdBarbero(UUID idBarbero) { this.idBarbero = idBarbero; }
    public UUID getIdBarberia() { return idBarberia; }
    public void setIdBarberia(UUID idBarberia) { this.idBarberia = idBarberia; }
    public UUID getIdServicio() { return idServicio; }
    public void setIdServicio(UUID idServicio) { this.idServicio = idServicio; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public Integer getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }
}