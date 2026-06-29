package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagoRepository extends JpaRepository<Pago, UUID> {

    @Query("""
        SELECT p
        FROM Pago p
        WHERE p.idBarberia = :idBarberia
        AND p.isDeleted = false
        ORDER BY p.fechaPago DESC
        """)
    List<Pago> findPagosPorBarberia(@Param("idBarberia") UUID idBarberia);

@Query("""
    SELECT p FROM Pago p
    WHERE p.id = :id AND p.isDeleted = false
    """)
Optional<Pago> findByIdActivo(@Param("id") UUID id);

@Query("""
    SELECT p FROM Pago p
    WHERE p.idSuscripcion = :idSuscripcion
    AND p.isDeleted = false
    """)
Optional<Pago> findBySuscripcion(@Param("idSuscripcion") UUID idSuscripcion);
}