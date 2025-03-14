package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameRatingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface GameRatingService {

    //Opérations de base
    GameRatingDto submitRating(GameRatingDto ratingDto);
    GameRatingDto updateRating(Integer ratingId, Integer newRating);
    void deleteRating(Integer ratingId);

    //Récupération des évaluations
    GameRatingDto getRatingById(Integer ratingId);
    GameRatingDto getUserRatingForGame(Integer userId, Integer gameId);
    Page<GameRatingDto> getRatingsForGame(Integer gameId, Pageable pageable);
    Page<GameRatingDto> getRatingsByUser(Integer userId, Pageable pageable);

    //Statistiques
    Double calculateAverageRatingForGame(Integer gameId);
    Map<Integer, Long> getRatingDistributionForGame(Integer gameId);
    Long countRatingsForGame(Integer gameId);

    //Vérifications
    boolean hasUserRatedGame(Integer userId, Integer gameId);
    boolean isValidRatingValue(Integer rating);

    //Modération
    Page<GameRatingDto> getRecentRatings(Pageable pageable);

    //Administration
    void deleteAllRatingsForGame(Integer gameId);
    void deleteAllRatingsForUser(Integer userId);
    Page<GameRatingDto> searchRatings(String searchQuery, Pageable pageable);

    //Analyse
    Map<Integer, Long> getTopRatedGames(int limit);

    //Intégration
    void updateGameAverageRating(Integer gameId);

    //Sécurité/Validation
    boolean isRatingOwner(Integer ratingId, Integer userId);

}