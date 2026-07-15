package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.CitaActualizarRequest;
import com.servicesnxs.service.administrative.dto.CitaCrearRequest;
import com.servicesnxs.service.administrative.dto.CitaDiaResponse;
import com.servicesnxs.service.administrative.dto.CitaHistorialResponse;
import com.servicesnxs.service.administrative.service.CitaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping("/citas/historial")
    public ResponseEntity<ApiResponse<List<CitaHistorialResponse>>> historial(
            @RequestParam UUID clienteId,
            @RequestParam UUID barberiaId
    ) {
        var data = citaService.historial(clienteId, barberiaId);
        return ResponseEntity.ok(ApiResponse.success("HISTORIAL OBTENIDO CORRECTAMENTE", data));
    }

    @GetMapping("/citas/barberia/{idBarberia}/dia")
    public ResponseEntity<ApiResponse<List<CitaDiaResponse>>> listarPorDia(
            @PathVariable UUID idBarberia,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        var data = citaService.listarPorDia(idBarberia, fecha);
        return ResponseEntity.ok(ApiResponse.success("CITAS DEL DIA OBTENIDAS CORRECTAMENTE", data));
    }

    @PatchMapping("/citas/{idCita}/confirmar")
    public ResponseEntity<ApiResponse<Void>> confirmar(@PathVariable UUID idCita) {
        citaService.confirmar(idCita);
        return ResponseEntity.ok(ApiResponse.success("CITA CONFIRMADA CORRECTAMENTE", null));
    }

    @PatchMapping("/citas/{idCita}/cancelar")
    public ResponseEntity<ApiResponse<Void>> cancelar(@PathVariable UUID idCita) {
        citaService.cancelar(idCita);
        return ResponseEntity.ok(ApiResponse.success("CITA CANCELADA CORRECTAMENTE", null));
    }

    @PatchMapping("/citas/{idCita}/finalizar")
    public ResponseEntity<ApiResponse<Void>> finalizar(@PathVariable UUID idCita) {
        citaService.finalizar(idCita);
        return ResponseEntity.ok(ApiResponse.success("CITA FINALIZADA CORRECTAMENTE", null));
    }


    @PostMapping("/citas")
public ResponseEntity<ApiResponse<UUID>> crear(@RequestBody CitaCrearRequest request) {
    UUID idCreada = citaService.crear(request);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("CITA CREADA CORRECTAMENTE", idCreada));
}


@PutMapping("/citas/{idCita}")
public ResponseEntity<ApiResponse<Void>> actualizar(
        @PathVariable UUID idCita,
        @RequestBody CitaActualizarRequest request
) {
    citaService.actualizar(idCita, request);
    return ResponseEntity.ok(ApiResponse.success("CITA ACTUALIZADA CORRECTAMENTE", null));
}
}