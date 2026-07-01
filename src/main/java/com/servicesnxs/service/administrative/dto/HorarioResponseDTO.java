package com.servicesnxs.service.administrative.dto;

import java.util.UUID;
 
public class HorarioResponseDTO {
 
    private UUID   id;
    private UUID   idBarberia;
    private String diaSemana;
    private String horaApertura;
    private String horaCierre;
    private Short  estado;
 
    public HorarioResponseDTO() {}
 
    public HorarioResponseDTO(UUID id, UUID idBarberia, String diaSemana,
                               String horaApertura, String horaCierre, Short estado) {
        this.id           = id;
        this.idBarberia   = idBarberia;
        this.diaSemana    = diaSemana;
        this.horaApertura = horaApertura;
        this.horaCierre   = horaCierre;
        this.estado       = estado;
    }
 
    public UUID   getId()           { return id; }
    public void   setId(UUID id)    { this.id = id; }
 
    public UUID   getIdBarberia()   { return idBarberia; }
    public void   setIdBarberia(UUID idBarberia) { this.idBarberia = idBarberia; }
 
    public String getDiaSemana()    { return diaSemana; }
    public void   setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
 
    public String getHoraApertura() { return horaApertura; }
    public void   setHoraApertura(String horaApertura) { this.horaApertura = horaApertura; }
 
    public String getHoraCierre()   { return horaCierre; }
    public void   setHoraCierre(String horaCierre) { this.horaCierre = horaCierre; }
 
    public Short  getEstado()       { return estado; }
    public void   setEstado(Short estado) { this.estado = estado; }
}
 
