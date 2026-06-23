package com.servicesnxs.service.administrative.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class UsuarioDTO {

    private UUID id;

    private String nombre;
    private String apellido;

    private String email;
    private String telefono;

    private String fotoPerfil;

    private String rol;

    private Integer estado;

    private OffsetDateTime createdAt;

    // ─────────────────────────────
    // GETTERS
    // ─────────────────────────────

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public String getRol() {
        return rol;
    }

    public Integer getEstado() {
        return estado;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    // ─────────────────────────────
    // SETTERS
    // ─────────────────────────────

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}