package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.DailyGameSessionDto;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface DailyGameSessionService {

    //Opérations de base
    DailyGameSessionDto getSessionById(Integer id);
    DailyGameSessionDto createSession(DailyGameSessionDto sessionDto);
    DailyGameSessionDto updateSession(Integer id, DailyGameSessionDto sessionDto);
    void deleteSession(Integer id);

    //Récupération des sessions
    DailyGameSessionDto getSessionByUserAndDate(Integer userId, Instant date);
    List<DailyGameSessionDto> getSessionsForUser(Integer userId);
    List<DailyGameSessionDto> getSessionsForUserBetweenDates(Integer userId, Instant start, Instant end);

    //Statistiques de jeu
    Integer calculateTotalPlayTimeForUser(Integer userId);
    Double calculateDailyAveragePlayTime(Integer userId);

    //Gestion des sessions
    DailyGameSessionDto recordPlaySession(Integer userId, Integer gameId, Integer durationSeconds);
    DailyGameSessionDto incrementGamesPlayed(Integer userId, Integer gameId);
    void resetDailySession(Integer userId);

    //Méthodes de rapport
    Map<String, Object> generateWeeklyReport(Integer userId);
    Map<String, Object> generateMonthlyReport(Integer userId);

    //Administration
    void recalculateSessionStats(Integer sessionId);
    void cleanOldSessions(int daysThreshold);

    //Utilitaires
    boolean hasSessionForDate(Integer userId, Instant date);
    Instant getLastPlayedDate(Integer userId);
    boolean isFirstSessionOfDay(Integer userId, Instant timestamp);
}