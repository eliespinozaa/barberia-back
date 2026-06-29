package com.servicesnxs.service.administrative.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.servicesnxs.service.administrative.dto.SuscripcionDTO;
import com.servicesnxs.service.administrative.model.Pago;
import com.servicesnxs.service.administrative.model.Suscripcion;
import com.servicesnxs.service.administrative.repository.PagoRepository;
import com.servicesnxs.service.administrative.repository.SuscripcionRepository;

@Service
public class SuscripcionService {

    private final SuscripcionRepository suscripcionRepository;
    private final PagoRepository pagoRepository;

    private static final BigDecimal PRECIO_MEMBRESIA = new BigDecimal("499.00");

    public SuscripcionService(
            SuscripcionRepository suscripcionRepository,
            PagoRepository pagoRepository
    ) {
        this.suscripcionRepository = suscripcionRepository;
        this.pagoRepository = pagoRepository;
    }

    public SuscripcionDTO obtenerActiva(UUID idBarberia) {
    List<Suscripcion> suscripciones = suscripcionRepository.findUltimaSuscripcion(idBarberia);
    if (suscripciones.isEmpty()) {
        throw new RuntimeException("No hay suscripción registrada");
    }
    return map(suscripciones.get(0)); 
}

    public SuscripcionDTO suspender(UUID idSuscripcion) {
        Suscripcion s = suscripcionRepository.findById(idSuscripcion)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada"));

        if (!"ACTIVA".equals(s.getEstado())) {
            throw new RuntimeException("Solo se puede suspender una suscripción ACTIVA");
        }

        s.setEstado("SUSPENDIDA");
        s.setUpdatedAt(OffsetDateTime.now());
        return map(suscripcionRepository.save(s));
    }

    public SuscripcionDTO reactivar(UUID idSuscripcion) {
        Suscripcion anterior = suscripcionRepository.findById(idSuscripcion)
                .orElseThrow(() -> new RuntimeException("Suscripción no encontrada"));

        String estadoActual = anterior.getEstado();

        if ("SUSPENDIDA".equals(estadoActual) && !anterior.getFechaFin().isBefore(LocalDate.now())) {
            anterior.setEstado("ACTIVA");
            anterior.setUpdatedAt(OffsetDateTime.now());
            return map(suscripcionRepository.save(anterior));
        }

        LocalDate hoy = LocalDate.now();
       
LocalDate inicioNuevo = hoy;
LocalDate finNuevo    = hoy.plusDays(30);

        Suscripcion nueva = new Suscripcion();
        nueva.setIdBarberia(anterior.getIdBarberia());
        nueva.setFechaInicio(inicioNuevo);
        nueva.setFechaFin(finNuevo);
        nueva.setEstado("ACTIVA");
        nueva.setCreatedAt(OffsetDateTime.now());
        nueva.setUpdatedAt(OffsetDateTime.now());
        nueva.setCreatedBy("system");
        nueva.setIsDeleted(false);
        Suscripcion guardada = suscripcionRepository.save(nueva);

        Pago pago = new Pago();
        pago.setIdSuscripcion(guardada.getId());
        pago.setIdBarberia(guardada.getIdBarberia());
        pago.setMonto(PRECIO_MEMBRESIA);
        pago.setEstado("PENDIENTE");
        pago.setCreatedAt(OffsetDateTime.now());
        pago.setUpdatedAt(OffsetDateTime.now());
        pago.setCreatedBy("system");
        pago.setIsDeleted(false);
        pagoRepository.save(pago);

        return map(guardada);
    }

    private SuscripcionDTO map(Suscripcion s) {
        SuscripcionDTO dto = new SuscripcionDTO();
        dto.setId(s.getId());
        dto.setIdBarberia(s.getIdBarberia());
        dto.setFechaInicio(s.getFechaInicio());
        dto.setFechaFin(s.getFechaFin());
        dto.setEstado(s.getEstado());
        return dto;
    }
}