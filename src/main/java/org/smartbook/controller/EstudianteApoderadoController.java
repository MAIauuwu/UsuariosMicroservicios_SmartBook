package org.smartbook.controller;

import lombok.AllArgsConstructor;
import org.smartbook.dto.EstudianteApoderadoDTO;
import org.smartbook.service.EstudianteApoderadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante-apoderado")
@AllArgsConstructor
public class EstudianteApoderadoController {

    private final EstudianteApoderadoService estudianteApoderadoService;

    @PostMapping
    public ResponseEntity<EstudianteApoderadoDTO> create(@RequestBody EstudianteApoderadoDTO dto) {
        EstudianteApoderadoDTO created = estudianteApoderadoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteApoderadoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(estudianteApoderadoService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EstudianteApoderadoDTO>> getAll() {
        return ResponseEntity.ok(estudianteApoderadoService.getAll());
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<EstudianteApoderadoDTO>> getByEstudianteId(@PathVariable Integer estudianteId) {
        return ResponseEntity.ok(estudianteApoderadoService.getByEstudianteId(estudianteId));
    }

    @GetMapping("/apoderado/{apoderadoId}")
    public ResponseEntity<List<EstudianteApoderadoDTO>> getByApoderadoId(@PathVariable Integer apoderadoId) {
        return ResponseEntity.ok(estudianteApoderadoService.getByApoderadoId(apoderadoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteApoderadoDTO> update(@PathVariable Integer id, @RequestBody EstudianteApoderadoDTO dto) {
        return ResponseEntity.ok(estudianteApoderadoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        estudianteApoderadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
