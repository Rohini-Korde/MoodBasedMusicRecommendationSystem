package com.MoodMelody.MoodMelody.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoodMelody.MoodMelody.dto.MoodDto;
import com.MoodMelody.MoodMelody.entity.Mood;
import com.MoodMelody.MoodMelody.entity.User;
import com.MoodMelody.MoodMelody.repository.MoodRepository;
import com.MoodMelody.MoodMelody.repository.UserRepository;
import com.MoodMelody.MoodMelody.service.MoodService;

@Service
public class MoodServiceImpl implements MoodService{
	@Autowired
    private MoodRepository moodRepository;

    @Autowired
    private UserRepository userRepository;

    // Save mood
    public MoodDto saveMood(Long userId, String moodType) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Mood mood = new Mood();
        mood.setMoodType(moodType);
        mood.setUser(user);

        Mood savedMood = moodRepository.save(mood);

        return new MoodDto(
                savedMood.getId(),
                savedMood.getMoodType(),
                userId
        );
    }

    // Get mood history
    public List<MoodDto> getMoodHistory(Long userId) {

        return moodRepository.findByUserId(userId)
                .stream()
                .map(mood -> new MoodDto(
                        mood.getId(),
                        mood.getMoodType(),
                        mood.getUser().getId()))
                .toList();
    }
}
