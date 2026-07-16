package com.servicesnxs.service.administrative.service;
 
import com.servicesnxs.service.administrative.dto.ResenaResumenDTO;
import com.servicesnxs.service.administrative.dto.ResenaCrearRequest;
import com.servicesnxs.service.administrative.dto.ResenaResponseDTO;
import com.servicesnxs.service.administrative.model.Resena;
import com.servicesnxs.service.administrative.model.Usuario;
import com.servicesnxs.service.administrative.repository.ResenaRepository;
import com.servicesnxs.service.administrative.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
 
@Service
public class ResenaService {
 
    private final ResenaRepository  resenaRepository;
    private final UsuarioRepository usuarioRepository;
 
    public ResenaService(ResenaRepository resenaRepository,
                         UsuarioRepository usuarioRepository) {
        this.resenaRepository  = resenaRepository;
        this.usuarioRepository = usuarioRepository;
    }
 
    // ── Listar todas las reseñas de una barbería ──
    public List<ResenaResponseDTO> listarPorBarberia(UUID idBarberia) {
        return resenaRepository.findByBarberia(idBarberia)
                .stream()
                .map(this::mapear)
                .collect(Collectors.toList());
    }
 
    // ── Resumen: promedio, total y desglose por estrellas ──
    public ResenaResumenDTO resumen(UUID idBarberia) {
        Double promedio = resenaRepository.promedioCalificacion(idBarberia);
        Long   total    = resenaRepository.totalResenas(idBarberia);
 
        if (promedio == null) promedio = 0.0;
        if (total    == null) total    = 0L;
 
        // Desglose por calificación
        List<Object[]> rawDesglose = resenaRepository.countPorCalificacion(idBarberia);
        List<ResenaResumenDTO.DesgloseFila> desglose = new ArrayList<>();
 
        for (Object[] fila : rawDesglose) {
            Integer estrellas  = (Integer) fila[0];
            Long    cantidad   = (Long)    fila[1];
            Double  porcentaje = total > 0
                    ? Math.round((cantidad * 100.0 / total) * 10.0) / 10.0
                    : 0.0;
            desglose.add(new ResenaResumenDTO.DesgloseFila(estrellas, cantidad, porcentaje));
        }
 
        // Redondear el promedio a 1 decimal
        promedio = Math.round(promedio * 10.0) / 10.0;
 
        return new ResenaResumenDTO(promedio, total, desglose);
    }
 
    // ── Privados ──
    private ResenaResponseDTO mapear(Resena r) {
        ResenaResponseDTO dto = new ResenaResponseDTO();
        dto.setId(r.getId());
        dto.setIdUsuario(r.getIdUsuario());
        dto.setIdBarberia(r.getIdBarberia());
        dto.setIdCita(r.getIdCita());
        dto.setCalificacion(r.getCalificacion());
        dto.setComentario(r.getComentario());
        dto.setEstado(r.getEstado());
        dto.setCreatedAt(r.getCreatedAt());
 
        if (r.getIdUsuario() != null) {
            usuarioRepository.findById(r.getIdUsuario()).ifPresent(u ->
                dto.setNombreUsuario(u.getNombre() + " " + u.getApellido())
            );
        }
 
        return dto;
    }

    public boolean existePorCita(UUID idCita) {
        return resenaRepository.existsPorCita(idCita);
    }

    public ResenaResponseDTO crear(ResenaCrearRequest request) {
        if (resenaRepository.existsPorCita(request.getIdCita())) {
            throw new IllegalStateException("Ya existe una reseña para esta cita");
        }

        OffsetDateTime ahora = OffsetDateTime.now();

        Resena r = new Resena();
        r.setIdUsuario(request.getIdUsuario());
        r.setIdBarberia(request.getIdBarberia());
        r.setIdCita(request.getIdCita());
        r.setCalificacion(request.getCalificacion());
        r.setComentario(request.getComentario());
        r.setEstado((short) 1);
        r.setIsDeleted(false);
        r.setCreatedAt(ahora);
        r.setUpdatedAt(ahora);  
        r.setCreatedBy("system");

        Resena guardada = resenaRepository.save(r);
        return mapear(guardada);
    }
}
