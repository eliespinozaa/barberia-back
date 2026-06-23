package com.servicesnxs.service.administrative.controller;

import com.servicesnxs.service.administrative.dto.ApiResponse;
import com.servicesnxs.service.administrative.dto.auth.*;
import com.servicesnxs.service.administrative.service.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {

        try {
            AuthResponse response = authService.login(
                    request.getCorreo(),
                    request.getPassword()
            );

            return ResponseEntity.ok(ApiResponse.success(response));

        } catch (Exception e) {
            return ResponseEntity
                    .status(401)
                    .body(ApiResponse.error(401, e.getMessage()));
        }
    }


    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}