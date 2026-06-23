package com.servicesnxs.service.administrative.controller;


import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.BarberiaRequestDTO;
import com.servicesnxs.service.administrative.dto.BarberiaResponseDTO;
import com.servicesnxs.service.administrative.model.Barberia;
import com.servicesnxs.service.administrative.service.BarberiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BarberiaController {
    private final BarberiaService barberiaService;

    public BarberiaController(BarberiaService barberiaService) {
        this.barberiaService = barberiaService;
    }

    @GetMapping("/barberia/barberias")
    public ApiResponse<List<Barberia>> obtenerBarberias() {
        try {
            List<Barberia> barberias = barberiaService.obtenerBarberiasActivas();
            return ApiResponse.success(
                    "BARBERIAS OBTENIDAS CORRECTAMENTE",
                    barberias);
        } catch (Exception e) {
            return ApiResponse.error(
                    500,
                    "ERROR AL OBTENER BARBERIAS");
        }
    }

    
    @GetMapping("/listar/barberias")
    public ApiResponse<List<BarberiaResponseDTO>> listar() {
        try {
            return ApiResponse.success(
                    "BARBERIAS OBTENIDAS CORRECTAMENTE",
                    barberiaService.listar()
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL OBTENER BARBERIAS");
        }
    }

    @PostMapping("/barberia/barberias")
    public ApiResponse<BarberiaResponseDTO> crearBarberia(
            @RequestBody BarberiaRequestDTO dto
    ) {
        try {
            return ApiResponse.success(
                    "BARBERÍA CREADA CORRECTAMENTE",
                    barberiaService.crear(dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL CREAR BARBERÍA");
        }
    }

    @PutMapping("/barberia/barberias/{id}")
    public ApiResponse<BarberiaResponseDTO> editarBarberia(
            @PathVariable UUID id,
            @RequestBody BarberiaRequestDTO dto
    ) {
        try {
            return ApiResponse.success(
                    "BARBERÍA ACTUALIZADA CORRECTAMENTE",
                    barberiaService.editar(id, dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL EDITAR BARBERÍA");
        }
    }
}