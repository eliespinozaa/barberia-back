package com.servicesnxs.service.administrative.dto;

 
import java.time.OffsetDateTime;
import java.util.UUID;
 
public class ResenaResponseDTO {
 
    private UUID          id;
    private UUID          idUsuario;
    private UUID          idBarberia;
    private UUID          idCita;
    private Integer       calificacion;
    private String        comentario;
    private Short         estado;
    private OffsetDateTime createdAt;
 
    private String        nombreUsuario;
 
    public ResenaResponseDTO() {}
 
    public UUID           getId()               { return id; }
    public void           setId(UUID id)        { this.id = id; }
 
    public UUID           getIdUsuario()        { return idUsuario; }
    public void           setIdUsuario(UUID v)  { this.idUsuario = v; }
 
    public UUID           getIdBarberia()       { return idBarberia; }
    public void           setIdBarberia(UUID v) { this.idBarberia = v; }
 
    public UUID           getIdCita()           { return idCita; }
    public void           setIdCita(UUID v)     { this.idCita = v; }
 
    public Integer        getCalificacion()     { return calificacion; }
    public void           setCalificacion(Integer v) { this.calificacion = v; }
 
    public String         getComentario()       { return comentario; }
    public void           setComentario(String v)    { this.comentario = v; }
 
    public Short          getEstado()           { return estado; }
    public void           setEstado(Short v)    { this.estado = v; }
 
    public OffsetDateTime getCreatedAt()                  { return createdAt; }
    public void           setCreatedAt(OffsetDateTime v)  { this.createdAt = v; }
 
    public String         getNombreUsuario()            { return nombreUsuario; }
    public void           setNombreUsuario(String v)    { this.nombreUsuario = v; }
}
