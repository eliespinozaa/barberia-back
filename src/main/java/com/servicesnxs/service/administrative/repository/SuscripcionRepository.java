package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, UUID> {

        Optional<Suscripcion> findFirstByIdBarberiaAndIsDeletedFalseOrderByFechaFinDesc(
                        UUID idBarberia);

@Query("""
SELECT s
FROM Suscripcion s
WHERE s.idBarberia = :idBarberia
AND s.isDeleted = false
AND s.fechaInicio <= CURRENT_DATE
AND s.fechaFin >= CURRENT_DATE
""")
Optional<Suscripcion> findSuscripcionActiva(@Param("idBarberia") UUID idBarberia);

// Por esto:
@Query("""
    SELECT s
    FROM Suscripcion s
    WHERE s.idBarberia = :idBarberia
    AND s.isDeleted = false
    ORDER BY s.fechaFin DESC
    """)
List<Suscripcion> findUltimaSuscripcion(@Param("idBarberia") UUID idBarberia);
}