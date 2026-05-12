package com.MoodMelody.MoodMelody.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoodMelody.MoodMelody.dto.SongDto;
import com.MoodMelody.MoodMelody.entity.Mood;
import com.MoodMelody.MoodMelody.repository.MoodRepository;
import com.MoodMelody.MoodMelody.repository.UserRepository;
import com.MoodMelody.MoodMelody.service.RecommendationService;
import com.MoodMelody.MoodMelody.service.SongService;

@Service
public class RecommendationServiceImpl implements RecommendationService{

    @Autowired
    private MoodRepository moodRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongService songService;

    // Get recommended songs for user
    public List<SongDto> recommendSongs(Long userId) {
    	
    	if(!userRepository.existsById(userId)) {
    	    throw new RuntimeException("User not found");
    	}

        // Step 1: Get latest mood of user
        List<Mood> moods = moodRepository.findByUserId(userId);

        if (moods.isEmpty()) {
            throw new RuntimeException("No mood found for user");
        }

        // Get latest mood (last entry)
        Mood latestMood = moods.get(moods.size() - 1);

        String moodType = latestMood.getMoodType();

        // Step 2: Get songs based on mood
        return songService.getSongsByMood(moodType);
    }
}