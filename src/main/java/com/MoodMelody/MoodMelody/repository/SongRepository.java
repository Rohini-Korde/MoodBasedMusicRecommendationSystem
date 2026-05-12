package com.MoodMelody.MoodMelody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MoodMelody.MoodMelody.entity.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByMood(String mood);

}
