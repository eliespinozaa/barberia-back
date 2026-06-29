package com.servicesnxs.service.administrative.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class PagoDTO {

    private UUID id;
    private UUID idSuscripcion;
    private UUID idBarberia;
    private String estado;
    private String metodoPago;
    private String referencia;
    private OffsetDateTime fechaPago;
    private java.time.LocalDate fechaInicio;

private Double monto;

public Double getMonto() { return monto; }
public void setMonto(Double monto) { this.monto = monto; }
public java.time.LocalDate getFechaInicio() { return fechaInicio; }
public void setFechaInicio(java.time.LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getIdSuscripcion() {
        return idSuscripcion;
    }
    public void setIdSuscripcion(UUID idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }
    public UUID getIdBarberia() {
        return idBarberia;
    }
    public void setIdBarberia(UUID idBarberia) {
        this.idBarberia = idBarberia;
    }
    
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public OffsetDateTime getFechaPago() {
        return fechaPago;
    }
    public void setFechaPago(OffsetDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}