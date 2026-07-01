package com.servicesnxs.service.administrative.repository;
 
import com.servicesnxs.service.administrative.model.HorarioBarberia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
import java.util.UUID;
 
@Repository
public interface HorarioRepository extends JpaRepository<HorarioBarberia, UUID> {
 
    // Lista todos los horarios activos de una barbería, ordenados Lunes→Domingo
    @Query("""
        SELECT h FROM HorarioBarberia h
        WHERE h.barberia.id = :idBarberia
          AND h.isDeleted   = false
        ORDER BY
          CASE h.diaSemana
            WHEN 'Lunes'     THEN 1
            WHEN 'Martes'    THEN 2
            WHEN 'Miercoles' THEN 3
            WHEN 'Jueves'    THEN 4
            WHEN 'Viernes'   THEN 5
            WHEN 'Sabado'    THEN 6
            WHEN 'Domingo'   THEN 7
            ELSE 8
          END
        """)
    List<HorarioBarberia> findByBarberia(@Param("idBarberia") UUID idBarberia);
 
    // Busca el horario de un día específico para una barbería (para evitar duplicados)
    @Query("""
        SELECT h FROM HorarioBarberia h
        WHERE h.barberia.id   = :idBarberia
          AND LOWER(h.diaSemana) = LOWER(:diaSemana)
          AND h.isDeleted     = false
        """)
    Optional<HorarioBarberia> findByBarberiaAndDia(
        @Param("idBarberia") UUID   idBarberia,
        @Param("diaSemana")  String diaSemana
    );
}
 

