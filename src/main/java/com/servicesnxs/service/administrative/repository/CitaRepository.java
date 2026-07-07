package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.dto.CitaDiaResponse;
import com.servicesnxs.service.administrative.dto.CitaHistorialResponse;
import com.servicesnxs.service.administrative.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CitaRepository extends JpaRepository<Cita, UUID> {

    @Query("""
        SELECT new com.servicesnxs.service.administrative.dto.CitaHistorialResponse(
            c.id,
            c.fecha,
            c.horaInicio,
            c.horaFin,
            s.nombre,
            u.nombre,
            c.estado,
            c.notas
        )
        FROM Cita c
        JOIN Servicio s ON s.id = c.idServicio
        JOIN Barbero b ON b.id = c.idBarbero
        JOIN Usuario u ON u.id = b.idUsuario
        WHERE c.idUsuario = :clienteId
          AND c.idBarberia = :barberiaId
          AND c.isDeleted = false
        ORDER BY c.fecha DESC, c.horaInicio DESC
        """)
    List<CitaHistorialResponse> historialPorClienteYBarberia(
            @Param("clienteId") UUID clienteId,
            @Param("barberiaId") UUID barberiaId
    );


    @Query("""
    SELECT new com.servicesnxs.service.administrative.dto.CitaDiaResponse(
        c.id,
        c.horaInicio,
        c.horaFin,
        u.nombre,
        s.nombre,
        s.precio,
        c.estado
    )
    FROM Cita c
    JOIN Servicio s ON s.id = c.idServicio
    JOIN Usuario u ON u.id = c.idUsuario
    WHERE c.idBarberia = :barberiaId
      AND c.fecha = :fecha
      AND c.isDeleted = false
    ORDER BY c.horaInicio ASC
    """)
List<CitaDiaResponse> listarPorBarberiaYFecha(
        @Param("barberiaId") UUID barberiaId,
        @Param("fecha") LocalDate fecha
);
}