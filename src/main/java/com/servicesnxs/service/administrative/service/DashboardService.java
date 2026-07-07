package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.DashboardResumenDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class DashboardService {

    private final JdbcTemplate jdbcTemplate;

    public DashboardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DashboardResumenDTO obtenerResumen(UUID idBarberia) {

        Long citasHoy = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM nxs_core.nxs_appointments " +
            "WHERE id_barberia = ? AND fecha = CURRENT_DATE AND is_deleted = false",
            Long.class, idBarberia);

        Long citasCanceladas = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM nxs_core.nxs_appointments " +
            "WHERE id_barberia = ? AND fecha = CURRENT_DATE " +
            "AND estado = 'CANCELADA' AND is_deleted = false",
            Long.class, idBarberia);

        BigDecimal ingresos = jdbcTemplate.queryForObject(
            "SELECT COALESCE(SUM(s.precio), 0) " +
            "FROM nxs_core.nxs_appointments a " +
            "JOIN nxs_core.nxs_services s ON s.id = a.id_servicio " +
            "WHERE a.id_barberia = ? AND a.fecha = CURRENT_DATE " +
            "AND a.estado = 'COMPLETADA' AND a.is_deleted = false",
            BigDecimal.class, idBarberia);

        Long promociones = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM nxs_core.nxs_promotions " +
            "WHERE id_barberia = ? AND estado = 1 " +
            "AND fecha_fin >= CURRENT_DATE AND is_deleted = false",
            Long.class, idBarberia);

        Long clientesNuevos = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM nxs_core.nxs_barbershop_customers " +
            "WHERE id_barberia = ? " +
            "AND fecha_registro >= (CURRENT_DATE - INTERVAL '30 days') " +
            "AND is_deleted = false",
            Long.class, idBarberia);

        Double calificacion = jdbcTemplate.queryForObject(
            "SELECT COALESCE(ROUND(AVG(calificacion)::numeric * 2, 1), 0) " +
            "FROM nxs_core.nxs_reviews " +
            "WHERE id_barberia = ? AND estado = 1 AND is_deleted = false",
            Double.class, idBarberia);

        Long resenas = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM nxs_core.nxs_reviews " +
            "WHERE id_barberia = ? AND estado = 1 AND is_deleted = false",
            Long.class, idBarberia);

        Long servicios = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM nxs_core.nxs_services " +
            "WHERE id_barberia = ? AND estado = 1 AND is_deleted = false",
            Long.class, idBarberia);

        return new DashboardResumenDTO(
            citasHoy, citasCanceladas, ingresos, promociones,
            clientesNuevos, calificacion, resenas, servicios
        );
    }
}