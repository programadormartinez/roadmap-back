package com.roadmap.my.myroadmapback.service;

import com.roadmap.my.myroadmapback.model.User;

public interface JwtService {
    public String generateToken(final User user);
    public String generateRefreshToken(final User user);
    public String extractUsername(String token);
    public boolean isTokenValid(String token, final User user);
}
