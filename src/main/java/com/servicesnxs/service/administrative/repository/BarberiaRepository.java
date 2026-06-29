package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.Barberia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarberiaRepository extends JpaRepository<Barberia, UUID> {
  @Query("""
    SELECT DISTINCT b
    FROM Barberia b
    LEFT JOIN FETCH b.horarios h
    WHERE b.estado = :estado
      AND b.isDeleted = false
    ORDER BY b.createdAt ASC
""")
List<Barberia> findByEstadoConHorarios(Short estado);

@Query("""
    SELECT b
    FROM Barberia b
    WHERE b.idBarberia = :id
      AND b.isDeleted = false
""")
Optional<Barberia> findActiveById(UUID id);

    List<Barberia> findByIsDeletedFalse();

}