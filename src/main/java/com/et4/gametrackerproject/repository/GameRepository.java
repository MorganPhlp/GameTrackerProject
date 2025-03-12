package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.enums.DifficultyLevel;
import com.et4.gametrackerproject.enums.GameCategory;
import com.et4.gametrackerproject.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game,Integer> {

    // Recherches de base
    Optional<Game> findByUrl(String url);

    List<Game> findByName(String name);

    List<Game> findByNameContainingIgnoreCase(String namePattern);

    List<Game> findByCategory(GameCategory category);

    List<Game> findByDifficultyLevel(DifficultyLevel difficultyLevel);

    Page<Game> findByIsActive(Boolean isActive, Pageable pageable);

    // Recherches combinées
    List<Game> findByCategoryAndDifficultyLevel(GameCategory category, DifficultyLevel difficultyLevel);

    List<Game> findByNameContainingIgnoreCaseAndCategoryAndIsActive(
            String namePattern, GameCategory category, Boolean isActive);

    // Recherches basées sur les notations
    List<Game> findByAverageRatingGreaterThanEqual(Double minRating);

    @Query("SELECT g FROM Game g WHERE g.averageRating >= :minRating")
    List<Game> findHighlyRatedGames(@Param("minRating") Double minRating);

    // Recherches basées sur la popularité
    List<Game> findByPlayCountGreaterThan(Integer minPlayCount);

    @Query("SELECT g FROM Game g ORDER BY g.playCount DESC")
    List<Game> findMostPopularGames(Pageable pageable);

    // Recherche par restriction d'âge
    List<Game> findByMinAgeLessThanEqual(Integer age);

    // Combinaisons de critères complexes
    @Query("SELECT g FROM Game g WHERE " +
            "(:name IS NULL OR LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:category IS NULL OR g.category = :category) AND " +
            "(:difficulty IS NULL OR g.difficultyLevel = :difficulty) AND " +
            "(:minRating IS NULL OR g.averageRating >= :minRating) AND " +
            "(:minAge IS NULL OR g.minAge <= :minAge) AND " +
            "g.isActive = true")
    Page<Game> findGamesWithFilters(
            @Param("name") String name,
            @Param("category") GameCategory category,
            @Param("difficulty") DifficultyLevel difficulty,
            @Param("minRating") Double minRating,
            @Param("minAge") Integer minAge,
            Pageable pageable);

    // Mises à jour et statistiques
    @Modifying
    @Query("UPDATE Game g SET g.playCount = g.playCount + 1 WHERE g.id = :gameId")
    void incrementPlayCount(@Param("gameId") Integer gameId);

    @Modifying
    @Query("UPDATE Game g SET g.averageRating = :newRating WHERE g.id = :gameId")
    void updateAverageRating(@Param("gameId") Integer gameId, @Param("newRating") Double newRating);

    @Query("SELECT SUM(g.playCount) FROM Game g")
    Integer getTotalPlayCount();

    // Recherches des jeux les plus récents
    @Query("SELECT g FROM Game g ORDER BY g.creationDate DESC")
    List<Game> findNewestGames(Pageable pageable);

    // Recherche des jeux avec des tags spécifiques
    @Query("SELECT DISTINCT g FROM Game g " +
            "JOIN g.tags t " +
            "WHERE t.tag.name IN :tagNames")
    List<Game> findGamesByTags(@Param("tagNames") List<String> tagNames);

    // Recherche des jeux populaires par catégorie
    @Query("SELECT g FROM Game g " +
            "WHERE g.category = :category " +
            "ORDER BY g.playCount DESC")
    List<Game> findMostPopularGamesByCategory(@Param("category") GameCategory category, Pageable pageable);

    // Trouver les jeux les plus actifs récemment (basé sur les progressions)
    @Query(value =
            "SELECT g.*, COUNT(DISTINCT gp.user_id) as active_users " +
                    "FROM game g " +
                    "JOIN gameprogress gp ON g.id = gp.game_id " +
                    "WHERE gp.last_played >= :since " +
                    "GROUP BY g.id " +
                    "ORDER BY active_users DESC",
            nativeQuery = true)
    List<Object[]> findMostActiveGames(@Param("since") Instant since, Pageable pageable);
}
