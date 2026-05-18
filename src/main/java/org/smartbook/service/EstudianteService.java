package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.EstudianteDTO;
import org.smartbook.model.Curso;
import org.smartbook.model.Estudiante;
import org.smartbook.model.Role;
import org.smartbook.repository.CursoRepository;
import org.smartbook.repository.EstudianteRepository;
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
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final RoleRepository roleRepository;
    private final CursoRepository cursoRepository;
    private final PasswordEncoder passwordEncoder;

    public EstudianteDTO createEstudiante(EstudianteDTO estudianteDTO) {
        if (estudianteRepository.existsByMatricula(estudianteDTO.getMatricula())) {
            throw new RuntimeException("La matrícula ya existe: " + estudianteDTO.getMatricula());
        }

        Role role = roleRepository.findById(estudianteDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + estudianteDTO.getRolId()));

        Curso curso = cursoRepository.findById(estudianteDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + estudianteDTO.getCursoId()));

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setPassword(passwordEncoder.encode(estudianteDTO.getPassword()));
        estudiante.setRole(role);
        estudiante.setCurso(curso);
        estudiante.setMatricula(estudianteDTO.getMatricula());
        estudiante.setCreatedAt(LocalDateTime.now());

        Estudiante savedEstudiante = estudianteRepository.save(estudiante);
        return convertToDTO(savedEstudiante);
    }

    @Transactional(readOnly = true)
    public EstudianteDTO getEstudianteById(Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
        return convertToDTO(estudiante);
    }

    @Transactional(readOnly = true)
    public EstudianteDTO getEstudianteByMatricula(String matricula) {
        Estudiante estudiante = estudianteRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con matrícula: " + matricula));
        return convertToDTO(estudiante);
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> getEstudiantesByCurso(Integer cursoId) {
        return estudianteRepository.findByCursoId(cursoId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EstudianteDTO> getAllEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EstudianteDTO updateEstudiante(Integer id, EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        if (!estudiante.getMatricula().equals(estudianteDTO.getMatricula()) 
                && estudianteRepository.existsByMatricula(estudianteDTO.getMatricula())) {
            throw new RuntimeException("La matrícula ya existe: " + estudianteDTO.getMatricula());
        }

        Role role = roleRepository.findById(estudianteDTO.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + estudianteDTO.getRolId()));

        Curso curso = cursoRepository.findById(estudianteDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + estudianteDTO.getCursoId()));

        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setEmail(estudianteDTO.getEmail());
        if (estudianteDTO.getPassword() != null && !estudianteDTO.getPassword().isEmpty()) {
            estudiante.setPassword(passwordEncoder.encode(estudianteDTO.getPassword()));
        }
        estudiante.setRole(role);
        estudiante.setCurso(curso);
        estudiante.setMatricula(estudianteDTO.getMatricula());

        Estudiante updatedEstudiante = estudianteRepository.save(estudiante);
        return convertToDTO(updatedEstudiante);
    }

    public void deleteEstudiante(Integer id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setEmail(estudiante.getEmail());
        dto.setRolId(estudiante.getRole().getId());
        dto.setCursoId(estudiante.getCurso().getId());
        dto.setMatricula(estudiante.getMatricula());
        dto.setCreatedAt(estudiante.getCreatedAt().toString());
        return dto;
    }
}

