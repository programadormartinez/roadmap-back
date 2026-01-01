package com.roadmap.my.myroadmapback.controller;

import com.roadmap.my.myroadmapback.dto.LoginRequestDTO;
import com.roadmap.my.myroadmapback.dto.RegisterRequestDTO;
import com.roadmap.my.myroadmapback.dto.TokenResponseDTO;
import com.roadmap.my.myroadmapback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody final RegisterRequestDTO request) {
        final TokenResponseDTO token = service.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody final LoginRequestDTO request) {
        final TokenResponseDTO token = service.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        final TokenResponseDTO token = service.refreshToken(authHeader);
        return ResponseEntity.ok(token);
    }
}
