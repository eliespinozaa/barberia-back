package com.servicesnxs.service.administrative.repository;

import com.servicesnxs.service.administrative.model.ClienteBarberia;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteBarberiaRepository extends JpaRepository<ClienteBarberia, UUID> {
    boolean existsByClienteIdAndIsDeletedFalse(UUID clienteId);


    
     Optional<ClienteBarberia> findByClienteIdAndBarberiaId(UUID clienteId, UUID barberiaId);
    List<ClienteBarberia> findByClienteId(UUID clienteId);
    boolean existsByClienteIdAndBarberiaId(UUID clienteId, UUID barberiaId);
}