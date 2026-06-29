package com.servicesnxs.service.administrative.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "nxs_payments", schema = "nxs_core")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "id_suscripcion")
    private UUID idSuscripcion;

    @Column(name = "id_barberia")
    private UUID idBarberia;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "estado")
    private String estado;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "fecha_pago")
    private OffsetDateTime fechaPago;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

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
    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public OffsetDateTime getDeletedAt() {
        return deletedAt;
    }
    public void setDeletedAt(OffsetDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}