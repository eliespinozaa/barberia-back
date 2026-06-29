package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.registrarUsuario.ActualizarUsuarioRequest;
import com.servicesnxs.service.administrative.dto.registrarUsuario.AuthResponse;
import com.servicesnxs.service.administrative.model.Usuario;
import com.servicesnxs.service.administrative.dto.*;
import com.servicesnxs.service.administrative.dto.registrarUsuario.RegisterRequest;
import com.servicesnxs.service.administrative.service.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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


    @GetMapping("/listar/usuarios")
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


    @GetMapping("/usuarios/{id}")
public ApiResponse<UsuarioDTO> obtenerUsuario(
        @PathVariable UUID id
) {
    try {
        return ApiResponse.success(
                "USUARIO OBTENIDO CORRECTAMENTE",
                usuarioService.obtenerPorId(id)
        );
    } catch (Exception e) {
        return ApiResponse.error(
                500,
                "ERROR AL OBTENER USUARIO"
        );
    }
}


@PutMapping("/usuarios/{id}")
public ResponseEntity<ApiResponse<UsuarioDTO>> actualizar(
        @PathVariable UUID id,
        @RequestBody ActualizarUsuarioRequest request
) {
    try {
        UsuarioDTO actualizado = usuarioService.actualizar(id, request);
        return ResponseEntity.ok(
                ApiResponse.success("USUARIO ACTUALIZADO CORRECTAMENTE", actualizado)
        );
    } catch (Exception e) {
        return ResponseEntity
                .status(400)
                .body(ApiResponse.error(400, e.getMessage()));
    }
}
}