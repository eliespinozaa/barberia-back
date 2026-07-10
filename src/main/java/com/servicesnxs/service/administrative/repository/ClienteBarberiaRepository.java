package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.dto.ClienteBarberiaListItemResponse;
import com.servicesnxs.service.administrative.model.ClienteBarberia;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteBarberiaRepository extends JpaRepository<ClienteBarberia, UUID> {

    boolean existsByClienteIdAndIsDeletedFalse(UUID clienteId);

    Optional<ClienteBarberia> findByClienteIdAndBarberiaId(UUID clienteId, UUID barberiaId);

    List<ClienteBarberia> findByClienteId(UUID clienteId);

    boolean existsByClienteIdAndBarberiaId(UUID clienteId, UUID barberiaId);

    Optional<ClienteBarberia> findTopByClienteIdAndIsDeletedFalseOrderByFechaRegistroDesc(UUID clienteId);

    @Query("""
        SELECT new com.servicesnxs.service.administrative.dto.ClienteBarberiaListItemResponse(
            cb.id,
            cb.clienteId,
            u.nombre,
            u.apellido,
            u.email,
            u.telefono,
            u.fotoPerfil,
            cb.fechaRegistro,
            COUNT(c.id),
            MAX(c.fecha)
        )
        FROM ClienteBarberia cb
        JOIN Usuario u ON u.id = cb.clienteId
        LEFT JOIN Cita c ON c.idUsuario = cb.clienteId
            AND c.idBarberia = cb.barberiaId
            AND c.estado = 'COMPLETADA'
            AND c.isDeleted = false
        WHERE cb.barberiaId = :barberiaId
          AND cb.isDeleted = FALSE
        GROUP BY cb.id, cb.clienteId, u.nombre, u.apellido, u.email, u.telefono, u.fotoPerfil, cb.fechaRegistro
        ORDER BY cb.fechaRegistro DESC
        """)
    List<ClienteBarberiaListItemResponse> listarPorBarberia(@Param("barberiaId") UUID barberiaId);
}