package com.servicesnxs.service.administrative.service;

import com.servicesnxs.service.administrative.dto.CitaCrearRequest;
import com.servicesnxs.service.administrative.dto.CitaDiaResponse;
import com.servicesnxs.service.administrative.dto.CitaHistorialResponse;
import com.servicesnxs.service.administrative.exception.ResourceNotFoundException;
import com.servicesnxs.service.administrative.model.Cita;
import com.servicesnxs.service.administrative.repository.CitaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<CitaDiaResponse> listarPorDia(UUID barberiaId, LocalDate fecha) {
        List<CitaDiaResponse> citas = citaRepository.listarPorBarberiaYFecha(barberiaId, fecha);

        if (fecha.isEqual(LocalDate.now())) {
            LocalDateTime ahora = LocalDateTime.now();
            for (CitaDiaResponse c : citas) {
                if ("CONFIRMADA".equals(c.getEstado())) {
                    LocalDateTime inicio = LocalDateTime.of(fecha, c.getHora());
                    LocalDateTime fin = LocalDateTime.of(fecha, c.getHoraFin());
                    if (!ahora.isBefore(inicio) && ahora.isBefore(fin)) {
                        c.setEstado("EN_PROCESO");
                    }
                }
            }
        }
        return citas;
    }

    public void confirmar(UUID idCita) {
        Cita cita = obtenerCitaActiva(idCita);
        if (!"PENDIENTE".equals(cita.getEstado())) {
            throw new IllegalStateException("Solo se puede confirmar una cita en estado PENDIENTE");
        }
        cita.setEstado("CONFIRMADA");
        citaRepository.save(cita);
    }

    public void cancelar(UUID idCita) {
        Cita cita = obtenerCitaActiva(idCita);
        if ("COMPLETADA".equals(cita.getEstado()) || "CANCELADA".equals(cita.getEstado())) {
            throw new IllegalStateException("No se puede cancelar una cita ya finalizada o cancelada");
        }
        cita.setEstado("CANCELADA");
        citaRepository.save(cita);
    }

    public void finalizar(UUID idCita) {
        Cita cita = obtenerCitaActiva(idCita);

        boolean enCurso = "CONFIRMADA".equals(cita.getEstado())
                && cita.getFecha().isEqual(LocalDate.now())
                && !LocalDateTime.now().isBefore(LocalDateTime.of(cita.getFecha(), cita.getHoraInicio()));

        if (!enCurso) {
            throw new IllegalStateException("Solo se puede finalizar una cita que ya está en curso");
        }
        cita.setEstado("COMPLETADA");
        citaRepository.save(cita);
    }

    private Cita obtenerCitaActiva(UUID idCita) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
        if (Boolean.TRUE.equals(cita.getIsDeleted())) {
            throw new ResourceNotFoundException("Cita no encontrada");
        }
        return cita;
    }





public UUID crear(CitaCrearRequest request) {
    List<CitaDiaResponse> citasDelDia = citaRepository.listarPorBarberiaYFecha(request.getIdBarberia(), request.getFecha());

    boolean ocupado = citasDelDia.stream()
        .filter(c -> !"CANCELADA".equals(c.getEstado()))
        .anyMatch(c -> c.getIdBarbero().equals(request.getIdBarbero()) 
                && c.getHora().equals(request.getHoraInicio()));

    if (ocupado) {
        throw new IllegalStateException("El horario seleccionado ya no está disponible");
    }

    int duracion = request.getDuracionMinutos() != null ? request.getDuracionMinutos() : 60;

    Cita cita = new Cita();
   // cita.setId(UUID.randomUUID());
    cita.setIdUsuario(request.getIdCliente());
    cita.setIdBarberia(request.getIdBarberia());
    cita.setIdBarbero(request.getIdBarbero());
    cita.setIdServicio(request.getIdServicio());
    cita.setFecha(request.getFecha());
    cita.setHoraInicio(request.getHoraInicio());
    cita.setHoraFin(request.getHoraInicio().plusMinutes(duracion));
    cita.setEstado("PENDIENTE");
    cita.setIsDeleted(false);
    cita.setNotas(request.getNotas());

    Cita guardada = citaRepository.save(cita);
    return guardada.getId();
}
}