package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, UUID> {

    @Query("""
        SELECT p
        FROM Promocion p
        WHERE p.idBarberia = :idBarberia
          AND p.isDeleted = false
        ORDER BY p.fechaInicio DESC
        """)
    List<Promocion> listarPorBarberia(@Param("idBarberia") UUID idBarberia);

    @Query("""
        SELECT p
        FROM Promocion p
        WHERE p.id = :id
          AND p.isDeleted = false
        """)
    Optional<Promocion> findActiveById(UUID id);
}