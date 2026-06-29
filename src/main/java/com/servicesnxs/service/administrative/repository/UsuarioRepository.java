package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmailAndIsDeletedFalse(String email);

   List<Usuario> findAllByIsDeletedFalseAndEstado(Integer estado);

   Optional<Usuario> findByIdAndIsDeletedFalse(UUID id);
}