package com.servicesnxs.service.administrative.dto.auth;

import java.util.UUID;

public class AuthResponse {
    private UUID id;
    private String correo;
    private String nombreCompleto;
    private String rol;
    private String mensaje;
    private String token;
    private Boolean clienteAsociado;

    public AuthResponse() {}

    public AuthResponse(
            UUID id,
            String correo,
            String nombreCompleto,
            String rol,
            String mensaje,
            String token,
            Boolean clienteAsociado
    ) {
        this.id = id;
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.mensaje = mensaje;
        this.token = token;
        this.clienteAsociado = clienteAsociado;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Boolean getClienteAsociado() {
        return clienteAsociado;
    }
    public void setClienteAsociado(Boolean clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }
}