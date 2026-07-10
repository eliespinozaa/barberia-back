package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.*;
import com.servicesnxs.service.administrative.exception.ResourceNotFoundException;
import com.servicesnxs.service.administrative.model.ClienteBarberia;
import com.servicesnxs.service.administrative.repository.ClienteBarberiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteBarberiaService {

    private final ClienteBarberiaRepository repository;
    private final BarberiaService barberiaService;

    public ClienteBarberiaService(ClienteBarberiaRepository repository,
                                   BarberiaService barberiaService) {
        this.repository = repository;
        this.barberiaService = barberiaService;
    }

    public ClienteBarberiaResponse asociar(ClienteBarberiaRequest req) {
        if (req.getClienteId() == null || req.getBarberiaId() == null) {
            throw new IllegalArgumentException("CLIENTE_ID Y BARBERIA_ID SON OBLIGATORIOS");
        }

        boolean existe = repository.existsByClienteIdAndBarberiaId(
                req.getClienteId(),
                req.getBarberiaId()
        );

        if (existe) {
            return new ClienteBarberiaResponse(
                    true,
                    "EL CLIENTE YA ESTA ASOCIADO A ESTA BARBERIA",
                    req.getBarberiaId()
            );
        }

        ClienteBarberia cb = new ClienteBarberia();
        cb.setClienteId(req.getClienteId());
        cb.setBarberiaId(req.getBarberiaId());
        repository.save(cb);

        return new ClienteBarberiaResponse(
                true,
                "CLIENTE ASOCIADO CORRECTAMENTE",
                req.getBarberiaId()
        );
    }

    public boolean estado(UUID clienteId, UUID barberiaId) {
        if (clienteId == null || barberiaId == null) {
            throw new IllegalArgumentException("CLIENTE_ID Y BARBERIA_ID SON OBLIGATORIOS");
        }
        return repository.findByClienteIdAndBarberiaId(clienteId, barberiaId).isPresent();
    }

    public List<ClienteBarberiaListItemResponse> listarPorBarberia(UUID barberiaId) {
        if (barberiaId == null) {
            throw new IllegalArgumentException("BARBERIA_ID ES OBLIGATORIO");
        }
        return repository.listarPorBarberia(barberiaId);
    }

    public BarberiaResponseDTO obtenerBarberiaDelCliente(UUID clienteId) {
        if (clienteId == null) {
            throw new IllegalArgumentException("CLIENTE_ID ES OBLIGATORIO");
        }

        ClienteBarberia asociacion = repository
                .findTopByClienteIdAndIsDeletedFalseOrderByFechaRegistroDesc(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Este cliente no tiene ninguna barbería asociada"));

        return barberiaService.obtenerPorId(asociacion.getBarberiaId());
    }
}