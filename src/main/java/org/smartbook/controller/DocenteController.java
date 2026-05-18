package org.smartbook.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.smartbook.dto.DocenteDTO;
import org.smartbook.service.DocenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
@AllArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    @PostMapping
    public ResponseEntity<DocenteDTO> createDocente(@Valid @RequestBody DocenteDTO docenteDTO) {
        DocenteDTO createdDocente = docenteService.createDocente(docenteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getDocenteById(@PathVariable Integer id) {
        DocenteDTO docente = docenteService.getDocenteById(id);
        return ResponseEntity.ok(docente);
    }

    @GetMapping("/colegiatura/{numeroColegiatura}")
    public ResponseEntity<DocenteDTO> getDocenteByNumeroColegiatura(@PathVariable String numeroColegiatura) {
        DocenteDTO docente = docenteService.getDocenteByNumeroColegiatura(numeroColegiatura);
        return ResponseEntity.ok(docente);
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<DocenteDTO>> getDocentesByEspecialidad(@PathVariable String especialidad) {
        List<DocenteDTO> docentes = docenteService.getDocentesByEspecialidad(especialidad);
        return ResponseEntity.ok(docentes);
    }

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> getAllDocentes() {
        List<DocenteDTO> docentes = docenteService.getAllDocentes();
        return ResponseEntity.ok(docentes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> updateDocente(@PathVariable Integer id, @Valid @RequestBody DocenteDTO docenteDTO) {
        DocenteDTO updatedDocente = docenteService.updateDocente(id, docenteDTO);
        return ResponseEntity.ok(updatedDocente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Integer id) {
        docenteService.deleteDocente(id);
        return ResponseEntity.noContent().build();
    }
}
