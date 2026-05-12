package com.MoodMelody.MoodMelody.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MoodMelody.MoodMelody.dto.FavoriteDto;
import com.MoodMelody.MoodMelody.entity.Favorite;
import com.MoodMelody.MoodMelody.entity.Song;
import com.MoodMelody.MoodMelody.entity.User;
import com.MoodMelody.MoodMelody.repository.FavoriteRepository;
import com.MoodMelody.MoodMelody.repository.SongRepository;
import com.MoodMelody.MoodMelody.repository.UserRepository;
import com.MoodMelody.MoodMelody.service.FavouriteService;

@Service
public class FavouriteServiceImpl implements FavouriteService{
	
	@Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;
	@Override
	public FavoriteDto addFavorite(Long userId, Long songId) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setSong(song);

        Favorite saved = favoriteRepository.save(favorite);

        return new FavoriteDto(
                saved.getId(),
                userId,
                songId,
                song.getTitle(),
                song.getArtist()
        );
	}

	@Override
	public List<FavoriteDto> getFavorites(Long userId) {
		return favoriteRepository.findByUserId(userId)
                .stream()
                .map(fav -> new FavoriteDto(
                        fav.getId(),
                        fav.getUser().getId(),
                        fav.getSong().getId(),
                        fav.getSong().getTitle(),
                        fav.getSong().getArtist()
                ))
                .toList();
	}

}
