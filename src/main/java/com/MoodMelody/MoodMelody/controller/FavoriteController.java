package com.MoodMelody.MoodMelody.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.MoodMelody.MoodMelody.dto.FavoriteDto;
import com.MoodMelody.MoodMelody.service.FavouriteService;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavouriteService favoriteService;

    // Add favorite
    @PostMapping
    public FavoriteDto addFavorite(
            @RequestParam Long userId,
            @RequestParam Long songId) {

        return favoriteService.addFavorite(userId, songId);
    }

    // Get favorites
    @GetMapping("/{userId}")
    public List<FavoriteDto> getFavorites(@PathVariable Long userId) {
        return favoriteService.getFavorites(userId);
    }
}
