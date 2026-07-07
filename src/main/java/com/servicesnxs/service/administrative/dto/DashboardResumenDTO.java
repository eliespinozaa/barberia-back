package com.servicesnxs.service.administrative.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class DashboardResumenDTO {

    private long citasHoy;
    private long citasCanceladas;
    private BigDecimal ingresos;
    private long promociones;
    private long clientesNuevos;
    private Double calificacion;

    @JsonProperty("reseñas")
    private long resenas;

    private long servicios;

    public DashboardResumenDTO(long citasHoy, long citasCanceladas, BigDecimal ingresos,
                                long promociones, long clientesNuevos, Double calificacion,
                                long resenas, long servicios) {
        this.citasHoy = citasHoy;
        this.citasCanceladas = citasCanceladas;
        this.ingresos = ingresos;
        this.promociones = promociones;
        this.clientesNuevos = clientesNuevos;
        this.calificacion = calificacion;
        this.resenas = resenas;
        this.servicios = servicios;
    }

    public long getCitasHoy() { return citasHoy; }
    public long getCitasCanceladas() { return citasCanceladas; }
    public BigDecimal getIngresos() { return ingresos; }
    public long getPromociones() { return promociones; }
    public long getClientesNuevos() { return clientesNuevos; }
    public Double getCalificacion() { return calificacion; }
    public long getResenas() { return resenas; }
    public long getServicios() { return servicios; }
}