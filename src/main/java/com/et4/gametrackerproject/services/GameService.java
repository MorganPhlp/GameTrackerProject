package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameService {

    // Opérations de base
    GameDto createGame(GameDto gameDto);
    GameDto updateGame(Integer id, GameDto gameDto);
    void deleteGame(Integer id);
    GameDto getGameById(Integer id);

    // Récupération
    Page<GameDto> getAllGames(Pageable pageable);
    Page<GameDto> searchGames(String query, Pageable pageable);
    List<GameDto> getGamesByIds(List<Integer> ids);

    //Filtrage
    Page<GameDto> filterByCategory(GameCategory category, Pageable pageable);
    Page<GameDto> filterByDifficulty(DifficultyLevel difficulty, Pageable pageable);
    Page<GameDto> filterByAgeRange(Integer minAge, Integer maxAge, Pageable pageable);
    Page<GameDto> filterByTags(Set<String> tags, Pageable pageable);

    //Gestion des métadonnées
    GameDto updateGameStatistics(Integer gameId);
    void updateAverageRating(Integer gameId);

    // Relations
    GameDto addTagToGame(Integer gameId, String tag);
    GameDto removeTagFromGame(Integer gameId, String tag);
    Page<GameDto> getSimilarGames(Integer gameId, Pageable pageable);

    // Statistiques
    Map<GameCategory, Long> getGameDistributionByCategory();
    Map<DifficultyLevel, Long> getGameDistributionByDifficulty();
    Map<String, Long> getPlayActivityTrends(Integer gameId);
    Map<String, Object> getGamePerformanceReport(Integer gameId);

    // Administration
    void batchUpdateGamesStatus(List<Integer> gameIds, boolean isActive);
    void migrateGameData(Integer sourceGameId, Integer targetGameId);
    Page<GameDto> getRecentlyUpdatedGames(Pageable pageable);

    //Validation
    boolean checkNameAvailability(String name);
    boolean validateAgeRequirements(Integer gameId, Integer userAge);

    //Intégration
    void refreshGameCache(Integer gameId);
    void handleRelatedEntitiesOnDeletion(Integer gameId);

    //Recommandations
    List<GameDto> getTrendingGames(int limit);

    //Gestion des assets
    GameDto updateGameThumbnail(Integer gameId, String thumbnailUrl);
    GameDto updateGameDescription(Integer gameId, String description);
}