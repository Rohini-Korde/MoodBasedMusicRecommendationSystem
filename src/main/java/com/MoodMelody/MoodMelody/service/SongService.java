package com.MoodMelody.MoodMelody.service;

import java.util.List;

import com.MoodMelody.MoodMelody.dto.SongDto;
import com.MoodMelody.MoodMelody.entity.Song;

public interface SongService {

	public SongDto addSong(Song song);
	
	public List<SongDto> getAllSongs();
	
	public List<SongDto> getSongsByMood(String mood);
	
	SongDto getSongById(Long id);
	
	SongDto updateSong(Long id, SongDto songDTO);

	void deleteSong(Long id);
}
