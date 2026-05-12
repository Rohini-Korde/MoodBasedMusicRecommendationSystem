package com.MoodMelody.MoodMelody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MoodMelody.MoodMelody.entity.Mood;

import java.util.List;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {

    List<Mood> findByUserId(Long userId);

}