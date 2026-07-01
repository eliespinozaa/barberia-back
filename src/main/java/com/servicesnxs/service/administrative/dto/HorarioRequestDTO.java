package com.servicesnxs.service.administrative.dto;

public class HorarioRequestDTO {
 
    private String idBarberia;  
    private String diaSemana;
    private String horaApertura; 
    private String horaCierre;   
    private Short  estado;
 
    public HorarioRequestDTO() {}
 
    public String getIdBarberia()  { return idBarberia; }
    public void setIdBarberia(String idBarberia) { this.idBarberia = idBarberia; }
 
    public String getDiaSemana()   { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
 
    public String getHoraApertura() { return horaApertura; }
    public void setHoraApertura(String horaApertura) { this.horaApertura = horaApertura; }
 
    public String getHoraCierre()  { return horaCierre; }
    public void setHoraCierre(String horaCierre) { this.horaCierre = horaCierre; }
 
    public Short getEstado()       { return estado; }
    public void setEstado(Short estado) { this.estado = estado; }
}
 
