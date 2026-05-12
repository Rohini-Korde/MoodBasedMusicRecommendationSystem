package com.MoodMelody.MoodMelody.service;

import java.util.List;

import com.MoodMelody.MoodMelody.dto.FavoriteDto;

public interface FavouriteService {
	
	FavoriteDto addFavorite(Long userId, Long songId);
	
	List<FavoriteDto> getFavorites(Long userId);

}
