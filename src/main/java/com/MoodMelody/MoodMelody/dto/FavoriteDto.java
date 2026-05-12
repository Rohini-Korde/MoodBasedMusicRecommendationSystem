package com.MoodMelody.MoodMelody.dto;

public record FavoriteDto(
        Long id,
        Long userId,
        Long songId,
        String title,
        String artist
) {}
