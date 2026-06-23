package com.servicesnxs.service.administrative.controller;


import com.servicesnxs.service.administrative.dto.*;
import com.servicesnxs.service.administrative.service.ClienteBarberiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ClienteBarberiaController {

    private final ClienteBarberiaService service;

    public ClienteBarberiaController(ClienteBarberiaService service) {
        this.service = service;
    }

    @PostMapping("/cliente-barberia/asociar")
    public ResponseEntity<ApiResponse<ClienteBarberiaResponse>> asociar(
            @RequestBody ClienteBarberiaRequest request
    ) {
        return ResponseEntity.ok(service.asociar(request));
    }

    @GetMapping("/cliente-barberia/estado")
    public ResponseEntity<ApiResponse<Boolean>> estado(
            @RequestParam UUID clienteId,
            @RequestParam UUID barberiaId
    ) {
        return ResponseEntity.ok(service.estado(clienteId, barberiaId));
    }
}