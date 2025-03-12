package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.FavoriteGameDto;
import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.services.FavoriteGameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteGameServiceImpl implements FavoriteGameService {

    @Override
    public FavoriteGameDto addToFavorites(FavoriteGameDto favoriteDto) {
        return null;
    }

    @Override
    public void removeFromFavorites(Integer favoriteId) {

    }

    @Override
    public FavoriteGameDto updateFavoritePosition(Integer favoriteId, Integer newPosition) {
        return null;
    }

    @Override
    public List<GameDto> getFavoriteGamesForUser(Integer userId) {
        return List.of();
    }

    @Override
    public List<UserDto> getUsersWhoFavoritedGame(Integer gameId) {
        return List.of();
    }

    @Override
    public boolean isGameFavoritedByUser(Integer userId, Integer gameId) {
        return false;
    }

    @Override
    public FavoriteGameDto getFavoriteById(Integer favoriteId) {
        return null;
    }

    @Override
    public List<FavoriteGameDto> reorderFavorites(Integer userId, List<Integer> newOrderIds) {
        return List.of();
    }

    @Override
    public void clearUserFavorites(Integer userId) {

    }

    @Override
    public Long getTotalFavoritesCountForGame(Integer gameId) {
        return 0L;
    }

    @Override
    public Map<String, Long> getMostFavoritedGames(int limit) {
        return Map.of();
    }

    @Override
    public Map<Integer, Long> getFavoriteCountByGameCategory() {
        return Map.of();
    }

    @Override
    public boolean existsFavoriteRelationship(Integer userId, Integer gameId) {
        return false;
    }

    @Override
    public boolean hasReachedFavoriteLimit(Integer userId) {
        return false;
    }

    @Override
    public List<FavoriteGameDto> batchAddFavorites(Integer userId, List<Integer> gameIds) {
        return List.of();
    }

    @Override
    public int batchRemoveFavorites(Integer userId, List<Integer> gameIds) {
        return 0;
    }

    @Override
    public void synchronizeFavorites(Integer userId, List<Integer> gameIds) {

    }

    @Override
    public void removeAllFavoritesForGame(Integer gameId) {

    }

    @Override
    public Map<UserDto, Integer> getTopUsersByFavorites(int limit) {
        return Map.of();
    }
}
