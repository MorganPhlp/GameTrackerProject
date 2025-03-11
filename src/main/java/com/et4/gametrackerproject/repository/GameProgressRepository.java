package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.enums.GameStatus;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.GameProgress;
import com.et4.gametrackerproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface GameProgressRepository extends JpaRepository<GameProgress,Integer> {

    // Trouver la progression d'un utilisateur pour un jeu spécifique
    Optional<GameProgress> findByUserAndGame(User user, Game game);

    // Récupérer la liste des progressions d'un utilisateur
    List<GameProgress> findByUser(User user);

    // Récupérer la liste des progressions d'un utilisateur avec pagination
    Page<GameProgress> findByUser(User user, Pageable pageable);

    // Récupérer la liste des progressions pour un jeu spécifique
    List<GameProgress> findByGame(Game game);

    // Récupérer les progressions d'un utilisateur filtrées par statut
    List<GameProgress> findByUserAndStatus(User user, GameStatus status);

    // Récupérer les progressions d'un utilisateur filtrées par statut avec pagination
    Page<GameProgress> findByUserAndStatus(User user, GameStatus status, Pageable pageable);

    // Récupérer les jeux auxquels l'utilisateur a joué récemment
    List<GameProgress> findByUserOrderByLastPlayedDesc(User user);

    // Récupérer les jeux auxquels l'utilisateur a joué récemment avec pagination
    Page<GameProgress> findByUserOrderByLastPlayedDesc(User user, Pageable pageable);

    // Rechercher les jeux avec les meilleurs scores d'un utilisateur
    List<GameProgress> findByUserOrderByBestScoreDesc(User user);

    // Rechercher les jeux avec le plus de temps joué par un utilisateur
    List<GameProgress> findByUserOrderByTimePlayedDesc(User user);

    // Rechercher les progressions actualisées récemment
    @Query("SELECT gp FROM GameProgress gp WHERE gp.user = :user AND gp.lastPlayed >= :since ORDER BY gp.lastPlayed DESC")
    List<GameProgress> findRecentlyPlayedGames(@Param("user") User user, @Param("since") Instant since);

    // Compter le nombre de jeux par statut pour un utilisateur
    @Query("SELECT gp.status, COUNT(gp) FROM GameProgress gp WHERE gp.user = :user GROUP BY gp.status")
    List<Object[]> countGamesByStatusForUser(@Param("user") User user);

    // Récupérer le temps total de jeu d'un utilisateur
    @Query("SELECT SUM(gp.timePlayed) FROM GameProgress gp WHERE gp.user = :user")
    Integer getTotalPlaytimeForUser(@Param("user") User user);

    // Récupérer les utilisateurs qui ont joué à un jeu spécifique, triés par temps de jeu
    @Query("SELECT gp.user, gp.timePlayed FROM GameProgress gp WHERE gp.game = :game ORDER BY gp.timePlayed DESC")
    List<Object[]> findUsersByGameOrderByPlaytime(@Param("game") Game game);

    // Récupérer les jeux les plus populaires basés sur le nombre d'utilisateurs
    @Query("SELECT gp.game, COUNT(gp.user) as userCount FROM GameProgress gp GROUP BY gp.game ORDER BY userCount DESC")
    List<Object[]> findMostPopularGames();

    // Récupérer les utilisateurs avec le meilleur score pour un jeu
    @Query("SELECT gp.user, gp.bestScore FROM GameProgress gp WHERE gp.game = :game ORDER BY gp.bestScore DESC")
    List<Object[]> findTopScoringUsersByGame(@Param("game") Game game);

    // Récupérer le ratio de victoires/défaites d'un utilisateur
    @Query("SELECT gp.wins, gp.losses FROM GameProgress gp WHERE gp.user = :user AND gp.game = :game")
    Object findWinLossRatioByUserAndGame(@Param("user") User user, @Param("game") Game game);

    // Récupérer les utilisateurs avec les plus longs streaks
    @Query("SELECT gp.user, gp.currentStreak, gp.game.name FROM GameProgress gp ORDER BY gp.currentStreak DESC")
    List<Object[]> findUsersWithLongestStreaks();

    // Récupérer les jeux avec le plus grand nombre terminés (statut COMPLETED)
    @Query("SELECT gp.game, COUNT(gp) as completions FROM GameProgress gp WHERE gp.status = 'COMPLETED' GROUP BY gp.game ORDER BY completions DESC")
    List<Object[]> findMostCompletedGames();

    // Récupérer les statistiques d'un utilisateur (temps total, jeux terminés, etc.)
    @Query(value =
            "SELECT " +
                    "SUM(gp.time_played) as total_time, " +
                    "COUNT(*) as total_games, " +
                    "SUM(CASE WHEN gp.status = 'COMPLETED' THEN 1 ELSE 0 END) as completed, " +
                    "SUM(gp.wins) as total_wins, " +
                    "SUM(gp.losses) as total_losses, " +
                    "MAX(gp.current_streak) as best_streak " +
                    "FROM gameprogress gp WHERE gp.user_id = :userId",
            nativeQuery = true)
    Object getUserStatistics(@Param("userId") Integer userId);

    // Mettre à jour le statut de progression pour un utilisateur et un jeu
    @Modifying
    @Query("UPDATE GameProgress gp SET gp.status = :status WHERE gp.user = :user AND gp.game = :game")
    void updateGameStatus(@Param("user") User user, @Param("game") Game game, @Param("status") GameStatus status);

    // Mettre à jour le temps joué pour un utilisateur et un jeu
    @Modifying
    @Query("UPDATE GameProgress gp SET gp.timePlayed = gp.timePlayed + :additionalTime, " +
            "gp.lastPlayed = :timestamp WHERE gp.user = :user AND gp.game = :game")
    void updatePlaytime(@Param("user") User user,
                        @Param("game") Game game,
                        @Param("additionalTime") Integer additionalTime,
                        @Param("timestamp") Instant timestamp);

    // Mettre à jour le score et vérifier s'il s'agit du meilleur score
    @Modifying
    @Query("UPDATE GameProgress gp SET gp.score = :score, " +
            "gp.bestScore = CASE WHEN :score > gp.bestScore OR gp.bestScore IS NULL THEN :score ELSE gp.bestScore END, " +
            "gp.lastPlayed = :timestamp WHERE gp.user = :user AND gp.game = :game")
    void updateScore(@Param("user") User user,
                     @Param("game") Game game,
                     @Param("score") Integer score,
                     @Param("timestamp") Instant timestamp);

    // Enregistrer une victoire
    @Modifying
    @Query("UPDATE GameProgress gp SET gp.wins = gp.wins + 1, " +
            "gp.currentStreak = gp.currentStreak + 1, " +
            "gp.lastPlayed = :timestamp WHERE gp.user = :user AND gp.game = :game")
    void recordWin(@Param("user") User user, @Param("game") Game game, @Param("timestamp") Instant timestamp);

    // Enregistrer une défaite
    @Modifying
    @Query("UPDATE GameProgress gp SET gp.losses = gp.losses + 1, " +
            "gp.currentStreak = 0, " +
            "gp.lastPlayed = :timestamp WHERE gp.user = :user AND gp.game = :game")
    void recordLoss(@Param("user") User user, @Param("game") Game game, @Param("timestamp") Instant timestamp);

    // Trouver les jeux qui n'ont pas été joués depuis longtemps
    @Query("SELECT gp FROM GameProgress gp WHERE gp.user = :user AND gp.lastPlayed < :cutoffDate ORDER BY gp.lastPlayed ASC")
    List<GameProgress> findUnplayedGames(@Param("user") User user, @Param("cutoffDate") Instant cutoffDate);
}