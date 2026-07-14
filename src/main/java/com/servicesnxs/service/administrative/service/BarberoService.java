package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.CrearBarberoRequest;
import com.servicesnxs.service.administrative.exception.ResourceNotFoundException;
import com.servicesnxs.service.administrative.model.Barbero;
import com.servicesnxs.service.administrative.model.Usuario;
import com.servicesnxs.service.administrative.repository.BarberoRepository;
import com.servicesnxs.service.administrative.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BarberoService {

    private final BarberoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public BarberoService(BarberoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Barbero> listarPorBarberia(UUID idBarberia) {
        return repository.findByIdBarberiaAndIsDeletedFalse(idBarberia);
    }

   public Barbero actualizar(UUID id, Barbero datos) {
    Barbero existente = repository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Barbero no encontrado"));
   
        existente.setEspecialidad(datos.getEspecialidad());
        existente.setDescripcion(datos.getDescripcion());
        existente.setExperiencia(datos.getExperiencia());
        existente.setEstado(datos.getEstado());
        existente.setUpdatedAt(OffsetDateTime.now());
        return repository.save(existente);
    }

public Barbero crear(CrearBarberoRequest request) {
    Optional<Usuario> existe = usuarioRepository.findByEmailAndIsDeletedFalse(request.getCorreo());
    if (existe.isPresent()) {
        throw new IllegalStateException("El correo ya está registrado"); 
    }

    OffsetDateTime now = OffsetDateTime.now();

    Usuario usuario = new Usuario();
    usuario.setNombre(request.getNombre());
    usuario.setApellido(request.getApellido());
    usuario.setEmail(request.getCorreo());
    usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
    usuario.setTelefono(request.getTelefono());
    usuario.setFotoPerfil(request.getFotoPerfil());
    usuario.setRolId(4L); 
    usuario.setEstado(request.getEstado() != null ? request.getEstado() : 1);
    usuario.setCreatedAt(now);
    usuario.setUpdatedAt(now);
    usuario.setCreatedBy("system");
    usuario.setIsDeleted(false);

    Usuario usuarioGuardado = usuarioRepository.save(usuario);

    Barbero barbero = new Barbero();
    barbero.setIdUsuario(usuarioGuardado.getId());
    barbero.setIdBarberia(request.getIdBarberia());
    barbero.setEspecialidad(request.getEspecialidad());
    barbero.setDescripcion(request.getDescripcion());
    barbero.setExperiencia(request.getExperiencia());
    barbero.setEstado(request.getEstado() != null ? request.getEstado().shortValue() : (short) 1); // ✅ fix
    barbero.setCreatedAt(now);
    barbero.setUpdatedAt(now);
    barbero.setCreatedBy("system");
    barbero.setIsDeleted(false);

    return repository.save(barbero);
}

public void eliminar(UUID id) {
    Barbero existente = repository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new ResourceNotFoundException("Barbero no encontrado"));
  
    existente.setEstado((short) 0);
    existente.setIsDeleted(true);
    existente.setUpdatedBy("system");
    existente.setDeletedAt(OffsetDateTime.now());
    existente.setUpdatedAt(OffsetDateTime.now());
    repository.save(existente);

    usuarioRepository.findByIdAndIsDeletedFalse(existente.getIdUsuario())
        .ifPresent(usuario -> {
            usuario.setEstado(0);
            usuario.setIsDeleted(true);
            usuario.setUpdatedBy("system");
            usuario.setDeletedAt(OffsetDateTime.now());
            usuario.setUpdatedAt(OffsetDateTime.now());
            usuarioRepository.save(usuario);
        });
}


public Barbero obtenerPorUsuario(UUID idUsuario) {
    return repository.findByIdUsuarioAndIsDeletedFalse(idUsuario)
            .stream()
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("Barbero no encontrado para este usuario"));
}
}