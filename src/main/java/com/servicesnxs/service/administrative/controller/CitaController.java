package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.CitaHistorialResponse;
import com.servicesnxs.service.administrative.service.CitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping("/citas/historial")
    public ApiResponse<List<CitaHistorialResponse>> historial(
            @RequestParam UUID clienteId,
            @RequestParam UUID barberiaId
    ) {
        try {
            return ApiResponse.success(
                    "HISTORIAL OBTENIDO CORRECTAMENTE",
                    citaService.historial(clienteId, barberiaId)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL OBTENER HISTORIAL");
        }
    }
}