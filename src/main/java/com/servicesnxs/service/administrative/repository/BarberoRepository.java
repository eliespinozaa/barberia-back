package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.Barbero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BarberoRepository extends JpaRepository<Barbero, UUID> {

    List<Barbero> findByIdBarberiaAndIsDeletedFalse(UUID idBarberia);

    Optional<Barbero> findByIdAndIsDeletedFalse(UUID id);
}