package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.EvaluacionDTO;
import org.smartbook.model.Curso;
import org.smartbook.model.Evaluacion;
import org.smartbook.repository.CursoRepository;
import org.smartbook.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final CursoRepository cursoRepository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public EvaluacionDTO createEvaluacion(EvaluacionDTO evaluacionDTO) {
        Curso curso = cursoRepository.findById(evaluacionDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + evaluacionDTO.getCursoId()));

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNombre(evaluacionDTO.getNombre());
        evaluacion.setDescripcion(evaluacionDTO.getDescripcion());
        evaluacion.setCurso(curso);
        evaluacion.setFecha(parseFecha(evaluacionDTO.getFecha()));
        evaluacion.setPuntajeMaximo(evaluacionDTO.getPuntajeMaximo());
        evaluacion.setCreatedAt(LocalDateTime.now());

        Evaluacion savedEvaluacion = evaluacionRepository.save(evaluacion);
        return convertToDTO(savedEvaluacion);
    }

    @Transactional(readOnly = true)
    public EvaluacionDTO getEvaluacionById(Integer id) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + id));
        return convertToDTO(evaluacion);
    }

    @Transactional(readOnly = true)
    public List<EvaluacionDTO> getAllEvaluaciones() {
        return evaluacionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EvaluacionDTO> getEvaluacionesByCurso(Integer cursoId) {
        return evaluacionRepository.findByCursoId(cursoId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EvaluacionDTO updateEvaluacion(Integer id, EvaluacionDTO evaluacionDTO) {
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + id));

        Curso curso = cursoRepository.findById(evaluacionDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + evaluacionDTO.getCursoId()));

        evaluacion.setNombre(evaluacionDTO.getNombre());
        evaluacion.setDescripcion(evaluacionDTO.getDescripcion());
        evaluacion.setCurso(curso);
        evaluacion.setFecha(parseFecha(evaluacionDTO.getFecha()));
        evaluacion.setPuntajeMaximo(evaluacionDTO.getPuntajeMaximo());

        Evaluacion updatedEvaluacion = evaluacionRepository.save(evaluacion);
        return convertToDTO(updatedEvaluacion);
    }

    private LocalDateTime parseFecha(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            return LocalDateTime.now();
        }
        String valor = fecha.trim();
        if (valor.length() <= 10) {
            return LocalDate.parse(valor, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        }
        return LocalDateTime.parse(valor, formatter);
    }

    public void deleteEvaluacion(Integer id) {
        if (!evaluacionRepository.existsById(id)) {
            throw new RuntimeException("Evaluación no encontrada con ID: " + id);
        }
        evaluacionRepository.deleteById(id);
    }

    private EvaluacionDTO convertToDTO(Evaluacion evaluacion) {
        EvaluacionDTO dto = new EvaluacionDTO();
        dto.setId(evaluacion.getId());
        dto.setNombre(evaluacion.getNombre());
        dto.setDescripcion(evaluacion.getDescripcion());
        dto.setCursoId(evaluacion.getCurso().getId());
        dto.setFecha(evaluacion.getFecha().format(formatter));
        dto.setPuntajeMaximo(evaluacion.getPuntajeMaximo());
        dto.setCreatedAt(evaluacion.getCreatedAt().format(formatter));
        return dto;
    }
}

