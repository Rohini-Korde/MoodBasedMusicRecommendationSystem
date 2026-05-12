package com.MoodMelody.MoodMelody.service;

import java.util.List;

import com.MoodMelody.MoodMelody.dto.SongDto;

public interface RecommendationService {
	
	public List<SongDto> recommendSongs(Long userId);
}
