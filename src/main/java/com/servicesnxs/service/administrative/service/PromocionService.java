package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.PromocionRequestDTO;
import com.servicesnxs.service.administrative.dto.PromocionResponseDTO;
import com.servicesnxs.service.administrative.model.Promocion;
import com.servicesnxs.service.administrative.repository.PromocionRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PromocionService {

    private final PromocionRepository promocionRepository;

    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<PromocionResponseDTO> listarPorBarberia(UUID idBarberia) {
        return promocionRepository.listarPorBarberia(idBarberia)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public PromocionResponseDTO crear(PromocionRequestDTO dto) {
        OffsetDateTime now = OffsetDateTime.now();

        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        Promocion p = new Promocion();
        p.setIdBarberia(UUID.fromString(dto.getIdBarberia()));
        p.setTitulo(dto.getTitulo());
        p.setDescripcion(dto.getDescripcion());
        p.setDescuento(dto.getDescuento());
        p.setFechaInicio(dto.getFechaInicio());
        p.setFechaFin(dto.getFechaFin());
        p.setImagen(dto.getImagen());
        p.setEstado(dto.getEstado() != null ? dto.getEstado() : 1);
        p.setCreatedAt(now);
        p.setUpdatedAt(now);
        p.setCreatedBy("system");
        p.setIsDeleted(false);

        return map(promocionRepository.save(p));
    }

    public PromocionResponseDTO editar(UUID id, PromocionRequestDTO dto) {
        OffsetDateTime now = OffsetDateTime.now();

        Promocion p = promocionRepository.findActiveById(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));

        if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        p.setTitulo(dto.getTitulo());
        p.setDescripcion(dto.getDescripcion());
        p.setDescuento(dto.getDescuento());
        p.setFechaInicio(dto.getFechaInicio());
        p.setFechaFin(dto.getFechaFin());
        p.setImagen(dto.getImagen());
        if (dto.getEstado() != null) {
            p.setEstado(dto.getEstado());
        }
        p.setUpdatedAt(now);
        p.setUpdatedBy("system");

        return map(promocionRepository.save(p));
    }

    public void eliminar(UUID id) {
        OffsetDateTime now = OffsetDateTime.now();
        Promocion p = promocionRepository.findActiveById(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));

        p.setEstado((short) 0);
        p.setIsDeleted(true);
        p.setDeletedAt(now);
        p.setUpdatedAt(now);
        p.setUpdatedBy("system");
        promocionRepository.save(p);
    }

    private PromocionResponseDTO map(Promocion p) {
        return new PromocionResponseDTO(
                p.getId(),
                p.getIdBarberia(),
                p.getTitulo(),
                p.getDescripcion(),
                p.getDescuento(),
                p.getFechaInicio(),
                p.getFechaFin(),
                p.getImagen(),
                p.getEstado()
        );
    }
}