package com.servicesnxs.service.administrative.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.SuscripcionDTO;
import com.servicesnxs.service.administrative.service.SuscripcionService;

@RestController
public class SuscripcionController {

    private final SuscripcionService suscripcionService;

    public SuscripcionController(SuscripcionService suscripcionService) {
        this.suscripcionService = suscripcionService;
    }

    @GetMapping("/suscripciones/barberia/{idBarberia}")
    public ApiResponse<SuscripcionDTO> obtenerActiva(@PathVariable UUID idBarberia) {
        try {
            return ApiResponse.success(
                    "SUSCRIPCIÓN OBTENIDA CORRECTAMENTE",
                    suscripcionService.obtenerActiva(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    @PatchMapping("/suscripciones/{idSuscripcion}/suspender")
    public ApiResponse<SuscripcionDTO> suspender(@PathVariable UUID idSuscripcion) {
        try {
            return ApiResponse.success(
                    "SUSCRIPCIÓN SUSPENDIDA CORRECTAMENTE",
                    suscripcionService.suspender(idSuscripcion)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

    @PatchMapping("/suscripciones/{idSuscripcion}/reactivar")
    public ApiResponse<SuscripcionDTO> reactivar(@PathVariable UUID idSuscripcion) {
        try {
            return ApiResponse.success(
                    "SUSCRIPCIÓN REACTIVADA CORRECTAMENTE",
                    suscripcionService.reactivar(idSuscripcion)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }
}