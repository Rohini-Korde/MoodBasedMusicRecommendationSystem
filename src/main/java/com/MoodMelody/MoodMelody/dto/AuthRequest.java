package com.MoodMelody.MoodMelody.dto;

public record AuthRequest(
        String email,
        String password
) {}