package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.CrearServicioRequest;
import com.servicesnxs.service.administrative.model.Servicio;
import com.servicesnxs.service.administrative.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ServicioService {

    private final ServicioRepository repository;

    public ServicioService(ServicioRepository repository) {
        this.repository = repository;
    }

    public List<Servicio> listarPorBarberia(UUID idBarberia) {
        return repository.findByIdBarberiaAndIsDeletedFalse(idBarberia);
    }

    public Servicio crear(CrearServicioRequest request) {
        OffsetDateTime now = OffsetDateTime.now();

        Servicio servicio = new Servicio();
        servicio.setIdBarberia(request.getIdBarberia());
        servicio.setNombre(request.getNombre());
        servicio.setDescripcion(request.getDescripcion());
        servicio.setPrecio(request.getPrecio());
        servicio.setImagen(request.getImagen());
        servicio.setEstado(request.getEstado() != null ? request.getEstado().shortValue() : (short) 1);
        servicio.setCreatedAt(now);
        servicio.setUpdatedAt(now);
        servicio.setCreatedBy("system");
        servicio.setIsDeleted(false);

        return repository.save(servicio);
    }

    public Servicio actualizar(UUID id, Servicio datos) {
        Servicio existente = repository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        if (datos.getImagen() != null && !datos.getImagen().isBlank()) {
            existente.setImagen(datos.getImagen());
        }
        if (datos.getEstado() != null) {
            existente.setEstado(datos.getEstado());
        }
        existente.setUpdatedAt(OffsetDateTime.now());

        return repository.save(existente);
    }

    public void eliminar(UUID id) {
        Servicio existente = repository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        existente.setEstado((short) 0);
        existente.setIsDeleted(true);
        existente.setUpdatedBy("system");
        existente.setDeletedAt(OffsetDateTime.now());
        existente.setUpdatedAt(OffsetDateTime.now());

        repository.save(existente);
    }
}