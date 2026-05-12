package com.MoodMelody.MoodMelody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MoodMelody.MoodMelody.dto.MoodDto;
import com.MoodMelody.MoodMelody.service.MoodService;

import java.util.List;

@RestController
@RequestMapping("/moods")
public class MoodController {

    @Autowired
    private MoodService moodService;

    // Save mood
    @PostMapping
    public MoodDto saveMood(
            @RequestParam Long userId,
            @RequestParam String moodType) {

        return moodService.saveMood(userId, moodType);
    }

    // Get mood history
    @GetMapping("/{userId}")
    public List<MoodDto> getMoodHistory(@PathVariable Long userId) {
        return moodService.getMoodHistory(userId);
    }
}
