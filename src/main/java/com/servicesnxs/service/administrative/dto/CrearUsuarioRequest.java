package com.servicesnxs.service.administrative.dto;

import java.util.UUID;

public class CrearUsuarioRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String telefono;
    private String fotoPerfil;
    private String rol;          // "SUPER_ADMIN" | "DUENO" | "CLIENTE" | "BARBERO"
    private Integer estado;
    private UUID idBarberia;     // solo requerido si rol == "BARBERO"

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
    public UUID getIdBarberia() { return idBarberia; }
    public void setIdBarberia(UUID idBarberia) { this.idBarberia = idBarberia; }
}