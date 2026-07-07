package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.DashboardResumenDTO;
import com.servicesnxs.service.administrative.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/dashboard/barberia/{idBarberia}")
    public ResponseEntity<ApiResponse<DashboardResumenDTO>> obtenerResumen(@PathVariable UUID idBarberia) {
        return ResponseEntity.ok(
            ApiResponse.success("RESUMEN OBTENIDO CORRECTAMENTE", service.obtenerResumen(idBarberia))
        );
    }
}