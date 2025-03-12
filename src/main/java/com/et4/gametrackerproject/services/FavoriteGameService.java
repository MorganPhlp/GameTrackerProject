package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.FavoriteGameDto;
import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface FavoriteGameService {

    //Opérations de base
    FavoriteGameDto addToFavorites(FavoriteGameDto favoriteDto);
    void removeFromFavorites(Integer favoriteId);
    FavoriteGameDto updateFavoritePosition(Integer favoriteId, Integer newPosition);

    //Récupération
    List<GameDto> getFavoriteGamesForUser(Integer userId);
    List<UserDto> getUsersWhoFavoritedGame(Integer gameId);
    boolean isGameFavoritedByUser(Integer userId, Integer gameId);
    FavoriteGameDto getFavoriteById(Integer favoriteId);

    // Gestion des listes
    List<FavoriteGameDto> reorderFavorites(Integer userId, List<Integer> newOrderIds);
    void clearUserFavorites(Integer userId);

    //Statistiques
    Long getTotalFavoritesCountForGame(Integer gameId);
    Map<String, Long> getMostFavoritedGames(int limit);
    Map<Integer, Long> getFavoriteCountByGameCategory();

    // Vérifications
    boolean existsFavoriteRelationship(Integer userId, Integer gameId);
    boolean hasReachedFavoriteLimit(Integer userId);

    //Gestion batch
    List<FavoriteGameDto> batchAddFavorites(Integer userId, List<Integer> gameIds);
    int batchRemoveFavorites(Integer userId, List<Integer> gameIds);

    //Synchronisation
    void synchronizeFavorites(Integer userId, List<Integer> gameIds);

    //Administration
    void removeAllFavoritesForGame(Integer gameId);
    Map<UserDto, Integer> getTopUsersByFavorites(int limit);

}