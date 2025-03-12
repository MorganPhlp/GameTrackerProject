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
    Map<Integer, Double> getAverageRatingsForGames(List<Integer> gameIds);
    Long countRatingsForGame(Integer gameId);

    //Vérifications
    boolean hasUserRatedGame(Integer userId, Integer gameId);
    boolean isValidRatingValue(Integer rating);

    //Gestion batch
    void batchDeleteRatings(List<Integer> ratingIds);
    int batchUpdateRatingsForUser(Integer userId, Integer newRating);
    void recalculateAllAverageRatings();

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
    void syncWithExternalRatingSystems(Integer gameId);

    //Sécurité/Validation
    boolean isRatingOwner(Integer ratingId, Integer userId);

}