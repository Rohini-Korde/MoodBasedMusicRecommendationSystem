package com.MoodMelody.MoodMelody.service;

import java.util.List;

import com.MoodMelody.MoodMelody.dto.MoodDto;
import com.MoodMelody.MoodMelody.dto.SongDto;
import com.MoodMelody.MoodMelody.entity.Song;

public interface MoodService {
	
	MoodDto saveMood(Long userId, String moodType);
	
	List<MoodDto> getMoodHistory(Long userId);
}
