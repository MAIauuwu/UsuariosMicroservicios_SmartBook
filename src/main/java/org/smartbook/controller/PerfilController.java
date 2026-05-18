package org.smartbook.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.smartbook.dto.PerfilDTO;
import org.smartbook.service.PerfilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perfiles")
@AllArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping
    public ResponseEntity<PerfilDTO> createPerfil(@Valid @RequestBody PerfilDTO perfilDTO) {
        PerfilDTO createdPerfil = perfilService.createPerfil(perfilDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerfil);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> getPerfilById(@PathVariable Integer id) {
        PerfilDTO perfil = perfilService.getPerfilById(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<PerfilDTO> getPerfilByUserId(@PathVariable Integer userId) {
        PerfilDTO perfil = perfilService.getPerfilByUserId(userId);
        return ResponseEntity.ok(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilDTO> updatePerfil(@PathVariable Integer id, @Valid @RequestBody PerfilDTO perfilDTO) {
        PerfilDTO updatedPerfil = perfilService.updatePerfil(id, perfilDTO);
        return ResponseEntity.ok(updatedPerfil);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Integer id) {
        perfilService.deletePerfil(id);
        return ResponseEntity.noContent().build();
    }
}
