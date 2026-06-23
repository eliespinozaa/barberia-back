package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.model.Usuario;
import com.servicesnxs.service.administrative.repository.UsuarioRepository;
import com.servicesnxs.service.administrative.repository.ClienteBarberiaRepository;
import com.servicesnxs.service.administrative.dto.auth.AuthResponse;
import com.servicesnxs.service.administrative.util.EncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteBarberiaRepository clienteBarberiaRepository;
    private final EncryptionService encryptionService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            ClienteBarberiaRepository clienteBarberiaRepository,
            EncryptionService encryptionService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.clienteBarberiaRepository = clienteBarberiaRepository;
        this.encryptionService = encryptionService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public AuthResponse login(String correo, String password) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmailAndIsDeletedFalse(correo);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Usuario user = userOpt.get();
        if (user.getEstado() == null || user.getEstado() != 1) {
            throw new RuntimeException("Usuario inactivo");
        }

        String passwordBD = user.getPasswordHash();
        boolean valido = false;
        try {
            String decrypt = encryptionService.decrypt(passwordBD);
            if (password.equals(decrypt)) {
                valido = true;
            } else if (passwordEncoder.matches(password, decrypt)) {
                valido = true;
            }
        } catch (Exception e) {
        }
        if (!valido) {
            if (password.equals(passwordBD)
                    || passwordEncoder.matches(password, passwordBD)) {
                valido = true;
            }
        }
        if (!valido) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String nombreCompleto = user.getNombre() + " " + user.getApellido();
        String rol = switch (user.getRolId().intValue()) {
            case 1 -> "SUPER_ADMIN";
            case 2 -> "DUENO";
            case 3 -> "CLIENTE";
            case 4 -> "BARBERO";
            default -> "DESCONOCIDO";
        };

        // Solo validar para clientes
        boolean clienteAsociado = false;
        if (user.getRolId().intValue() == 3) {
            clienteAsociado = clienteBarberiaRepository
                    .existsByClienteIdAndIsDeletedFalse(user.getId());
        }

        String token = "FAKE-JWT-TOKEN";
        //String token = jwtUtil.generateToken(
        //user.getEmail(),
        //rol,
        //user.getId()
//);

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                nombreCompleto,
                rol,
                "Login exitoso",
                token,
                clienteAsociado
        );
    }
}