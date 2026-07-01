package com.servicesnxs.service.administrative.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PromocionRequestDTO {
    private String idBarberia;
    private String titulo;
    private String descripcion;
    private BigDecimal descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String imagen;
    private Short estado;

    public PromocionRequestDTO() {}

    public String getIdBarberia() { return idBarberia; }
    public void setIdBarberia(String idBarberia) { this.idBarberia = idBarberia; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getDescuento() { return descuento; }
    public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public Short getEstado() { return estado; }
    public void setEstado(Short estado) { this.estado = estado; }
}