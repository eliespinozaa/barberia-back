package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.*;
import com.servicesnxs.service.administrative.model.ClienteBarberia;
import com.servicesnxs.service.administrative.repository.ClienteBarberiaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteBarberiaService {

    private final ClienteBarberiaRepository repository;

    public ClienteBarberiaService(ClienteBarberiaRepository repository) {
        this.repository = repository;
    }

    public ApiResponse<ClienteBarberiaResponse> asociar(ClienteBarberiaRequest req) {
        if (req.getClienteId() == null || req.getBarberiaId() == null) {
            return ApiResponse.error(400, "CLIENTE_ID Y BARBERIA_ID SON OBLIGATORIOS");
        }

        boolean existe = repository.existsByClienteIdAndBarberiaId(
                req.getClienteId(),
                req.getBarberiaId()
        );

        if (existe) {
            ClienteBarberiaResponse resp = new ClienteBarberiaResponse(
                    true,
                    "EL CLIENTE YA ESTA ASOCIADO A ESTA BARBERIA",
                    req.getBarberiaId()
            );
            return ApiResponse.success("YA EXISTE ASOCIACION", resp);
        }

        ClienteBarberia cb = new ClienteBarberia();
        cb.setClienteId(req.getClienteId());
        cb.setBarberiaId(req.getBarberiaId());
        repository.save(cb);

        ClienteBarberiaResponse resp = new ClienteBarberiaResponse(
                true,
                "CLIENTE ASOCIADO CORRECTAMENTE",
                req.getBarberiaId()
        );
        return ApiResponse.success(resp);
    }

    public ApiResponse<Boolean> estado(UUID clienteId, UUID barberiaId) {
        if (clienteId == null || barberiaId == null) {
            return ApiResponse.error(400, "CLIENTE_ID Y BARBERIA_ID SON OBLIGATORIOS");
        }
        boolean asociado = repository.findByClienteIdAndBarberiaId(clienteId, barberiaId).isPresent();
        return ApiResponse.success(asociado);
    }
}