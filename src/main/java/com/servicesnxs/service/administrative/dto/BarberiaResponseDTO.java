package com.servicesnxs.service.administrative.dto;

import java.util.UUID;

public class BarberiaResponseDTO {
    private UUID id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String imagen;
    private Short estado;

    private UUID idUsuario;

    public BarberiaResponseDTO() {}

    public BarberiaResponseDTO(UUID id, String nombre, String direccion, String telefono, String imagen,Short estado, UUID idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.imagen = imagen;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

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

    public UUID getIdUsuario() { return idUsuario; }
    public void setIdUsuario(UUID idUsuario) { this.idUsuario = idUsuario; }
}