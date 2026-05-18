package org.smartbook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.smartbook.dto.UserDTO;
import org.smartbook.model.Role;
import org.smartbook.model.User;
import org.smartbook.repository.RoleRepository;
import org.smartbook.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        when(passwordEncoder.encode(anyString())).thenReturn("encoded-password");
    }

    @Test
    void createUser_Success() {
        UserDTO dto = new UserDTO();
        dto.setNombre("Juan");
        dto.setApellido("Pérez");
        dto.setEmail("juan@example.com");
        dto.setPassword("password123");
        dto.setRolId(1);

        when(userRepository.existsByEmail("juan@example.com")).thenReturn(false);

        Role role = new Role();
        role.setId(1);
        role.setNombre("USUARIO");
        when(roleRepository.findById(1)).thenReturn(Optional.of(role));

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setEmail("juan@example.com");
        savedUser.setCreatedAt(LocalDateTime.now());
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDTO result = userService.createUser(dto);

        assertNotNull(result);
        assertEquals("juan@example.com", result.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_EmailAlreadyExists() {
        UserDTO dto = new UserDTO();
        dto.setEmail("existing@example.com");

        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.createUser(dto));
    }

    @Test
    void getUserById_Success() {
        User user = new User();
        user.setId(1);
        user.setEmail("juan@example.com");
        user.setCreatedAt(LocalDateTime.now());

        Role role = new Role();
        role.setId(1);
        user.setRole(role);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals("juan@example.com", result.getEmail());
    }
}
