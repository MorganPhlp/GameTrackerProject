package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.DailyGameSessionDto;
import com.et4.gametrackerproject.services.DailyGameSessionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class DailyGameSessionServiceImpl implements DailyGameSessionService {
    @Override
    public DailyGameSessionDto getSessionById(Integer id) {
        return null;
    }

    @Override
    public DailyGameSessionDto createSession(DailyGameSessionDto sessionDto) {
        return null;
    }

    @Override
    public DailyGameSessionDto updateSession(Integer id, DailyGameSessionDto sessionDto) {
        return null;
    }

    @Override
    public void deleteSession(Integer id) {

    }

    @Override
    public DailyGameSessionDto getSessionByUserAndDate(Integer userId, Instant date) {
        return null;
    }

    @Override
    public List<DailyGameSessionDto> getSessionsForUser(Integer userId) {
        return List.of();
    }

    @Override
    public List<DailyGameSessionDto> getSessionsForUserBetweenDates(Integer userId, Instant start, Instant end) {
        return List.of();
    }

    @Override
    public Integer calculateTotalPlayTimeForUser(Integer userId) {
        return 0;
    }

    @Override
    public Double calculateDailyAveragePlayTime(Integer userId) {
        return 0.0;
    }

    @Override
    public Map<String, Integer> getPlayTimeDistribution(Integer userId) {
        return Map.of();
    }

    @Override
    public DailyGameSessionDto recordPlaySession(Integer userId, Integer gameId, Integer durationSeconds) {
        return null;
    }

    @Override
    public DailyGameSessionDto incrementGamesPlayed(Integer userId, Integer gameId) {
        return null;
    }

    @Override
    public void resetDailySession(Integer userId) {

    }

    @Override
    public Map<String, Object> generateWeeklyReport(Integer userId) {
        return Map.of();
    }

    @Override
    public Map<String, Object> generateMonthlyReport(Integer userId) {
        return Map.of();
    }

    @Override
    public void recalculateSessionStats(Integer sessionId) {

    }

    @Override
    public void cleanOldSessions(int daysThreshold) {

    }

    @Override
    public boolean hasSessionForDate(Integer userId, Instant date) {
        return false;
    }

    @Override
    public Instant getLastPlayedDate(Integer userId) {
        return null;
    }

    @Override
    public boolean isFirstSessionOfDay(Integer userId, Instant timestamp) {
        return false;
    }
}
