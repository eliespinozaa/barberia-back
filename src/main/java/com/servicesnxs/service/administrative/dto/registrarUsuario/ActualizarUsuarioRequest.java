package com.servicesnxs.service.administrative.dto.registrarUsuario;

public class ActualizarUsuarioRequest {

    private String nombre;
    private String correo;
    private String telefono;
    private Integer estado;
private String apellido;

    private String passwordActual;
    private String nuevaPassword;
private String fotoPerfil;

public String getFotoPerfil() { return fotoPerfil; }
public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }

    public String getPasswordActual() { return passwordActual; }
    public void setPasswordActual(String passwordActual) { this.passwordActual = passwordActual; }

    public String getNuevaPassword() { return nuevaPassword; }
    public void setNuevaPassword(String nuevaPassword) { this.nuevaPassword = nuevaPassword; }

    
public String getApellido() { return apellido; }
public void setApellido(String apellido) { this.apellido = apellido; }
}