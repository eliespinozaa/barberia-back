package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.CrearServicioRequest;
import com.servicesnxs.service.administrative.model.Servicio;
import com.servicesnxs.service.administrative.service.ServicioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping("/servicios/barberia/{idBarberia}")
    public ApiResponse<List<Servicio>> listarPorBarberia(@PathVariable UUID idBarberia) {
        try {
            return ApiResponse.success(
                    "SERVICIOS OBTENIDOS CORRECTAMENTE",
                    servicioService.listarPorBarberia(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL OBTENER SERVICIOS");
        }
    }

    @PostMapping("/servicios")
    public ApiResponse<Servicio> crear(@RequestBody CrearServicioRequest request) {
        try {
            return ApiResponse.success(
                    "SERVICIO CREADO CORRECTAMENTE",
                    servicioService.crear(request)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL CREAR SERVICIO");
        }
    }

    @PutMapping("/servicios/{id}")
    public ApiResponse<Servicio> actualizar(@PathVariable UUID id, @RequestBody Servicio datos) {
        try {
            return ApiResponse.success(
                    "SERVICIO ACTUALIZADO CORRECTAMENTE",
                    servicioService.actualizar(id, datos)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL ACTUALIZAR SERVICIO");
        }
    }

    @DeleteMapping("/servicios/{id}")
    public ApiResponse<Void> eliminar(@PathVariable UUID id) {
        try {
            servicioService.eliminar(id);
            return ApiResponse.success("SERVICIO ELIMINADO CORRECTAMENTE", null);
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL ELIMINAR SERVICIO");
        }
    }
}