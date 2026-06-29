package com.servicesnxs.service.administrative.dto;

import java.time.LocalDate;
import java.util.UUID;

public class SuscripcionDTO {

    private UUID id;

    private UUID idBarberia;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private String estado;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdBarberia() {
        return idBarberia;
    }

    public void setIdBarberia(UUID idBarberia) {
        this.idBarberia = idBarberia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}