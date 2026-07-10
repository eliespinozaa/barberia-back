package com.servicesnxs.service.administrative.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

public class CitaDiaResponse {

    private UUID id;
    private UUID idBarbero; 
    private LocalTime hora;
    private LocalTime horaFin;
    private String nombreCliente;
    private String servicio;
    private BigDecimal precio;
    private String estado;

    public CitaDiaResponse() {
    }

    public CitaDiaResponse(
            UUID id,
            LocalTime hora,
            LocalTime horaFin,
            String nombreCliente,
            String servicio,
            BigDecimal precio,
            String estado) {
        this.id = id;
        this.hora = hora;
        this.horaFin = horaFin;
        this.nombreCliente = nombreCliente;
        this.servicio = servicio;
        this.precio = precio;
        this.estado = estado;
    }

    public CitaDiaResponse(
            UUID id,
            UUID idBarbero,
            LocalTime hora,
            LocalTime horaFin,
            String nombreCliente,
            String servicio,
            BigDecimal precio,
            String estado) {
        this.id = id;
        this.idBarbero = idBarbero;
        this.hora = hora;
        this.horaFin = horaFin;
        this.nombreCliente = nombreCliente;
        this.servicio = servicio;
        this.precio = precio;
        this.estado = estado;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getIdBarbero() { return idBarbero; }
    public void setIdBarbero(UUID idBarbero) { this.idBarbero = idBarbero; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}