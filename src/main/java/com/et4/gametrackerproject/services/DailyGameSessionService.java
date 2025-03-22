package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.DailyGameSessionDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.model.User;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface DailyGameSessionService {

    //Opérations de base
    DailyGameSessionDto getSessionById(Integer id);
    DailyGameSessionDto createSession(DailyGameSessionDto sessionDto);
    DailyGameSessionDto updateSession(Integer id, DailyGameSessionDto sessionDto);
    void deleteSession(Integer id);

    List<DailyGameSessionDto> getSessionByDate(Instant date);


    DailyGameSessionDto getSessionByUserAndDate(User user, Instant date);

    List<DailyGameSessionDto> getSessionsForUser(User user);

    List<DailyGameSessionDto> getSessionsForUserBetweenDates(Integer userId, Instant start, Instant end);

    //Statistiques de jeu

    Map<UserDto, Long> getMostActiveUsers();

    Integer calculateTotalPlaytimeByUser(User user);

    Integer calculatePlaytimeByUserInPeriod(User user, Instant startDate, Instant endDate);

    Long countSessionsByUser(User user);

    Integer countGamesPlayedByUser(User user);

    DailyGameSessionDto getLongestSessionForUser(User user);

    List<DailyGameSessionDto> getRecentSessionsForUser(User user, int limit);

    Double calculateAveragePlaytimeByUser(User user);

    Double calculateDailyAveragePlayTime(Integer userId);

    //Gestion des sessions
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