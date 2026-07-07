package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.model.Usuario;
import com.servicesnxs.service.administrative.repository.UsuarioRepository;
import com.servicesnxs.service.administrative.repository.ClienteBarberiaRepository;
import com.servicesnxs.service.administrative.dto.auth.AuthResponse;
import com.servicesnxs.service.administrative.exception.AccountInactiveException;
import com.servicesnxs.service.administrative.exception.AuthenticationFailedException;
import com.servicesnxs.service.administrative.util.EncryptionService;
import com.servicesnxs.service.administrative.util.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteBarberiaRepository clienteBarberiaRepository;
    private final EncryptionService encryptionService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

public AuthService(
        UsuarioRepository usuarioRepository,
        ClienteBarberiaRepository clienteBarberiaRepository,
        EncryptionService encryptionService,
        JwtUtil jwtUtil
) {
    this.usuarioRepository = usuarioRepository;
    this.clienteBarberiaRepository = clienteBarberiaRepository;
    this.encryptionService = encryptionService;
    this.jwtUtil = jwtUtil;
    this.passwordEncoder = new BCryptPasswordEncoder();
}

public AuthResponse login(String correo, String password) {
    Optional<Usuario> userOpt = usuarioRepository.findByEmailAndIsDeletedFalse(correo);
    if (userOpt.isEmpty()) {
        throw new AuthenticationFailedException("Correo o contraseña incorrectos");
    }
    Usuario user = userOpt.get();

    if (user.getEstado() == null || user.getEstado() != 1) {
        throw new AccountInactiveException("El usuario está inactivo");
    }

    String passwordBD = user.getPasswordHash();
    boolean valido = false;
    try {
        String decrypt = encryptionService.decrypt(passwordBD);
        if (password.equals(decrypt) || passwordEncoder.matches(password, decrypt)) {
            valido = true;
        }
    } catch (Exception e) {
        // ignorado intencionalmente, se intenta el fallback abajo
    }
    if (!valido && (password.equals(passwordBD) || passwordEncoder.matches(password, passwordBD))) {
        valido = true;
    }
    if (!valido) {
        throw new AuthenticationFailedException("Correo o contraseña incorrectos");
    }

    String nombreCompleto = user.getNombre() + " " + user.getApellido();
    String rol = switch (user.getRolId().intValue()) {
        case 1 -> "SUPER_ADMIN";
        case 2 -> "DUENO";
        case 3 -> "CLIENTE";
        case 4 -> "BARBERO";
        default -> "DESCONOCIDO";
    };

    boolean clienteAsociado = user.getRolId().intValue() == 3
            && clienteBarberiaRepository.existsByClienteIdAndIsDeletedFalse(user.getId());

    String token = jwtUtil.generateToken(user.getEmail(), rol, user.getId());

    return new AuthResponse(user.getId(), user.getEmail(), nombreCompleto, rol,
            "Login exitoso", token, clienteAsociado);
}
}