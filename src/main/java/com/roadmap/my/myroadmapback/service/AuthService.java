package com.roadmap.my.myroadmapback.service;

import com.roadmap.my.myroadmapback.dto.LoginRequestDTO;
import com.roadmap.my.myroadmapback.dto.RegisterRequestDTO;
import com.roadmap.my.myroadmapback.dto.TokenResponseDTO;
import com.roadmap.my.myroadmapback.model.User;

public interface AuthService {
    public TokenResponseDTO register(RegisterRequestDTO request);

    public TokenResponseDTO login(LoginRequestDTO request);

    public TokenResponseDTO refreshToken(final String authHeader);
}
