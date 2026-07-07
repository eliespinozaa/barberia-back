package com.servicesnxs.service.administrative.controller;
 
import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.ResenaResumenDTO;
import com.servicesnxs.service.administrative.dto.ResenaResponseDTO;
import com.servicesnxs.service.administrative.service.ResenaService;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.UUID;
 
@RestController
public class ResenaController {
 
    private final ResenaService resenaService;
 
    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }
 
    // Listar todas las reseñas de una barbería
    @GetMapping("/resenas/barberia/{idBarberia}")
    public ApiResponse<List<ResenaResponseDTO>> listarPorBarberia(
            @PathVariable UUID idBarberia) {
        try {
            return ApiResponse.success(
                    "RESEÑAS OBTENIDAS CORRECTAMENTE",
                    resenaService.listarPorBarberia(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }
 
    // Resumen: promedio + total + desglose por estrellas
    @GetMapping("/resenas/barberia/{idBarberia}/resumen")
    public ApiResponse<ResenaResumenDTO> resumen(
            @PathVariable UUID idBarberia) {
        try {
            return ApiResponse.success(
                    "RESUMEN OBTENIDO CORRECTAMENTE",
                    resenaService.resumen(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }
}
 
