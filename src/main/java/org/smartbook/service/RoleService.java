package org.smartbook.service;

import lombok.AllArgsConstructor;
import org.smartbook.dto.RoleDTO;
import org.smartbook.model.Role;
import org.smartbook.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setNombre(roleDTO.getNombre());
        Role savedRole = roleRepository.save(role);
        return convertToDTO(savedRole);
    }

    @Transactional(readOnly = true)
    public RoleDTO getRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
        return convertToDTO(role);
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO updateRole(Integer id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
        role.setNombre(roleDTO.getNombre());
        Role updatedRole = roleRepository.save(role);
        return convertToDTO(updatedRole);
    }

    public void deleteRole(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado con ID: " + id);
        }
        roleRepository.deleteById(id);
    }

    private RoleDTO convertToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getNombre());
    }
}

