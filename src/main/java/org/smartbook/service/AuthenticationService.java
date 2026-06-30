package org.smartbook.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.smartbook.model.Role;
import org.smartbook.model.User;
import org.smartbook.repository.RoleRepository;
import org.smartbook.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado: " + request.getEmail());
        }

        String requestedRole = request.getRolNombre() != null ? request.getRolNombre() : "USUARIO";
        Role role = roleRepository.findByNombre(requestedRole)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setNombre(requestedRole);
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
        String token = jwtService.generateToken(savedUser.getEmail(), role.getNombre(), savedUser.getId());

        return AuthenticationResponse.builder()
                .token(token)
                .message("Usuario registrado exitosamente")
                .userId(savedUser.getId())
                .nombre(savedUser.getNombre())
                .apellido(savedUser.getApellido())
                .email(savedUser.getEmail())
                .rol(role.getNombre())
                .build();
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().getNombre(), user.getId());
        return AuthenticationResponse.builder()
                .token(token)
                .message("Autenticación exitosa")
                .userId(user.getId())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .email(user.getEmail())
                .rol(user.getRole().getNombre())
                .build();
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
        private String rolNombre;
    }

    @Data
    @Builder
    public static class AuthenticationResponse {
        private String token;
        private String message;
        private Integer userId;
        private String nombre;
        private String apellido;
        private String email;
        private String rol;
    }
}

