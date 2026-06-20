package org.smartbook.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.smartbook.dto.ApoderadoDTO;
import org.smartbook.service.ApoderadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apoderados")
@AllArgsConstructor
public class ApoderadoController {

    private final ApoderadoService apoderadoService;

    @PostMapping
    public ResponseEntity<ApoderadoDTO> createApoderado(@Valid @RequestBody ApoderadoDTO apoderadoDTO) {
        ApoderadoDTO createdApoderado = apoderadoService.createApoderado(apoderadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApoderado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> getApoderadoById(@PathVariable Integer id) {
        ApoderadoDTO apoderado = apoderadoService.getApoderadoById(id);
        return ResponseEntity.ok(apoderado);
    }

    @GetMapping("/documento/{documentoIdentidad}")
    public ResponseEntity<ApoderadoDTO> getApoderadoByDocumentoIdentidad(@PathVariable String documentoIdentidad) {
        ApoderadoDTO apoderado = apoderadoService.getApoderadoByDocumentoIdentidad(documentoIdentidad);
        return ResponseEntity.ok(apoderado);
    }

    @GetMapping("/parentesco/{parentesco}")
    public ResponseEntity<List<ApoderadoDTO>> getApoderadosByParentesco(@PathVariable String parentesco) {
        List<ApoderadoDTO> apoderados = apoderadoService.getApoderadosByParentesco(parentesco);
        return ResponseEntity.ok(apoderados);
    }

    @GetMapping
    public ResponseEntity<List<ApoderadoDTO>> getAllApoderados() {
        List<ApoderadoDTO> apoderados = apoderadoService.getAllApoderados();
        return ResponseEntity.ok(apoderados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApoderadoDTO> updateApoderado(@PathVariable Integer id, @Valid @RequestBody ApoderadoDTO apoderadoDTO) {
        ApoderadoDTO updatedApoderado = apoderadoService.updateApoderado(id, apoderadoDTO);
        return ResponseEntity.ok(updatedApoderado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApoderado(@PathVariable Integer id) {
        apoderadoService.deleteApoderado(id);
        return ResponseEntity.noContent().build();
    }
}
