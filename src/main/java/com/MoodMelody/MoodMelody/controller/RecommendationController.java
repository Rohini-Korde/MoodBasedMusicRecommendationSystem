package com.MoodMelody.MoodMelody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MoodMelody.MoodMelody.dto.SongDto;
import com.MoodMelody.MoodMelody.service.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<SongDto> getRecommendations(@PathVariable Long userId) {
        return recommendationService.recommendSongs(userId);
    }
}
