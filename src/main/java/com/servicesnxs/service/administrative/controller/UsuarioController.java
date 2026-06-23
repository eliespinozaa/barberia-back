package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.registrarUsuario.AuthResponse;
import com.servicesnxs.service.administrative.model.Usuario;
import com.servicesnxs.service.administrative.dto.*;
import com.servicesnxs.service.administrative.dto.registrarUsuario.RegisterRequest;
import com.servicesnxs.service.administrative.service.*;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {

        try {
            AuthResponse response = usuarioService.register(request);

            return ResponseEntity.ok(
                    ApiResponse.success("USUARIO REGISTRADO EXITOSAMENTE", response)
            );

        } catch (Exception e) {
            return ResponseEntity
                    .status(400)
                    .body(ApiResponse.error(400, e.getMessage()));
        }
    }


    @GetMapping("/usuarios/listar")
    public ResponseEntity<ApiResponse<List<UsuarioDTO>>> listarUsuarios() {

        try {

            List<UsuarioDTO> data = usuarioService.listarUsuarios();

            return ResponseEntity.ok(
                ApiResponse.success(
                    "USUARIOS OBTENIDOS CORRECTAMENTE",
                    data
                )
            );

        } catch (Exception e) {

            return ResponseEntity.status(500).body(
                ApiResponse.internalError()
            );
        }
    }
}