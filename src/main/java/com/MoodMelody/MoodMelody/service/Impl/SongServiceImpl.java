package com.MoodMelody.MoodMelody.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoodMelody.MoodMelody.dto.SongDto;
import com.MoodMelody.MoodMelody.entity.Song;
import com.MoodMelody.MoodMelody.repository.SongRepository;
import com.MoodMelody.MoodMelody.service.SongService;

@Service
public class SongServiceImpl implements SongService{

    @Autowired
    private SongRepository songRepository;

    // Add song
    public SongDto addSong(Song song) {

        Song savedSong = songRepository.save(song);

        return new SongDto(
                savedSong.getId(),
                savedSong.getTitle(),
                savedSong.getArtist(),
                savedSong.getMood()
        );
    }

    // Get all songs
    public List<SongDto> getAllSongs() {
        return songRepository.findAll()
                .stream()
                .map(song -> new SongDto(
                        song.getId(),
                        song.getTitle(),
                        song.getArtist(),
                        song.getMood()))
                .toList();
    }

    // Get songs by mood
    public List<SongDto> getSongsByMood(String mood) {
        return songRepository.findByMood(mood)
                .stream()
                .map(song -> new SongDto(
                        song.getId(),
                        song.getTitle(),
                        song.getArtist(),
                        song.getMood()))
                .toList();
    }

	@Override
	public SongDto updateSong(Long id, SongDto songDTO) {
		 Song song = songRepository.findById(id)
		            .orElseThrow(() ->
		                    new RuntimeException("Song not found"));

		    song.setTitle(songDTO.title());
		    song.setArtist(songDTO.artist());
		    song.setMood(songDTO.mood());

		    Song updatedSong = songRepository.save(song);

		    return new SongDto(
		            updatedSong.getId(),
		            updatedSong.getTitle(),
		            updatedSong.getArtist(),
		            updatedSong.getMood()
		    );
	}

	@Override
	public void deleteSong(Long id) {

	    Song song = songRepository.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("Song not found"));

	    songRepository.delete(song);
	}
	
	@Override
	public SongDto getSongById(Long id) {

	    Song song = songRepository.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("Song not found"));

	    return new SongDto(
	            song.getId(),
	            song.getTitle(),
	            song.getArtist(),
	            song.getMood()
	    );
	}
}
