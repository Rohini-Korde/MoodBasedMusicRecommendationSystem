package com.MoodMelody.MoodMelody.dto;

import jakarta.validation.constraints.NotBlank;

public record SongDto(

        Long id,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Artist is required")
        String artist,

        @NotBlank(message = "Mood is required")
        String mood
) {}
