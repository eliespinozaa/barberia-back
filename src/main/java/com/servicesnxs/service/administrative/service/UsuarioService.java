package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.UsuarioDTO;
import com.servicesnxs.service.administrative.dto.registrarUsuario.*;
import com.servicesnxs.service.administrative.model.*;
import com.servicesnxs.service.administrative.repository.*;
import com.servicesnxs.service.administrative.util.EncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EncryptionService encryptionService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            EncryptionService encryptionService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.encryptionService = encryptionService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public AuthResponse register(RegisterRequest request) {
        Optional<Usuario> exists = usuarioRepository.findByEmailAndIsDeletedFalse(request.getCorreo());
        if (exists.isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        OffsetDateTime now = OffsetDateTime.now();

        Usuario user = new Usuario();
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setEmail(request.getCorreo());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setTelefono(request.getTelefono());
        user.setRolId(request.getRolId());
        user.setEstado(request.getEstado() != null ? request.getEstado() : 1);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setCreatedBy("system");
        user.setIsDeleted(false);

        Usuario saved = usuarioRepository.save(user);

        String rol = resolverRol(saved.getRolId());

        return new AuthResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getNombre(),
                saved.getApellido(),
                rol,
                saved.getTelefono(),
                saved.getEstado()
        );
    }

    private String resolverRol(Long rolId) {
        return switch (rolId.intValue()) {
            case 1 -> "SUPER_ADMIN";
            case 2 -> "DUENO";
            case 3 -> "CLIENTE";
            case 4 -> "BARBERO";
            default -> "DESCONOCIDO";
        };
    }

//listar usuarios que esten activos y que no esten eliminados
    public List<UsuarioDTO> listarUsuarios() {

    List<Usuario> users = usuarioRepository.findAllByIsDeletedFalseAndEstado(1);
    

    return users.stream().map(user -> {

        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(user.getId());
        dto.setNombre(user.getNombre());
        dto.setApellido(user.getApellido());
        dto.setEmail(user.getEmail());
        dto.setTelefono(user.getTelefono());
        dto.setFotoPerfil(user.getFotoPerfil());

        dto.setRol(resolverRol(user.getRolId()));
        dto.setEstado(user.getEstado());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;

    }).toList();
}
}