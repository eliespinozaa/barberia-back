package com.servicesnxs.service.administrative.repository;
 
import com.servicesnxs.service.administrative.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.UUID;
 
@Repository
public interface ResenaRepository extends JpaRepository<Resena, UUID> {
 
    // Todas las reseñas activas de una barbería, más recientes primero
    @Query("""
        SELECT r FROM Resena r
        WHERE r.idBarberia = :idBarberia
          AND r.isDeleted  = false
          AND r.estado     = 1
        ORDER BY r.createdAt DESC
        """)
    List<Resena> findByBarberia(@Param("idBarberia") UUID idBarberia);
 
    // Promedio de calificación
    @Query("""
        SELECT AVG(r.calificacion)
        FROM Resena r
        WHERE r.idBarberia = :idBarberia
          AND r.isDeleted  = false
          AND r.estado     = 1
        """)
    Double promedioCalificacion(@Param("idBarberia") UUID idBarberia);
 
    // Total de reseñas
    @Query("""
        SELECT COUNT(r)
        FROM Resena r
        WHERE r.idBarberia = :idBarberia
          AND r.isDeleted  = false
          AND r.estado     = 1
        """)
    Long totalResenas(@Param("idBarberia") UUID idBarberia);
 
    // Cantidad de reseñas por calificación
    @Query("""
        SELECT r.calificacion, COUNT(r)
        FROM Resena r
        WHERE r.idBarberia = :idBarberia
          AND r.isDeleted  = false
          AND r.estado     = 1
        GROUP BY r.calificacion
        ORDER BY r.calificacion DESC
        """)
    List<Object[]> countPorCalificacion(@Param("idBarberia") UUID idBarberia);
}
 
