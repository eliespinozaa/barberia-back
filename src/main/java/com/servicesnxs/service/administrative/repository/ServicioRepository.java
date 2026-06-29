package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServicioRepository extends JpaRepository<Servicio, UUID> {
    List<Servicio> findByIdBarberiaAndIsDeletedFalse(UUID idBarberia);
    Optional<Servicio> findByIdAndIsDeletedFalse(UUID id);
}