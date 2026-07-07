package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.BarberiaRequestDTO;
import com.servicesnxs.service.administrative.dto.BarberiaResponseDTO;
import com.servicesnxs.service.administrative.dto.CambiarEstadoRequestDTO;
import com.servicesnxs.service.administrative.model.Barberia;
import com.servicesnxs.service.administrative.service.BarberiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class BarberiaController {
    private final BarberiaService barberiaService;

    public BarberiaController(BarberiaService barberiaService) {
        this.barberiaService = barberiaService;
    }

    @GetMapping("/barberia/barberias")
    public ResponseEntity<ApiResponse<List<Barberia>>> obtenerBarberias() {
        var data = barberiaService.obtenerBarberiasActivas();
        return ResponseEntity.ok(ApiResponse.success("BARBERIAS OBTENIDAS CORRECTAMENTE", data));
    }

    @GetMapping("/listar/barberias")
    public ResponseEntity<ApiResponse<List<BarberiaResponseDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.success("BARBERIAS OBTENIDAS CORRECTAMENTE", barberiaService.listar()));
    }

    @PostMapping("/barberia/barberias")
    public ResponseEntity<ApiResponse<BarberiaResponseDTO>> crearBarberia(@RequestBody BarberiaRequestDTO dto) {
        BarberiaResponseDTO creada = barberiaService.crear(dto);
        URI location = URI.create("/barberia/barberias/" + creada.getId());
        return ResponseEntity.created(location)
                .body(ApiResponse.success("BARBERÍA CREADA CORRECTAMENTE", creada));
    }

    @PutMapping("/barberia/barberias/{id}")
    public ResponseEntity<ApiResponse<BarberiaResponseDTO>> editarBarberia(
            @PathVariable UUID id, @RequestBody BarberiaRequestDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("BARBERÍA ACTUALIZADA CORRECTAMENTE", barberiaService.editar(id, dto)));
    }

    @GetMapping("/barberia/barberias/{id}")
    public ResponseEntity<ApiResponse<BarberiaResponseDTO>> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success("BARBERÍA OBTENIDA CORRECTAMENTE", barberiaService.obtenerPorId(id)));
    }

    @DeleteMapping("/barberia/barberias/{id}")
    public ResponseEntity<Void> eliminarBarberia(@PathVariable UUID id) {
        barberiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/barberia/barberias/{id}/estado")
    public ResponseEntity<ApiResponse<BarberiaResponseDTO>> cambiarEstado(
            @PathVariable UUID id, @RequestBody CambiarEstadoRequestDTO dto) {
        var data = barberiaService.cambiarEstado(id, dto.isActivar());
        String mensaje = dto.isActivar() ? "BARBERÍA ACTIVADA CORRECTAMENTE" : "BARBERÍA SUSPENDIDA CORRECTAMENTE";
        return ResponseEntity.ok(ApiResponse.success(mensaje, data));
    }

    @GetMapping("/barberia/barberias/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse<BarberiaResponseDTO>> obtenerPorUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(ApiResponse.success("BARBERÍA OBTENIDA CORRECTAMENTE", barberiaService.obtenerPorUsuario(idUsuario)));
    }
}