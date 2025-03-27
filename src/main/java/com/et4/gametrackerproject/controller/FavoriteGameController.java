package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.FavoriteGameApi;
import com.et4.gametrackerproject.dto.FavoriteGameDto;
import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.services.FavoriteGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FavoriteGameController implements FavoriteGameApi {

    @Autowired
    private FavoriteGameService favoriteGameService;

    public FavoriteGameController(FavoriteGameService favoritegameservice) {
        this.favoriteGameService = favoritegameservice;
    }


    @Override
    public FavoriteGameDto addToFavorites(FavoriteGameDto favoriteDto) {
        return favoriteGameService.addToFavorites(favoriteDto);
    }

    @Override
    public void removeFromFavorites(Integer favoriteId) {
        favoriteGameService.removeFromFavorites(favoriteId);
    }

    @Override
    public List<GameDto> getFavoriteGamesForUser(Integer userId) {
        return favoriteGameService.getFavoriteGamesForUser(userId);
    }

    @Override
    public List<UserDto> getUsersWhoFavoritedGame(Integer gameId) {
        return favoriteGameService.getUsersWhoFavoritedGame(gameId);
    }

    @Override
    public boolean isGameFavoritedByUser(Integer userId, Integer gameId) {
        return favoriteGameService.isGameFavoritedByUser(userId, gameId);
    }

    @Override
    public FavoriteGameDto getFavoriteById(Integer favoriteId) {
        return favoriteGameService.getFavoriteById(favoriteId);
    }

    @Override
    public void clearUserFavorites(Integer userId) {
        favoriteGameService.clearUserFavorites(userId);
    }

    @Override
    public Long getTotalFavoritesCountForGame(Integer gameId) {
        return favoriteGameService.getTotalFavoritesCountForGame(gameId);
    }

    @Override
    public Map<String, Long> getMostFavoritedGames(int limit) {
        return favoriteGameService.getMostFavoritedGames(limit);
    }

    @Override
    public Map<Integer, Long> getFavoriteCountByGameCategory() {
        return favoriteGameService.getFavoriteCountByGameCategory();
    }
}
