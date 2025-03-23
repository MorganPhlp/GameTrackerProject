package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.model.DailyGameSession;
import com.et4.gametrackerproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface DailyGameSessionRepository extends JpaRepository<DailyGameSession,Integer> {

    //=============================================Recherche==========================================
    List<DailyGameSession> findByDate(Instant date);
    List<DailyGameSession> findByUser(User user);

    @Query("SELECT d FROM DailyGameSession d WHERE d.user.id = :#{#user.id} AND d.date = :date")
    Optional<DailyGameSession> findByUserAndDate(@Param("user") UserDto user, @Param("date") Instant date);

    @Query("SELECT d FROM DailyGameSession d WHERE d.user.id = :userId AND d.date BETWEEN :start AND :end")
    List<DailyGameSession> findByUserBetweenDates(@Param("userId") Integer userId,
                                                  @Param("start") Instant start,
                                                  @Param("end") Instant end);

    // Récupérer la session avec le plus de temps de jeu pour un utilisateur
    @Query("SELECT d FROM DailyGameSession d WHERE d.user = :user ORDER BY d.totalTimePlayed DESC")
    List<DailyGameSession> findLongestSessionsByUser(@Param("user") User user);

    // Récupérer les N dernières sessions d'un utilisateur
    @Query("SELECT d FROM DailyGameSession d WHERE d.user = :user ORDER BY d.date DESC")
    List<DailyGameSession> findRecentSessionsByUser(@Param("user") User user);

    // Récupérer les utilisateurs les plus actifs (en termes de temps de jeu total)
    @Query("SELECT d.user, SUM(d.totalTimePlayed) as totalTime FROM DailyGameSession d GROUP BY d.user ORDER BY totalTime DESC")
    List<Object[]> findMostActiveUsers();

    @Query("SELECT MAX(d.date) FROM DailyGameSession d WHERE d.user.id = :userId")
    Instant findLastPlayedDateByUserId(@Param("userId") Integer userId);


    //=============================================CALCULS==========================================

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

    // Calculer la moyenne quotidienne de jeu pour un utilisateur
    @Query("SELECT AVG(d.totalTimePlayed) FROM DailyGameSession d WHERE d.user = :user")
    Double calculateAveragePlaytimeByUser(@Param("user") User user);



}
