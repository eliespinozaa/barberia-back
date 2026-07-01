package com.servicesnxs.service.administrative.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ClienteBarberiaListItemResponse {

    private UUID idRegistro;      
    private UUID idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String fotoPerfil;
    private Long citas;
private LocalDate ultimaVisita;

    private OffsetDateTime fechaRegistro;

    public ClienteBarberiaListItemResponse() {}

 public ClienteBarberiaListItemResponse(
        UUID idRegistro,
        UUID idCliente,
        String nombre,
        String apellido,
        String email,
        String telefono,
        String fotoPerfil,
        OffsetDateTime fechaRegistro,
        Long citas,
        LocalDate ultimaVisita
) {
    this.idRegistro = idRegistro;
    this.idCliente = idCliente;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.telefono = telefono;
    this.fotoPerfil = fotoPerfil;
    this.fechaRegistro = fechaRegistro;
    this.citas = citas;
    this.ultimaVisita = ultimaVisita;
}

    public UUID getIdRegistro() { return idRegistro; }
    public void setIdRegistro(UUID idRegistro) { this.idRegistro = idRegistro; }

    public UUID getIdCliente() { return idCliente; }
    public void setIdCliente(UUID idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public OffsetDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(OffsetDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public Long getCitas() {
    return citas;
}

public void setCitas(Long citas) {
    this.citas = citas;
}

public LocalDate getUltimaVisita() {
    return ultimaVisita;
}

public void setUltimaVisita(LocalDate ultimaVisita) {
    this.ultimaVisita = ultimaVisita;
}
}