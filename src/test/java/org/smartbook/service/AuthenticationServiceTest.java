package org.smartbook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        when(jwtService.generateToken(anyString())).thenReturn("mock-jwt-token");
        when(passwordEncoder.encode(anyString())).thenReturn("encoded-password");
    }

    @Test
    void register_Success() {
        AuthenticationService.RegisterRequest request = new AuthenticationService.RegisterRequest();
        request.setNombre("Juan");
        request.setApellido("Pérez");
        request.setEmail("juan@example.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail("juan@example.com")).thenReturn(false);

        Role existingRole = new Role();
        existingRole.setId(1);
        existingRole.setNombre("USUARIO");
        when(roleRepository.findByNombre("USUARIO")).thenReturn(Optional.of(existingRole));

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setEmail("juan@example.com");
        savedUser.setCreatedAt(LocalDateTime.now());
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        var response = authenticationService.register(request);

        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());
        assertEquals("Usuario registrado exitosamente", response.getMessage());
    }

    @Test
    void register_EmailAlreadyExists() {
        AuthenticationService.RegisterRequest request = new AuthenticationService.RegisterRequest();
        request.setEmail("existing@example.com");

        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authenticationService.register(request));
    }

    @Test
    void authenticate_Success() {
        AuthenticationService.LoginRequest request = new AuthenticationService.LoginRequest();
        request.setEmail("juan@example.com");
        request.setPassword("password123");

        User user = new User();
        user.setEmail("juan@example.com");
        user.setPassword("encoded-password");

        when(userRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encoded-password")).thenReturn(true);

        var response = authenticationService.authenticate(request);

        assertNotNull(response);
        assertEquals("mock-jwt-token", response.getToken());
    }

    @Test
    void authenticate_UserNotFound() {
        AuthenticationService.LoginRequest request = new AuthenticationService.LoginRequest();
        request.setEmail("nonexistent@example.com");

        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> authenticationService.authenticate(request));
    }

    @Test
    void authenticate_WrongPassword() {
        AuthenticationService.LoginRequest request = new AuthenticationService.LoginRequest();
        request.setEmail("juan@example.com");
        request.setPassword("wrong-password");

        User user = new User();
        user.setEmail("juan@example.com");
        user.setPassword("encoded-password");

        when(userRepository.findByEmail("juan@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "encoded-password")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authenticationService.authenticate(request));
    }
}
