package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.CursoDTO;
import org.smartbook.model.Curso;
import org.smartbook.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoDTO createCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setNombre(cursoDTO.getNombre());
        curso.setNivel(cursoDTO.getNivel());
        curso.setAnio(cursoDTO.getAnio());
        
        Curso savedCurso = cursoRepository.save(curso);
        return convertToDTO(savedCurso);
    }

    @Transactional(readOnly = true)
    public CursoDTO getCursoById(Integer id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
        return convertToDTO(curso);
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> getAllCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CursoDTO> getCursosByAnio(Integer anio) {
        return cursoRepository.findByAnio(anio)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CursoDTO updateCurso(Integer id, CursoDTO cursoDTO) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
        
        curso.setNombre(cursoDTO.getNombre());
        curso.setNivel(cursoDTO.getNivel());
        curso.setAnio(cursoDTO.getAnio());
        
        Curso updatedCurso = cursoRepository.save(curso);
        return convertToDTO(updatedCurso);
    }

    public void deleteCurso(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }

    private CursoDTO convertToDTO(Curso curso) {
        return new CursoDTO(curso.getId(), curso.getNombre(), curso.getNivel(), curso.getAnio());
    }
}

