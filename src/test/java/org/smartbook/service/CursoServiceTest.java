package org.smartbook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smartbook.dto.CursoDTO;
import org.smartbook.model.Curso;
import org.smartbook.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void createCurso_Success() {
        CursoDTO dto = new CursoDTO(null, "Matemáticas", "Básico", 2024);
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Matemáticas");
        curso.setNivel("Básico");
        curso.setAnio(2024);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        CursoDTO result = cursoService.createCurso(dto);

        assertNotNull(result);
        assertEquals("Matemáticas", result.getNombre());
        verify(cursoRepository).save(any(Curso.class));
    }

    @Test
    void getCursoById_Success() {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Matemáticas");

        when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

        CursoDTO result = cursoService.getCursoById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void getAllCursos_Success() {
        when(cursoRepository.findAll()).thenReturn(List.of(new Curso(), new Curso()));

        List<CursoDTO> result = cursoService.getAllCursos();

        assertEquals(2, result.size());
    }

    @Test
    void deleteCurso_Success() {
        when(cursoRepository.existsById(1)).thenReturn(true);

        cursoService.deleteCurso(1);

        verify(cursoRepository).deleteById(1);
    }
}
