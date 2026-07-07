package com.servicesnxs.service.administrative.model;
 
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
 
@Entity
@Table(name = "nxs_reviews", schema = "nxs_core")
public class Resena {
 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
 
    @Column(name = "id_usuario")
    private UUID idUsuario;
 
    @Column(name = "id_barberia")
    private UUID idBarberia;
 
    @Column(name = "id_cita")
    private UUID idCita;
 
    @Column(name = "calificacion")
    private Integer calificacion;
 
    @Column(name = "comentario")
    private String comentario;
 
    @Column(name = "estado")
    private Short estado;
 
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
 
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
 
    @Column(name = "created_by")
    private String createdBy;
 
    @Column(name = "updated_by")
    private String updatedBy;
 
    @Column(name = "is_deleted")
    private Boolean isDeleted;
 
    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
 
    public Resena() {}
 
    public UUID getId()                         { return id; }
    public void setId(UUID id)                  { this.id = id; }
 
    public UUID getIdUsuario()                  { return idUsuario; }
    public void setIdUsuario(UUID idUsuario)    { this.idUsuario = idUsuario; }
 
    public UUID getIdBarberia()                 { return idBarberia; }
    public void setIdBarberia(UUID idBarberia)  { this.idBarberia = idBarberia; }
 
    public UUID getIdCita()                     { return idCita; }
    public void setIdCita(UUID idCita)          { this.idCita = idCita; }
 
    public Integer getCalificacion()                    { return calificacion; }
    public void setCalificacion(Integer calificacion)   { this.calificacion = calificacion; }
 
    public String getComentario()               { return comentario; }
    public void setComentario(String comentario){ this.comentario = comentario; }
 
    public Short getEstado()                    { return estado; }
    public void setEstado(Short estado)         { this.estado = estado; }
 
    public OffsetDateTime getCreatedAt()                        { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt)          { this.createdAt = createdAt; }
 
    public OffsetDateTime getUpdatedAt()                        { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt)          { this.updatedAt = updatedAt; }
 
    public String getCreatedBy()                { return createdBy; }
    public void setCreatedBy(String createdBy)  { this.createdBy = createdBy; }
 
    public String getUpdatedBy()                { return updatedBy; }
    public void setUpdatedBy(String updatedBy)  { this.updatedBy = updatedBy; }
 
    public Boolean getIsDeleted()               { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
 
    public OffsetDateTime getDeletedAt()                    { return deletedAt; }
    public void setDeletedAt(OffsetDateTime deletedAt)      { this.deletedAt = deletedAt; }
}
