package com.servicesnxs.service.administrative.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.PagoDTO;
import com.servicesnxs.service.administrative.service.PagoService;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.servicesnxs.service.administrative.dto.RegistrarPagoRequest;


@RestController
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/pagos/barberia/{idBarberia}")
    public ApiResponse<List<PagoDTO>> listarPorBarberia(@PathVariable UUID idBarberia) {
        try {
            return ApiResponse.success(
                    "PAGOS OBTENIDOS CORRECTAMENTE",
                    pagoService.listarPorBarberia(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

@PatchMapping("/pagos/{idPago}/registrar")
public ApiResponse<PagoDTO> registrarPago(
    @PathVariable UUID idPago,
    @RequestBody RegistrarPagoRequest req
) {
    try {
        return ApiResponse.success(
            "PAGO REGISTRADO CORRECTAMENTE",
            pagoService.registrarPago(idPago, req)
        );
    } catch (Exception e) {
        return ApiResponse.error(500, e.getMessage());
    }
}
}