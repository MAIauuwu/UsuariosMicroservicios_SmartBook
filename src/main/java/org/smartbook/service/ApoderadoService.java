package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.ApoderadoDTO;
import org.smartbook.model.Apoderado;
import org.smartbook.model.Role;
import org.smartbook.repository.ApoderadoRepository;
import org.smartbook.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ApoderadoService {

    private final ApoderadoRepository apoderadoRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApoderadoDTO createApoderado(ApoderadoDTO apoderadoDTO) {
        if (apoderadoRepository.existsByDocumentoIdentidad(apoderadoDTO.getDocumentoIdentidad())) {
            throw new RuntimeException("El documento de identidad ya existe: " + apoderadoDTO.getDocumentoIdentidad());
        }

        Role role = roleRepository.findById(apoderadoDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + apoderadoDTO.getRolId()));

        Apoderado apoderado = new Apoderado();
        apoderado.setNombre(apoderadoDTO.getNombre());
        apoderado.setApellido(apoderadoDTO.getApellido());
        apoderado.setEmail(apoderadoDTO.getEmail());
        apoderado.setPassword(passwordEncoder.encode(apoderadoDTO.getPassword()));
        apoderado.setRole(role);
        apoderado.setParentesco(apoderadoDTO.getParentesco());
        apoderado.setTelefono(apoderadoDTO.getTelefono());
        apoderado.setDireccion(apoderadoDTO.getDireccion());
        apoderado.setDocumentoIdentidad(apoderadoDTO.getDocumentoIdentidad());
        apoderado.setCreatedAt(LocalDateTime.now());

        Apoderado savedApoderado = apoderadoRepository.save(apoderado);
        return convertToDTO(savedApoderado);
    }

    @Transactional(readOnly = true)
    public ApoderadoDTO getApoderadoById(Integer id) {
        Apoderado apoderado = apoderadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apoderado no encontrado con ID: " + id));
        return convertToDTO(apoderado);
    }

    @Transactional(readOnly = true)
    public ApoderadoDTO getApoderadoByDocumentoIdentidad(String documentoIdentidad) {
        Apoderado apoderado = apoderadoRepository.findByDocumentoIdentidad(documentoIdentidad)
                .orElseThrow(() -> new RuntimeException("Apoderado no encontrado con documento: " + documentoIdentidad));
        return convertToDTO(apoderado);
    }

    @Transactional(readOnly = true)
    public List<ApoderadoDTO> getApoderadosByParentesco(String parentesco) {
        return apoderadoRepository.findByParentesco(parentesco)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApoderadoDTO> getAllApoderados() {
        return apoderadoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ApoderadoDTO updateApoderado(Integer id, ApoderadoDTO apoderadoDTO) {
        Apoderado apoderado = apoderadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apoderado no encontrado con ID: " + id));

        if (!apoderado.getDocumentoIdentidad().equals(apoderadoDTO.getDocumentoIdentidad())
                && apoderadoRepository.existsByDocumentoIdentidad(apoderadoDTO.getDocumentoIdentidad())) {
            throw new RuntimeException("El documento de identidad ya existe: " + apoderadoDTO.getDocumentoIdentidad());
        }

        Role role = roleRepository.findById(apoderadoDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + apoderadoDTO.getRolId()));

        apoderado.setNombre(apoderadoDTO.getNombre());
        apoderado.setApellido(apoderadoDTO.getApellido());
        apoderado.setEmail(apoderadoDTO.getEmail());
        if (apoderadoDTO.getPassword() != null && !apoderadoDTO.getPassword().isEmpty()) {
            apoderado.setPassword(passwordEncoder.encode(apoderadoDTO.getPassword()));
        }
        apoderado.setRole(role);
        apoderado.setParentesco(apoderadoDTO.getParentesco());
        apoderado.setTelefono(apoderadoDTO.getTelefono());
        apoderado.setDireccion(apoderadoDTO.getDireccion());
        apoderado.setDocumentoIdentidad(apoderadoDTO.getDocumentoIdentidad());

        Apoderado updatedApoderado = apoderadoRepository.save(apoderado);
        return convertToDTO(updatedApoderado);
    }

    public void deleteApoderado(Integer id) {
        if (!apoderadoRepository.existsById(id)) {
            throw new RuntimeException("Apoderado no encontrado con ID: " + id);
        }
        apoderadoRepository.deleteById(id);
    }

    private ApoderadoDTO convertToDTO(Apoderado apoderado) {
        ApoderadoDTO dto = new ApoderadoDTO();
        dto.setId(apoderado.getId());
        dto.setNombre(apoderado.getNombre());
        dto.setApellido(apoderado.getApellido());
        dto.setEmail(apoderado.getEmail());
        dto.setRolId(apoderado.getRole().getId());
        dto.setParentesco(apoderado.getParentesco());
        dto.setTelefono(apoderado.getTelefono());
        dto.setDireccion(apoderado.getDireccion());
        dto.setDocumentoIdentidad(apoderado.getDocumentoIdentidad());
        dto.setCreatedAt(apoderado.getCreatedAt().toString());
        return dto;
    }
}
