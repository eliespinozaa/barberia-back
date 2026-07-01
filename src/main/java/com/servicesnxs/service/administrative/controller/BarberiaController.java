package com.servicesnxs.service.administrative.controller;


import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.BarberiaRequestDTO;
import com.servicesnxs.service.administrative.dto.BarberiaResponseDTO;
import com.servicesnxs.service.administrative.dto.CambiarEstadoRequestDTO;
import com.servicesnxs.service.administrative.model.Barberia;
import com.servicesnxs.service.administrative.service.BarberiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BarberiaController {
    private final BarberiaService barberiaService;

    public BarberiaController(BarberiaService barberiaService) {
        this.barberiaService = barberiaService;
    }

    @GetMapping("/barberia/barberias")
    public ApiResponse<List<Barberia>> obtenerBarberias() {
        try {
            List<Barberia> barberias = barberiaService.obtenerBarberiasActivas();
            return ApiResponse.success(
                    "BARBERIAS OBTENIDAS CORRECTAMENTE",
                    barberias);
        } catch (Exception e) {
            return ApiResponse.error(
                    500,
                    "ERROR AL OBTENER BARBERIAS");
        }
    }

// listar barberias
    @GetMapping("/listar/barberias")
    public ApiResponse<List<BarberiaResponseDTO>> listar() {
        try {
            return ApiResponse.success(
                    "BARBERIAS OBTENIDAS CORRECTAMENTE",
                    barberiaService.listar()
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL OBTENER BARBERIAS");
        }
    }

// crear el registro de una barberia
    @PostMapping("/barberia/barberias")
    public ApiResponse<BarberiaResponseDTO> crearBarberia(
            @RequestBody BarberiaRequestDTO dto
    ) {
        try {
            return ApiResponse.success(
                    "BARBERÍA CREADA CORRECTAMENTE",
                    barberiaService.crear(dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL CREAR BARBERÍA");
        }
    }

// actualizar el registro de una barberia
    @PutMapping("/barberia/barberias/{id}")
    public ApiResponse<BarberiaResponseDTO> editarBarberia(
            @PathVariable UUID id,
            @RequestBody BarberiaRequestDTO dto
    ) {
        try {
            return ApiResponse.success(
                    "BARBERÍA ACTUALIZADA CORRECTAMENTE",
                    barberiaService.editar(id, dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, "ERROR AL EDITAR BARBERÍA");
        }
    }

// obtener el registro de una barberia
    @GetMapping("/barberia/barberias/{id}")
public ApiResponse<BarberiaResponseDTO> obtenerPorId(@PathVariable UUID id) {
    try {
        return ApiResponse.success(
                "BARBERÍA OBTENIDA CORRECTAMENTE",
                barberiaService.obtenerPorId(id)
        );
    } catch (Exception e) {
        return ApiResponse.error(
                500,
                "ERROR AL OBTENER BARBERÍA"
        );
    }
}

// eliminar el registro de una barberia
@DeleteMapping("/barberia/barberias/{id}")
public ApiResponse<Void> eliminarBarberia(@PathVariable UUID id) {
    try {
        barberiaService.eliminar(id);

        return ApiResponse.success(
                "BARBERÍA ELIMINADA CORRECTAMENTE",
                null
        );

    } catch (Exception e) {
        return ApiResponse.error(
                500,
                "ERROR AL ELIMINAR BARBERÍA"
        );
    }
}

// cambiar estado (activar / suspender) de una barberia
@PatchMapping("/barberia/barberias/{id}/estado")
public ApiResponse<BarberiaResponseDTO> cambiarEstado(
        @PathVariable UUID id,
        @RequestBody CambiarEstadoRequestDTO dto
) {
    try {
        return ApiResponse.success(
                dto.isActivar() ? "BARBERÍA ACTIVADA CORRECTAMENTE" : "BARBERÍA SUSPENDIDA CORRECTAMENTE",
                barberiaService.cambiarEstado(id, dto.isActivar())
        );
    } catch (Exception e) {
        return ApiResponse.error(500, "ERROR AL CAMBIAR ESTADO DE BARBERÍA");
    }
}


@GetMapping("/barberia/barberias/usuario/{idUsuario}")
public ApiResponse<BarberiaResponseDTO> obtenerPorUsuario(@PathVariable UUID idUsuario) {
    try {
        return ApiResponse.success(
                "BARBERÍA OBTENIDA CORRECTAMENTE",
                barberiaService.obtenerPorUsuario(idUsuario)
        );
    } catch (Exception e) {
        return ApiResponse.error(500, "ERROR AL OBTENER BARBERÍA DEL USUARIO");
    }
}
}