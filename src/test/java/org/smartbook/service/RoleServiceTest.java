package org.smartbook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smartbook.dto.RoleDTO;
import org.smartbook.model.Role;
import org.smartbook.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void createRole_Success() {
        RoleDTO dto = new RoleDTO(null, "ADMIN");
        Role role = new Role();
        role.setId(1);
        role.setNombre("ADMIN");

        when(roleRepository.save(any(Role.class))).thenReturn(role);

        RoleDTO result = roleService.createRole(dto);

        assertNotNull(result);
        assertEquals("ADMIN", result.getNombre());
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void getRoleById_Success() {
        Role role = new Role();
        role.setId(1);
        role.setNombre("ADMIN");

        when(roleRepository.findById(1)).thenReturn(Optional.of(role));

        RoleDTO result = roleService.getRoleById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("ADMIN", result.getNombre());
    }

    @Test
    void getRoleById_NotFound() {
        when(roleRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> roleService.getRoleById(99));
    }

    @Test
    void getAllRoles_Success() {
        Role r1 = new Role();
        r1.setId(1);
        r1.setNombre("ADMIN");
        Role r2 = new Role();
        r2.setId(2);
        r2.setNombre("USER");

        when(roleRepository.findAll()).thenReturn(List.of(r1, r2));

        List<RoleDTO> result = roleService.getAllRoles();

        assertEquals(2, result.size());
    }

    @Test
    void deleteRole_Success() {
        when(roleRepository.existsById(1)).thenReturn(true);

        roleService.deleteRole(1);

        verify(roleRepository).deleteById(1);
    }

    @Test
    void deleteRole_NotFound() {
        when(roleRepository.existsById(99)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> roleService.deleteRole(99));
    }
}
