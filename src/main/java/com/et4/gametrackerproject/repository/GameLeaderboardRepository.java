package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.GameLeaderboard;
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

public interface GameLeaderboardRepository extends JpaRepository<GameLeaderboard,Integer> {

    // Trouver le classement d'un utilisateur pour un jeu et une période
    Optional<GameLeaderboard> findByGameAndUserAndPeriod(Game game, User user, LeaderboardPeriod period);

    // Récupérer le tableau de classement complet pour un jeu et une période, trié par rang
    List<GameLeaderboard> findByGameAndPeriodOrderByRankNumber(Game game, LeaderboardPeriod period);

    // Récupérer le tableau de classement complet pour un jeu et une période, trié par score
    List<GameLeaderboard> findByGameAndPeriodOrderByScoreDesc(Game game, LeaderboardPeriod period);

    // Pagination du tableau de classement
    Page<GameLeaderboard> findByGameAndPeriodOrderByRankNumber(Game game, LeaderboardPeriod period, Pageable pageable);

    // Récupérer les N meilleurs joueurs pour un jeu et une période
    @Query("SELECT gl FROM GameLeaderboard gl WHERE gl.game = :game AND gl.period = :period ORDER BY gl.rankNumber ASC")
    List<GameLeaderboard> findTopRankedByGameAndPeriod(@Param("game") Game game,
                                                       @Param("period") LeaderboardPeriod period,
                                                       Pageable pageable);

    // Récupérer tous les classements d'un utilisateur
    List<GameLeaderboard> findByUser(User user);

    // Récupérer tous les classements d'un utilisateur pour une période spécifique
    List<GameLeaderboard> findByUserAndPeriod(User user, LeaderboardPeriod period);

    // Récupérer tous les classements d'un utilisateur pour un jeu spécifique
    List<GameLeaderboard> findByUserAndGame(User user, Game game);

    // Compter le nombre d'entrées dans le classement pour un jeu et une période
    Long countByGameAndPeriod(Game game, LeaderboardPeriod period);

    // Récupérer le meilleur classement historique d'un utilisateur pour un jeu
    @Query("SELECT MIN(gl.rankNumber) FROM GameLeaderboard gl WHERE gl.user = :user AND gl.game = :game")
    Integer findBestRankByUserAndGame(@Param("user") User user, @Param("game") Game game);

    // Récupérer le meilleur score historique d'un utilisateur pour un jeu
    @Query("SELECT MAX(gl.score) FROM GameLeaderboard gl WHERE gl.user = :user AND gl.game = :game")
    Integer findBestScoreByUserAndGame(@Param("user") User user, @Param("game") Game game);

    // Récupérer les jeux où un utilisateur est dans le top N
    @Query("SELECT gl.game FROM GameLeaderboard gl WHERE gl.user = :user AND gl.rankNumber <= :topRank AND gl.period = :period")
    List<Game> findGamesWhereUserIsInTopRank(@Param("user") User user,
                                             @Param("topRank") Integer topRank,
                                             @Param("period") LeaderboardPeriod period);

    // Trouver les jeux les plus compétitifs (avec le plus grand nombre de différents utilisateurs classés)
    @Query("SELECT gl.game, COUNT(DISTINCT gl.user) as playerCount FROM GameLeaderboard gl " +
            "WHERE gl.period = :period GROUP BY gl.game ORDER BY playerCount DESC")
    List<Object[]> findMostCompetitiveGamesByPeriod(@Param("period") LeaderboardPeriod period);

    // Récupérer l'historique des classements d'un utilisateur pour un jeu
    @Query("SELECT gl FROM GameLeaderboard gl WHERE gl.user = :user AND gl.game = :game ORDER BY gl.date DESC")
    List<GameLeaderboard> findUserRankHistoryForGame(@Param("user") User user, @Param("game") Game game);

    // Récupérer les entrées de classement par date spécifique
    List<GameLeaderboard> findByDate(Instant date);

    // Supprimer les entrées de classement obsolètes
    @Modifying
    @Query("DELETE FROM GameLeaderboard gl WHERE gl.date < :cutoffDate")
    void deleteObsoleteEntries(@Param("cutoffDate") Instant cutoffDate);
}
