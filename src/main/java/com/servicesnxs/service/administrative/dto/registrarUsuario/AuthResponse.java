package com.servicesnxs.service.administrative.dto.registrarUsuario;

import java.util.UUID;

public class AuthResponse {

    private UUID id;
    private String correo;
    private String nombre;
    private String apellido;
    private String rol;
    private String telefono;
    private Integer estado;

    public AuthResponse() {}

    public AuthResponse(UUID id, String correo, String nombre, String apellido,
                        String rol, String telefono, Integer estado) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.telefono = telefono;
        this.estado = estado;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
}