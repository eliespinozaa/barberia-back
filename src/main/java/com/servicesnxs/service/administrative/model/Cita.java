package com.servicesnxs.service.administrative.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "nxs_appointments", schema = "nxs_core")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Column(name = "id_barberia")
    private UUID idBarberia;

    @Column(name = "id_barbero")
    private UUID idBarbero;

    @Column(name = "id_servicio")
    private UUID idServicio;

    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    private String estado;
    private String notas;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UUID getIdBarberia() {
        return idBarberia;
    }

    public void setIdBarberia(UUID idBarberia) {
        this.idBarberia = idBarberia;
    }

    public UUID getIdBarbero() {
        return idBarbero;
    }

    public void setIdBarbero(UUID idBarbero) {
        this.idBarbero = idBarbero;
    }

    public UUID getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(UUID idServicio) {
        this.idServicio = idServicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}