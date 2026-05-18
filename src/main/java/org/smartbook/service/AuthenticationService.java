package org.smartbook.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.smartbook.model.Role;
import org.smartbook.model.User;
import org.smartbook.repository.RoleRepository;
import org.smartbook.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + request.getEmail());
        }

        Role role = roleRepository.findByNombre("USUARIO")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setNombre("USUARIO");
                    return roleRepository.save(newRole);
                });

        User user = new User();
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser.getEmail());

        return new AuthenticationResponse(token, "Usuario registrado exitosamente");
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthenticationResponse(token, "Autenticación exitosa");
    }

    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class RegisterRequest {
        private String nombre;
        private String apellido;
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class AuthenticationResponse {
        private String token;
        private String message;
    }
}

