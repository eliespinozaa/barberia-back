package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.CrearBarberoRequest;
import com.servicesnxs.service.administrative.model.Barbero;
import com.servicesnxs.service.administrative.service.BarberoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BarberoController {

    private final BarberoService service;

    public BarberoController(BarberoService service) {
        this.service = service;
    }

    @GetMapping("/barberos/barberia/{idBarberia}")
    public ApiResponse<List<Barbero>> listarPorBarberia(
            @PathVariable UUID idBarberia
    ) {
        try {
            return ApiResponse.success(
                    "BARBEROS OBTENIDOS CORRECTAMENTE",
                    service.listarPorBarberia(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }

@PutMapping("/barberos/{id}")
public ApiResponse<Barbero> actualizar(
        @PathVariable UUID id,
        @RequestBody Barbero datos
) {
    try {
        return ApiResponse.success(
                "BARBERO ACTUALIZADO CORRECTAMENTE",
                service.actualizar(id, datos)
        );
    } catch (Exception e) {
        return ApiResponse.error(500, e.getMessage());
    }
}


@PostMapping("/barberos")
public ApiResponse<Barbero> crear(@RequestBody CrearBarberoRequest request) {
    try {
        return ApiResponse.success(
                "BARBERO CREADO CORRECTAMENTE",
                service.crear(request)
        );
    } catch (Exception e) {
        return ApiResponse.error(400, e.getMessage());
    }
}

@DeleteMapping("/barberos/{id}")
public ApiResponse<Void> eliminarBarbero(@PathVariable UUID id) {
    try {
        service.eliminar(id);
        return ApiResponse.success("BARBERO ELIMINADO CORRECTAMENTE", null);
    } catch (Exception e) {
        return ApiResponse.error(500, "ERROR AL ELIMINAR BARBERO");
    }
}
}