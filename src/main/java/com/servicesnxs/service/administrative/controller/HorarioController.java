package com.servicesnxs.service.administrative.controller;
 
import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.HorarioRequestDTO;
import com.servicesnxs.service.administrative.dto.HorarioResponseDTO;
import com.servicesnxs.service.administrative.service.HorarioService;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.UUID;
 
@RestController
public class HorarioController {
 
    private final HorarioService horarioService;
 
    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }
 
    @GetMapping("/horarios/barberia/{idBarberia}")
    public ApiResponse<List<HorarioResponseDTO>> listarPorBarberia(
            @PathVariable UUID idBarberia) {
        try {
            return ApiResponse.success(
                    "HORARIOS OBTENIDOS CORRECTAMENTE",
                    horarioService.listarPorBarberia(idBarberia)
            );
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }
 
    @PostMapping("/horarios")
    public ApiResponse<HorarioResponseDTO> crear(
            @RequestBody HorarioRequestDTO dto) {
        try {
            return ApiResponse.success(
                    "HORARIO CREADO CORRECTAMENTE",
                    horarioService.crear(dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(400,
                    e.getMessage() != null ? e.getMessage() : "ERROR AL CREAR HORARIO");
        }
    }
 
    @PutMapping("/horarios/{id}")
    public ApiResponse<HorarioResponseDTO> actualizar(
            @PathVariable UUID id,
            @RequestBody HorarioRequestDTO dto) {
        try {
            return ApiResponse.success(
                    "HORARIO ACTUALIZADO CORRECTAMENTE",
                    horarioService.actualizar(id, dto)
            );
        } catch (Exception e) {
            return ApiResponse.error(400,
                    e.getMessage() != null ? e.getMessage() : "ERROR AL ACTUALIZAR HORARIO");
        }
    }
 
    @DeleteMapping("/horarios/{id}")
    public ApiResponse<Void> eliminar(@PathVariable UUID id) {
        try {
            horarioService.eliminar(id);
            return ApiResponse.success("HORARIO ELIMINADO CORRECTAMENTE", null);
        } catch (Exception e) {
            return ApiResponse.error(500, e.getMessage());
        }
    }
}
 
