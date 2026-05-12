package com.MoodMelody.MoodMelody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MoodMelody.MoodMelody.dto.SongDto;
import com.MoodMelody.MoodMelody.entity.Song;
import com.MoodMelody.MoodMelody.service.SongService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    // Add song
    @PostMapping
    public SongDto addSong(@Valid @RequestBody Song song) {
        return songService.addSong(song);
    }

    // Get all songs
    @GetMapping
    public List<SongDto> getAllSongs() {
        return songService.getAllSongs();
    }

    // Get songs by mood
    @GetMapping("/mood/{mood}")
    public List<SongDto> getSongsByMood(@PathVariable String mood) {
        return songService.getSongsByMood(mood);
    }
    
    @GetMapping("/{id}")
    public SongDto getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }
    
    @PutMapping("/{id}")
    public SongDto updateSong(
            @PathVariable Long id,
            @Valid @RequestBody SongDto songDTO) {

        return songService.updateSong(id, songDTO);
    }
    
    @DeleteMapping("/{id}")
    public String deleteSong(@PathVariable Long id) {

        songService.deleteSong(id);

        return "Song deleted successfully";
    }
}
