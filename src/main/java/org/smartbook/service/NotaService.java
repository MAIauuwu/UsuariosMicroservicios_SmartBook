package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.NotaDTO;
import org.smartbook.model.Evaluacion;
import org.smartbook.model.Estudiante;
import org.smartbook.model.Nota;
import org.smartbook.repository.EvaluacionRepository;
import org.smartbook.repository.EstudianteRepository;
import org.smartbook.repository.NotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class NotaService {

    private final NotaRepository notaRepository;
    private final EstudianteRepository estudianteRepository;
    private final EvaluacionRepository evaluacionRepository;

    public NotaDTO createNota(NotaDTO notaDTO) {
        Estudiante estudiante = estudianteRepository.findById(notaDTO.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + notaDTO.getEstudianteId()));

        Evaluacion evaluacion = evaluacionRepository.findById(notaDTO.getEvaluacionId())
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + notaDTO.getEvaluacionId()));

        Nota nota = new Nota();
        nota.setEstudiante(estudiante);
        nota.setEvaluacion(evaluacion);
        nota.setNota(notaDTO.getNota());

        Nota savedNota = notaRepository.save(nota);
        return convertToDTO(savedNota);
    }

    @Transactional(readOnly = true)
    public NotaDTO getNotaById(Integer id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con ID: " + id));
        return convertToDTO(nota);
    }

    @Transactional(readOnly = true)
    public List<NotaDTO> getAllNotas() {
        return notaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotaDTO> getNotasByEstudiante(Integer estudianteId) {
        return notaRepository.findByEstudianteId(estudianteId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotaDTO> getNotasByEvaluacion(Integer evaluacionId) {
        return notaRepository.findByEvaluacionId(evaluacionId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotaDTO updateNota(Integer id, NotaDTO notaDTO) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con ID: " + id));

        Estudiante estudiante = estudianteRepository.findById(notaDTO.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + notaDTO.getEstudianteId()));

        Evaluacion evaluacion = evaluacionRepository.findById(notaDTO.getEvaluacionId())
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + notaDTO.getEvaluacionId()));

        nota.setEstudiante(estudiante);
        nota.setEvaluacion(evaluacion);
        nota.setNota(notaDTO.getNota());

        Nota updatedNota = notaRepository.save(nota);
        return convertToDTO(updatedNota);
    }

    public void deleteNota(Integer id) {
        if (!notaRepository.existsById(id)) {
            throw new RuntimeException("Nota no encontrada con ID: " + id);
        }
        notaRepository.deleteById(id);
    }

    private NotaDTO convertToDTO(Nota nota) {
        return new NotaDTO(
                nota.getId(),
                nota.getEstudiante().getId(),
                nota.getEvaluacion().getId(),
                nota.getNota()
        );
    }
}

