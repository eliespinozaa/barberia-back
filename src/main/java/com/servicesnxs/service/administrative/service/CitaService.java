package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.CitaHistorialResponse;
import com.servicesnxs.service.administrative.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<CitaHistorialResponse> historial(UUID clienteId, UUID barberiaId) {
        return citaRepository.historialPorClienteYBarberia(clienteId, barberiaId);
    }
}