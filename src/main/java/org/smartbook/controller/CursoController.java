package org.smartbook.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.smartbook.dto.CursoDTO;
import org.smartbook.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@Valid @RequestBody CursoDTO cursoDTO) {
        CursoDTO createdCurso = cursoService.createCurso(cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCurso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Integer id) {
        CursoDTO curso = cursoService.getCursoById(id);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        List<CursoDTO> cursos = cursoService.getAllCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<CursoDTO>> getCursosByAnio(@PathVariable Integer anio) {
        List<CursoDTO> cursos = cursoService.getCursosByAnio(anio);
        return ResponseEntity.ok(cursos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@PathVariable Integer id, @Valid @RequestBody CursoDTO cursoDTO) {
        CursoDTO updatedCurso = cursoService.updateCurso(id, cursoDTO);
        return ResponseEntity.ok(updatedCurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}

