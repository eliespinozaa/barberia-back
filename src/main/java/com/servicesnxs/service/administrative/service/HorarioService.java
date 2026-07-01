package com.servicesnxs.service.administrative.service;
 
import com.servicesnxs.service.administrative.dto.HorarioRequestDTO;
import com.servicesnxs.service.administrative.dto.HorarioResponseDTO;
import com.servicesnxs.service.administrative.model.Barberia;
import com.servicesnxs.service.administrative.model.HorarioBarberia;
import com.servicesnxs.service.administrative.repository.BarberiaRepository;
import com.servicesnxs.service.administrative.repository.HorarioRepository;
import org.springframework.stereotype.Service;
 
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
 
@Service
public class HorarioService {
 
    private final HorarioRepository  horarioRepository;
    private final BarberiaRepository barberiaRepository;
 
    public HorarioService(HorarioRepository horarioRepository,
                          BarberiaRepository barberiaRepository) {
        this.horarioRepository  = horarioRepository;
        this.barberiaRepository = barberiaRepository;
    }
 
    // ── Listar horarios de una barbería ──
    public List<HorarioResponseDTO> listarPorBarberia(UUID idBarberia) {
        return horarioRepository.findByBarberia(idBarberia)
                .stream()
                .map(this::mapear)
                .collect(Collectors.toList());
    }
 
    // ── Crear (o actualizar si ya existe ese día) ──
    public HorarioResponseDTO crear(HorarioRequestDTO dto) {
        UUID idBarberia = UUID.fromString(dto.getIdBarberia());
 
        return horarioRepository
                .findByBarberiaAndDia(idBarberia, dto.getDiaSemana())
                .map(existente -> actualizarEntidad(existente, dto))
                .orElseGet(() -> crearNuevo(idBarberia, dto));
    }
 
    public HorarioResponseDTO actualizar(UUID id, HorarioRequestDTO dto) {
        HorarioBarberia h = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        return actualizarEntidad(h, dto);
    }
 
    public void eliminar(UUID id) {
        HorarioBarberia h = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
 
        OffsetDateTime ahora = OffsetDateTime.now();
        h.setIsDeleted(true);
        h.setDeletedAt(ahora);
        h.setUpdatedAt(ahora);
        h.setUpdatedBy("system");
        horarioRepository.save(h);
    }
 
 
  private HorarioResponseDTO crearNuevo(UUID idBarberia, HorarioRequestDTO dto) {
    Barberia barberia = barberiaRepository.findById(idBarberia)
            .orElseThrow(() -> new RuntimeException("Barbería no encontrada"));

    boolean abierto = dto.getEstado() == null || dto.getEstado() == 1;

    String apertura = (dto.getHoraApertura() != null && !dto.getHoraApertura().isBlank())
            ? dto.getHoraApertura() : "08:00";
    String cierre   = (dto.getHoraCierre()   != null && !dto.getHoraCierre().isBlank())
            ? dto.getHoraCierre()   : "18:00";

    OffsetDateTime ahora = OffsetDateTime.now();
    HorarioBarberia h = new HorarioBarberia();
    h.setBarberia(barberia);
    h.setDiaSemana(dto.getDiaSemana());
    h.setHoraApertura(LocalTime.parse(apertura));
    h.setHoraCierre(LocalTime.parse(cierre));
    h.setEstado(dto.getEstado() != null ? dto.getEstado() : 1);
    h.setCreatedAt(ahora);
    h.setUpdatedAt(ahora);
    h.setCreatedBy("system");
    h.setIsDeleted(false);

    return mapear(horarioRepository.save(h));
}
 

private HorarioResponseDTO actualizarEntidad(HorarioBarberia h, HorarioRequestDTO dto) {
    boolean abierto = dto.getEstado() == null || dto.getEstado() == 1;

    if (abierto) {
        h.setHoraApertura(LocalTime.parse(dto.getHoraApertura()));
        h.setHoraCierre(LocalTime.parse(dto.getHoraCierre()));
    }
    if (dto.getEstado() != null) h.setEstado(dto.getEstado());
    h.setUpdatedAt(OffsetDateTime.now());
    h.setUpdatedBy("system");
    return mapear(horarioRepository.save(h));
}
 
    private HorarioResponseDTO mapear(HorarioBarberia h) {
        return new HorarioResponseDTO(
                h.getIdHorarioBarberia(),
                h.getBarberia() != null ? h.getBarberia().getIdBarberia() : null,
                h.getDiaSemana(),
                h.getHoraApertura() != null ? h.getHoraApertura().toString() : null,
                h.getHoraCierre()   != null ? h.getHoraCierre().toString()   : null,
                h.getEstado()
        );
    }
}
 
