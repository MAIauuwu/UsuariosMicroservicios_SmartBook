package org.smartbook.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.smartbook.dto.NotaDTO;
import org.smartbook.service.NotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
@AllArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @PostMapping
    public ResponseEntity<NotaDTO> createNota(@Valid @RequestBody NotaDTO notaDTO) {
        NotaDTO createdNota = notaService.createNota(notaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNota);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> getNotaById(@PathVariable Integer id) {
        NotaDTO nota = notaService.getNotaById(id);
        return ResponseEntity.ok(nota);
    }

    @GetMapping
    public ResponseEntity<List<NotaDTO>> getAllNotas() {
        List<NotaDTO> notas = notaService.getAllNotas();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<NotaDTO>> getNotasByEstudiante(@PathVariable Integer estudianteId) {
        List<NotaDTO> notas = notaService.getNotasByEstudiante(estudianteId);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/evaluacion/{evaluacionId}")
    public ResponseEntity<List<NotaDTO>> getNotasByEvaluacion(@PathVariable Integer evaluacionId) {
        List<NotaDTO> notas = notaService.getNotasByEvaluacion(evaluacionId);
        return ResponseEntity.ok(notas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Integer id, @Valid @RequestBody NotaDTO notaDTO) {
        NotaDTO updatedNota = notaService.updateNota(id, notaDTO);
        return ResponseEntity.ok(updatedNota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Integer id) {
        notaService.deleteNota(id);
        return ResponseEntity.noContent().build();
    }
}

