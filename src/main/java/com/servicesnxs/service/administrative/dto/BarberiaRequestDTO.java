package com.servicesnxs.service.administrative.dto;


public class BarberiaRequestDTO {
    private String nombre;
    private String direccion;
    private String telefono;
    private String imagen;
    private Short estado;
    private String idUsuario; // UUID en string

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public Short getEstado() { return estado; }
    public void setEstado(Short estado) { this.estado = estado; }
    
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
}