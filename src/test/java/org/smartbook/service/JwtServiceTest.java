package org.smartbook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "jwtSecret", "TestSecretKeyForDevelopmentPurposesOnly123456789");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 86400000L);
    }

    @Test
    void generateTokenWithEmail_Success() {
        String token = jwtService.generateToken("test@example.com");

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractEmail_FromValidToken() {
        String token = jwtService.generateToken("test@example.com");
        String email = jwtService.extractEmail(token);

        assertEquals("test@example.com", email);
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {
        String token = jwtService.generateToken("test@example.com");
        assertTrue(jwtService.validateToken(token, "test@example.com"));
    }

    @Test
    void validateToken_InvalidEmail_ReturnsFalse() {
        String token = jwtService.generateToken("test@example.com");
        assertFalse(jwtService.validateToken(token, "other@example.com"));
    }
}
