package com.servicesnxs.service.administrative.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class RegistrarPagoRequest {
    private UUID idSuscripcion;
    private UUID idBarberia;
    private BigDecimal monto;
    private String metodoPago;  // "EFECTIVO", "TRANSFERENCIA", etc.
    private String referencia;

    // Getters y setters
    public UUID getIdSuscripcion() { return idSuscripcion; }
    public void setIdSuscripcion(UUID idSuscripcion) { this.idSuscripcion = idSuscripcion; }

    public UUID getIdBarberia() { return idBarberia; }
    public void setIdBarberia(UUID idBarberia) { this.idBarberia = idBarberia; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
}