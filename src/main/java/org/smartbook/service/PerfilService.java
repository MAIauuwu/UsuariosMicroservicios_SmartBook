package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.PerfilDTO;
import org.smartbook.model.Perfil;
import org.smartbook.model.User;
import org.smartbook.repository.PerfilRepository;
import org.smartbook.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final UserRepository userRepository;

    public PerfilDTO createPerfil(PerfilDTO perfilDTO) {
        if (perfilRepository.existsByUserId(perfilDTO.getUserId())) {
            throw new RuntimeException("El usuario ya tiene un perfil creado");
        }

        User user = userRepository.findById(perfilDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + perfilDTO.getUserId()));

        Perfil perfil = new Perfil();
        perfil.setUser(user);
        perfil.setBiografia(perfilDTO.getBiografia());
        perfil.setFotoUrl(perfilDTO.getFotoUrl());
        perfil.setTelefono(perfilDTO.getTelefono());
        perfil.setDireccion(perfilDTO.getDireccion());
        perfil.setCiudad(perfilDTO.getCiudad());
        perfil.setPais(perfilDTO.getPais());
        perfil.setCreatedAt(LocalDateTime.now());

        Perfil savedPerfil = perfilRepository.save(perfil);
        return convertToDTO(savedPerfil);
    }

    @Transactional(readOnly = true)
    public PerfilDTO getPerfilById(Integer id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));
        return convertToDTO(perfil);
    }

    @Transactional(readOnly = true)
    public PerfilDTO getPerfilByUserId(Integer userId) {
        Perfil perfil = perfilRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado para el usuario con ID: " + userId));
        return convertToDTO(perfil);
    }

    public PerfilDTO updatePerfil(Integer id, PerfilDTO perfilDTO) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));

        perfil.setBiografia(perfilDTO.getBiografia());
        perfil.setFotoUrl(perfilDTO.getFotoUrl());
        perfil.setTelefono(perfilDTO.getTelefono());
        perfil.setDireccion(perfilDTO.getDireccion());
        perfil.setCiudad(perfilDTO.getCiudad());
        perfil.setPais(perfilDTO.getPais());
        perfil.setUpdatedAt(LocalDateTime.now());

        Perfil updatedPerfil = perfilRepository.save(perfil);
        return convertToDTO(updatedPerfil);
    }

    public void deletePerfil(Integer id) {
        if (!perfilRepository.existsById(id)) {
            throw new RuntimeException("Perfil no encontrado con ID: " + id);
        }
        perfilRepository.deleteById(id);
    }

    private PerfilDTO convertToDTO(Perfil perfil) {
        PerfilDTO dto = new PerfilDTO();
        dto.setId(perfil.getId());
        dto.setUserId(perfil.getUser().getId());
        dto.setBiografia(perfil.getBiografia());
        dto.setFotoUrl(perfil.getFotoUrl());
        dto.setTelefono(perfil.getTelefono());
        dto.setDireccion(perfil.getDireccion());
        dto.setCiudad(perfil.getCiudad());
        dto.setPais(perfil.getPais());
        dto.setCreatedAt(perfil.getCreatedAt() != null ? perfil.getCreatedAt().toString() : null);
        dto.setUpdatedAt(perfil.getUpdatedAt() != null ? perfil.getUpdatedAt().toString() : null);
        return dto;
    }
}
