package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.PromocionRequestDTO;
import com.servicesnxs.service.administrative.dto.PromocionResponseDTO;
import com.servicesnxs.service.administrative.service.PromocionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @GetMapping("/promociones/barberia/{idBarberia}")
    public ApiResponse<List<PromocionResponseDTO>> listarPorBarberia(@PathVariable UUID idBarberia) {
        try {
            return ApiResponse.success(
                    "PROMOCIONES OBTENIDAS CORRECTAMENTE",
                    promocionService.listarPorBarberia(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL OBTENER PROMOCIONES");
        }
    }

    @PostMapping("/promociones")
    public ApiResponse<PromocionResponseDTO> crear(@RequestBody PromocionRequestDTO dto) {
        try {
            return ApiResponse.success(
                    "PROMOCIÓN CREADA CORRECTAMENTE",
                    promocionService.crear(dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage() != null ? e.getMessage() : "ERROR AL CREAR PROMOCIÓN");
        }
    }

    @PutMapping("/promociones/{id}")
    public ApiResponse<PromocionResponseDTO> editar(@PathVariable UUID id, @RequestBody PromocionRequestDTO dto) {
        try {
            return ApiResponse.success(
                    "PROMOCIÓN ACTUALIZADA CORRECTAMENTE",
                    promocionService.editar(id, dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(400, e.getMessage() != null ? e.getMessage() : "ERROR AL ACTUALIZAR PROMOCIÓN");
        }
    }

    @DeleteMapping("/promociones/{id}")
    public ApiResponse<Void> eliminar(@PathVariable UUID id) {
        try {
            promocionService.eliminar(id);
            return ApiResponse.success("PROMOCIÓN ELIMINADA CORRECTAMENTE", null);
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL ELIMINAR PROMOCIÓN");
        }
    }
}