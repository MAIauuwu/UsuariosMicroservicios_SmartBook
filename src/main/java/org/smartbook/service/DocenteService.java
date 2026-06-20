package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.DocenteDTO;
import org.smartbook.model.Docente;
import org.smartbook.model.Role;
import org.smartbook.repository.DocenteRepository;
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
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DocenteDTO createDocente(DocenteDTO docenteDTO) {
        if (docenteRepository.existsByNumeroColegiatura(docenteDTO.getNumeroColegiatura())) {
            throw new RuntimeException("El número de colegiatura ya existe: " + docenteDTO.getNumeroColegiatura());
        }

        Role role = roleRepository.findById(docenteDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + docenteDTO.getRolId()));

        Docente docente = new Docente();
        docente.setNombre(docenteDTO.getNombre());
        docente.setApellido(docenteDTO.getApellido());
        docente.setEmail(docenteDTO.getEmail());
        docente.setPassword(passwordEncoder.encode(docenteDTO.getPassword()));
        docente.setRole(role);
        docente.setNumeroColegiatura(docenteDTO.getNumeroColegiatura());
        docente.setEspecialidad(docenteDTO.getEspecialidad());
        docente.setGradoAcademico(docenteDTO.getGradoAcademico());
        docente.setCreatedAt(LocalDateTime.now());

        Docente savedDocente = docenteRepository.save(docente);
        return convertToDTO(savedDocente);
    }

    @Transactional(readOnly = true)
    public DocenteDTO getDocenteById(Integer id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));
        return convertToDTO(docente);
    }

    @Transactional(readOnly = true)
    public DocenteDTO getDocenteByNumeroColegiatura(String numeroColegiatura) {
        Docente docente = docenteRepository.findByNumeroColegiatura(numeroColegiatura)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con colegiatura: " + numeroColegiatura));
        return convertToDTO(docente);
    }

    @Transactional(readOnly = true)
    public List<DocenteDTO> getDocentesByEspecialidad(String especialidad) {
        return docenteRepository.findByEspecialidad(especialidad)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DocenteDTO> getAllDocentes() {
        return docenteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DocenteDTO updateDocente(Integer id, DocenteDTO docenteDTO) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con ID: " + id));

        if (!docente.getNumeroColegiatura().equals(docenteDTO.getNumeroColegiatura())
                && docenteRepository.existsByNumeroColegiatura(docenteDTO.getNumeroColegiatura())) {
            throw new RuntimeException("El número de colegiatura ya existe: " + docenteDTO.getNumeroColegiatura());
        }

        Role role = roleRepository.findById(docenteDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + docenteDTO.getRolId()));

        docente.setNombre(docenteDTO.getNombre());
        docente.setApellido(docenteDTO.getApellido());
        docente.setEmail(docenteDTO.getEmail());
        if (docenteDTO.getPassword() != null && !docenteDTO.getPassword().isEmpty()) {
            docente.setPassword(passwordEncoder.encode(docenteDTO.getPassword()));
        }
        docente.setRole(role);
        docente.setNumeroColegiatura(docenteDTO.getNumeroColegiatura());
        docente.setEspecialidad(docenteDTO.getEspecialidad());
        docente.setGradoAcademico(docenteDTO.getGradoAcademico());

        Docente updatedDocente = docenteRepository.save(docente);
        return convertToDTO(updatedDocente);
    }

    public void deleteDocente(Integer id) {
        if (!docenteRepository.existsById(id)) {
            throw new RuntimeException("Docente no encontrado con ID: " + id);
        }
        docenteRepository.deleteById(id);
    }

    private DocenteDTO convertToDTO(Docente docente) {
        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setNombre(docente.getNombre());
        dto.setApellido(docente.getApellido());
        dto.setEmail(docente.getEmail());
        dto.setRolId(docente.getRole().getId());
        dto.setNumeroColegiatura(docente.getNumeroColegiatura());
        dto.setEspecialidad(docente.getEspecialidad());
        dto.setGradoAcademico(docente.getGradoAcademico());
        dto.setCreatedAt(docente.getCreatedAt().toString());
        return dto;
    }
}
