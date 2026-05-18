package org.smartbook.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.smartbook.dto.EvaluacionDTO;
import org.smartbook.service.EvaluacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@AllArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    @PostMapping
    public ResponseEntity<EvaluacionDTO> createEvaluacion(@Valid @RequestBody EvaluacionDTO evaluacionDTO) {
        EvaluacionDTO createdEvaluacion = evaluacionService.createEvaluacion(evaluacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvaluacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> getEvaluacionById(@PathVariable Integer id) {
        EvaluacionDTO evaluacion = evaluacionService.getEvaluacionById(id);
        return ResponseEntity.ok(evaluacion);
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionDTO>> getAllEvaluaciones() {
        List<EvaluacionDTO> evaluaciones = evaluacionService.getAllEvaluaciones();
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<EvaluacionDTO>> getEvaluacionesByCurso(@PathVariable Integer cursoId) {
        List<EvaluacionDTO> evaluaciones = evaluacionService.getEvaluacionesByCurso(cursoId);
        return ResponseEntity.ok(evaluaciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> updateEvaluacion(@PathVariable Integer id, @Valid @RequestBody EvaluacionDTO evaluacionDTO) {
        EvaluacionDTO updatedEvaluacion = evaluacionService.updateEvaluacion(id, evaluacionDTO);
        return ResponseEntity.ok(updatedEvaluacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable Integer id) {
        evaluacionService.deleteEvaluacion(id);
        return ResponseEntity.noContent().build();
    }
}

