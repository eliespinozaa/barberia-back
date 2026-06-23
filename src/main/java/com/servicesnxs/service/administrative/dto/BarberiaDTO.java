package com.servicesnxs.service.administrative.dto;


import java.util.UUID;

public class BarberiaDTO {
    private UUID idBarberia;
    private String nombre;
    private String direccion;
    private String telefono;
    private String imagen;

    public UUID getIdBarberia() {
        return idBarberia;
    }
    public void setIdBarberia(UUID idBarberia) {
        this.idBarberia = idBarberia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}