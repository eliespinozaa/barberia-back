package com.servicesnxs.service.administrative.dto;

import java.util.UUID;

public class ResenaCrearRequest {
    private UUID idUsuario;
    private UUID idBarberia;
    private UUID idCita;
    private Integer calificacion;
    private String comentario;

    public UUID getIdUsuario()                 { return idUsuario; }
    public void setIdUsuario(UUID idUsuario)   { this.idUsuario = idUsuario; }

    public UUID getIdBarberia()                { return idBarberia; }
    public void setIdBarberia(UUID idBarberia) { this.idBarberia = idBarberia; }

    public UUID getIdCita()                    { return idCita; }
    public void setIdCita(UUID idCita)         { this.idCita = idCita; }

    public Integer getCalificacion()                  { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }

    public String getComentario()                { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}