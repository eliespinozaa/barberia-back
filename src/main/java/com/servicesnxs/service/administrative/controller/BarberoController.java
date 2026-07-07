package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.CrearBarberoRequest;
import com.servicesnxs.service.administrative.model.Barbero;
import com.servicesnxs.service.administrative.service.BarberoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class BarberoController {
    private final BarberoService service;

    public BarberoController(BarberoService service) {
        this.service = service;
    }

    @GetMapping("/barberos/barberia/{idBarberia}")
    public ResponseEntity<ApiResponse<List<Barbero>>> listarPorBarberia(@PathVariable UUID idBarberia) {
        return ResponseEntity.ok(ApiResponse.success("BARBEROS OBTENIDOS CORRECTAMENTE", service.listarPorBarberia(idBarberia)));
    }

    @PutMapping("/barberos/{id}")
    public ResponseEntity<ApiResponse<Barbero>> actualizar(@PathVariable UUID id, @RequestBody Barbero datos) {
        return ResponseEntity.ok(ApiResponse.success("BARBERO ACTUALIZADO CORRECTAMENTE", service.actualizar(id, datos)));
    }

    @PostMapping("/barberos")
    public ResponseEntity<ApiResponse<Barbero>> crear(@RequestBody CrearBarberoRequest request) {
        Barbero creado = service.crear(request);
        URI location = URI.create("/barberos/" + creado.getId());
        return ResponseEntity.created(location)
                .body(ApiResponse.success("BARBERO CREADO CORRECTAMENTE", creado));
    }

    @DeleteMapping("/barberos/{id}")
    public ResponseEntity<Void> eliminarBarbero(@PathVariable UUID id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}