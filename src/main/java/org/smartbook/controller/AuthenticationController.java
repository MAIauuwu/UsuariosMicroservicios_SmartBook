package org.smartbook.controller;

import lombok.AllArgsConstructor;
import org.smartbook.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationService.AuthenticationResponse> register(
            @RequestBody AuthenticationService.RegisterRequest request) {
        AuthenticationService.AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationService.AuthenticationResponse> login(
            @RequestBody AuthenticationService.LoginRequest request) {
        AuthenticationService.AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}

