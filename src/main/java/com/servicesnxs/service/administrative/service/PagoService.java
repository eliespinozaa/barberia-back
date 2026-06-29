package com.servicesnxs.service.administrative.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.servicesnxs.service.administrative.dto.PagoDTO;
import com.servicesnxs.service.administrative.dto.RegistrarPagoRequest;
import com.servicesnxs.service.administrative.model.Pago;
import com.servicesnxs.service.administrative.repository.PagoRepository;
import com.servicesnxs.service.administrative.repository.SuscripcionRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class PagoService {

private final SuscripcionRepository suscripcionRepository;
private final PagoRepository pagoRepository;


public PagoService(PagoRepository pagoRepository, SuscripcionRepository suscripcionRepository) {
    this.pagoRepository = pagoRepository;
    this.suscripcionRepository = suscripcionRepository;
}

    public List<PagoDTO> listarPorBarberia(UUID idBarberia) {
        return pagoRepository.findPagosPorBarberia(idBarberia)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

   
private PagoDTO map(Pago p) {
    PagoDTO dto = new PagoDTO();
    dto.setId(p.getId());
    dto.setIdSuscripcion(p.getIdSuscripcion());
    dto.setIdBarberia(p.getIdBarberia());
    dto.setMonto(p.getMonto() != null ? p.getMonto().doubleValue() : null);
    dto.setEstado(p.getEstado());
    dto.setMetodoPago(p.getMetodoPago());
    dto.setReferencia(p.getReferencia());
    dto.setFechaPago(p.getFechaPago());

    if (p.getIdSuscripcion() != null) {
        suscripcionRepository.findById(p.getIdSuscripcion())
            .ifPresent(s -> dto.setFechaInicio(s.getFechaInicio()));
    }

    return dto;
}


public PagoDTO registrarPago(UUID idPago, RegistrarPagoRequest req) {
    Pago pago = pagoRepository.findById(idPago)
        .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

    pago.setEstado("PAGADO");
    pago.setMonto(req.getMonto());
    pago.setMetodoPago(req.getMetodoPago());
    pago.setReferencia(req.getReferencia());
    pago.setFechaPago(OffsetDateTime.now());
    pago.setUpdatedAt(OffsetDateTime.now());

    return map(pagoRepository.save(pago));
}
}