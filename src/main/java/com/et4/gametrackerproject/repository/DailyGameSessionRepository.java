package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.DailyGameSession;
import com.et4.gametrackerproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface DailyGameSessionRepository extends JpaRepository<DailyGameSession,Integer> {

    // Recherche par utilisateur
    List<DailyGameSession> findByUser(User user);

    // Recherche par date
    List<DailyGameSession> findByDate(Instant date);

    // Recherche par utilisateur et date (combinaison unique)
    Optional<DailyGameSession> findByUserAndDate(User user, Instant date);

    // Recherche des sessions pour un utilisateur sur une période donnée
    List<DailyGameSession> findByUserAndDateBetween(User user, Instant startDate, Instant endDate);

    // Recherche des sessions où le temps de jeu dépasse un certain seuil
    List<DailyGameSession> findByTotalTimePlayedGreaterThan(Integer minutes);

    // Recherche des sessions où le nombre de jeux uniques joués dépasse un seuil
    List<DailyGameSession> findByUniqueGamesPlayedGreaterThanEqual(Integer count);

    // Calcul du temps de jeu total pour un utilisateur
    @Query("SELECT SUM(d.totalTimePlayed) FROM DailyGameSession d WHERE d.user = :user")
    Integer calculateTotalPlaytimeByUser(@Param("user") User user);

    // Calcul du temps de jeu total pour un utilisateur sur une période donnée
    @Query("SELECT SUM(d.totalTimePlayed) FROM DailyGameSession d WHERE d.user = :user AND d.date BETWEEN :startDate AND :endDate")
    Integer calculatePlaytimeByUserInPeriod(
            @Param("user") User user,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );

    // Obtenir le nombre total de sessions de jeu pour un utilisateur
    @Query("SELECT COUNT(d) FROM DailyGameSession d WHERE d.user = :user")
    Long countSessionsByUser(@Param("user") User user);

    // Obtenir le nombre total de jeux joués par un utilisateur
    @Query("SELECT SUM(d.gamesPlayed) FROM DailyGameSession d WHERE d.user = :user")
    Integer countGamesPlayedByUser(@Param("user") User user);

    // Récupérer la session avec le plus de temps de jeu pour un utilisateur
    @Query("SELECT d FROM DailyGameSession d WHERE d.user = :user ORDER BY d.totalTimePlayed DESC")
    List<DailyGameSession> findLongestSessionsByUser(@Param("user") User user);

    // Récupérer les N dernières sessions d'un utilisateur
    @Query("SELECT d FROM DailyGameSession d WHERE d.user = :user ORDER BY d.date DESC")
    List<DailyGameSession> findRecentSessionsByUser(@Param("user") User user);

    // Calculer la moyenne quotidienne de jeu pour un utilisateur
    @Query("SELECT AVG(d.totalTimePlayed) FROM DailyGameSession d WHERE d.user = :user")
    Double calculateAveragePlaytimeByUser(@Param("user") User user);

    // Récupérer les utilisateurs les plus actifs (en termes de temps de jeu total)
    @Query("SELECT d.user, SUM(d.totalTimePlayed) as totalTime FROM DailyGameSession d GROUP BY d.user ORDER BY totalTime DESC")
    List<Object[]> findMostActiveUsers();
}
