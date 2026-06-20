package org.smartbook.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.smartbook.dto.EstudianteDTO;
import org.smartbook.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@AllArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<EstudianteDTO> createEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO createdEstudiante = estudianteService.createEstudiante(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEstudiante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Integer id) {
        EstudianteDTO estudiante = estudianteService.getEstudianteById(id);
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<EstudianteDTO> getEstudianteByMatricula(@PathVariable String matricula) {
        EstudianteDTO estudiante = estudianteService.getEstudianteByMatricula(matricula);
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByCurso(@PathVariable Integer cursoId) {
        List<EstudianteDTO> estudiantes = estudianteService.getEstudiantesByCurso(cursoId);
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.getAllEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(@PathVariable Integer id, @Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO updatedEstudiante = estudianteService.updateEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(updatedEstudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Integer id) {
        estudianteService.deleteEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}

