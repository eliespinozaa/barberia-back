package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.ResenaCrearRequest;
import com.servicesnxs.service.administrative.dto.ResenaResumenDTO;
import com.servicesnxs.service.administrative.dto.ResenaResponseDTO;
import com.servicesnxs.service.administrative.service.ResenaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @GetMapping("/resenas/barberia/{idBarberia}")
    public ResponseEntity<ApiResponse<List<ResenaResponseDTO>>> listarPorBarberia(
            @PathVariable UUID idBarberia) {
        var data = resenaService.listarPorBarberia(idBarberia);
        return ResponseEntity.ok(ApiResponse.success("RESEÑAS OBTENIDAS CORRECTAMENTE", data));
    }

    @GetMapping("/resenas/barberia/{idBarberia}/resumen")
    public ResponseEntity<ApiResponse<ResenaResumenDTO>> resumen(
            @PathVariable UUID idBarberia) {
        var data = resenaService.resumen(idBarberia);
        return ResponseEntity.ok(ApiResponse.success("RESUMEN OBTENIDO CORRECTAMENTE", data));
    }

    @GetMapping("/resenas/cita/{idCita}/existe")
    public ResponseEntity<ApiResponse<Boolean>> existePorCita(@PathVariable UUID idCita) {
        var data = resenaService.existePorCita(idCita);
        return ResponseEntity.ok(ApiResponse.success("CONSULTA REALIZADA CORRECTAMENTE", data));
    }

    @PostMapping("/resenas")
    public ResponseEntity<ApiResponse<ResenaResponseDTO>> crear(@RequestBody ResenaCrearRequest request) {
        var data = resenaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("RESEÑA CREADA CORRECTAMENTE", data));
    }
}