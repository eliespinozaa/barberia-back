package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.BarberiaRequestDTO;
import com.servicesnxs.service.administrative.dto.BarberiaResponseDTO;
import com.servicesnxs.service.administrative.model.Barberia;
import com.servicesnxs.service.administrative.repository.BarberiaRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BarberiaService {
    private final BarberiaRepository barberiaRepository;

    public BarberiaService(BarberiaRepository barberiaRepository) {
        this.barberiaRepository = barberiaRepository;
    }

    public List<Barberia> obtenerBarberiasActivas() {
        return barberiaRepository.findByEstadoConHorarios((short) 1);
    }

    // CREATE
    public BarberiaResponseDTO crear(BarberiaRequestDTO dto) {
        OffsetDateTime now = OffsetDateTime.now();
        Barberia b = new Barberia();
        b.setNombre(dto.getNombre());
        b.setDireccion(dto.getDireccion());
        b.setTelefono(dto.getTelefono());
        b.setImagen(dto.getImagen());
        b.setEstado(dto.getEstado());

        b.setCreatedAt(now);
        b.setUpdatedAt(now);
        b.setCreatedBy("system");
        b.setUpdatedBy(null);
        b.setIsDeleted(false);
        b.setUsuarioId(UUID.fromString(dto.getIdUsuario()));

        Barberia saved = barberiaRepository.save(b);

        return map(saved);
    }

    // UPDATE
    public BarberiaResponseDTO editar(UUID id, BarberiaRequestDTO dto) {
        OffsetDateTime now = OffsetDateTime.now();

        Barberia b = barberiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbería no encontrada"));

        b.setNombre(dto.getNombre());
        b.setDireccion(dto.getDireccion());
        b.setTelefono(dto.getTelefono());
        b.setImagen(dto.getImagen());
        b.setEstado(dto.getEstado());
        b.setUpdatedAt(now);
        b.setUpdatedBy("system");

        if (dto.getIdUsuario() != null) {
            b.setUsuarioId(UUID.fromString(dto.getIdUsuario()));
        }
        return map(barberiaRepository.save(b));
    }

    // LIST
    public List<BarberiaResponseDTO> listar() {
        return barberiaRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private BarberiaResponseDTO map(Barberia b) {
        return new BarberiaResponseDTO(
                b.getIdBarberia(),
                b.getNombre(),
                b.getDireccion(),
                b.getTelefono(),
                b.getImagen(),
                b.getEstado(),
                b.getUsuarioId());
    }
}