package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.EstudianteApoderadoDTO;
import org.smartbook.model.Apoderado;
import org.smartbook.model.Estudiante;
import org.smartbook.model.EstudianteApoderado;
import org.smartbook.repository.ApoderadoRepository;
import org.smartbook.repository.EstudianteApoderadoRepository;
import org.smartbook.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EstudianteApoderadoService {

    private final EstudianteApoderadoRepository estudianteApoderadoRepository;
    private final EstudianteRepository estudianteRepository;
    private final ApoderadoRepository apoderadoRepository;

    public EstudianteApoderadoDTO create(EstudianteApoderadoDTO dto) {
        if (estudianteApoderadoRepository.existsByEstudianteIdAndApoderadoId(
                dto.getEstudianteId(), dto.getApoderadoId())) {
            throw new RuntimeException(
                    "Ya existe una relacion entre el estudiante y el apoderado indicados");
        }

        estudianteRepository.findById(dto.getEstudianteId())
                .orElseThrow(() -> new RuntimeException(
                        "Estudiante no encontrado con ID: " + dto.getEstudianteId()));

        apoderadoRepository.findById(dto.getApoderadoId())
                .orElseThrow(() -> new RuntimeException(
                        "Apoderado no encontrado con ID: " + dto.getApoderadoId()));

        EstudianteApoderado relacion = new EstudianteApoderado();
        relacion.setEstudianteId(dto.getEstudianteId());
        relacion.setApoderadoId(dto.getApoderadoId());
        relacion.setParentesco(dto.getParentesco());

        EstudianteApoderado saved = estudianteApoderadoRepository.save(relacion);
        return convertToDTO(saved);
    }

    @Transactional(readOnly = true)
    public EstudianteApoderadoDTO getById(Integer id) {
        EstudianteApoderado relacion = estudianteApoderadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Relacion no encontrada con ID: " + id));
        return convertToDTO(relacion);
    }

    @Transactional(readOnly = true)
    public List<EstudianteApoderadoDTO> getAll() {
        return estudianteApoderadoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EstudianteApoderadoDTO> getByEstudianteId(Integer estudianteId) {
        return estudianteApoderadoRepository.findByEstudianteId(estudianteId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EstudianteApoderadoDTO> getByApoderadoId(Integer apoderadoId) {
        return estudianteApoderadoRepository.findByApoderadoId(apoderadoId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EstudianteApoderadoDTO update(Integer id, EstudianteApoderadoDTO dto) {
        EstudianteApoderado relacion = estudianteApoderadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Relacion no encontrada con ID: " + id));

        relacion.setParentesco(dto.getParentesco());

        EstudianteApoderado updated = estudianteApoderadoRepository.save(relacion);
        return convertToDTO(updated);
    }

    public void delete(Integer id) {
        if (!estudianteApoderadoRepository.existsById(id)) {
            throw new RuntimeException("Relacion no encontrada con ID: " + id);
        }
        estudianteApoderadoRepository.deleteById(id);
    }

    private EstudianteApoderadoDTO convertToDTO(EstudianteApoderado relacion) {
        EstudianteApoderadoDTO dto = new EstudianteApoderadoDTO();
        dto.setId(relacion.getId());
        dto.setEstudianteId(relacion.getEstudianteId());
        dto.setApoderadoId(relacion.getApoderadoId());
        dto.setParentesco(relacion.getParentesco());

        estudianteRepository.findById(relacion.getEstudianteId())
                .ifPresent(e -> {
                    dto.setEstudianteNombre(e.getNombre());
                    dto.setEstudianteApellido(e.getApellido());
                });

        apoderadoRepository.findById(relacion.getApoderadoId())
                .ifPresent(a -> {
                    dto.setApoderadoNombre(a.getNombre());
                    dto.setApoderadoApellido(a.getApellido());
                    dto.setApoderadoEmail(a.getEmail());
                });

        return dto;
    }
}
