package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.GameLeaderboardDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import com.et4.gametrackerproject.services.GameLeaderboardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class GameLeaderboardServiceImpl implements GameLeaderboardService {
    @Override
    public GameLeaderboardDto submitScore(GameLeaderboardDto scoreEntry) {
        return null;
    }

    @Override
    public GameLeaderboardDto updateScore(Integer entryId, Integer newScore) {
        return null;
    }

    @Override
    public void deleteScoreEntry(Integer entryId) {

    }

    @Override
    public Page<GameLeaderboardDto> getLeaderboardForGame(Integer gameId, LeaderboardPeriod period, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameLeaderboardDto> getFullLeaderboard(LeaderboardPeriod period, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameLeaderboardDto> getHistoricalLeaderboard(Integer gameId, Instant date, Pageable pageable) {
        return null;
    }

    @Override
    public Integer getUserRank(Integer userId, Integer gameId, LeaderboardPeriod period) {
        return 0;
    }

    @Override
    public Map<LeaderboardPeriod, Integer> getUserRanksAcrossPeriods(Integer userId, Integer gameId) {
        return Map.of();
    }

    @Override
    public List<GameLeaderboardDto> getSurroundingEntries(Integer userId, Integer gameId, LeaderboardPeriod period, int range) {
        return List.of();
    }

    @Override
    public Map<Integer, Integer> getTopScoresForGame(Integer gameId, int limit) {
        return Map.of();
    }

    @Override
    public Map<String, Long> getScoreDistribution(Integer gameId, LeaderboardPeriod period) {
        return Map.of();
    }

    @Override
    public Map<LeaderboardPeriod, Integer> getPerformanceTrend(Integer userId, Integer gameId) {
        return Map.of();
    }

    @Override
    public void recalculateLeaderboard(Integer gameId, LeaderboardPeriod period) {

    }

    @Override
    public void resetLeaderboard(Integer gameId, LeaderboardPeriod period) {

    }

    @Override
    public void freezeLeaderboard(Integer gameId, LeaderboardPeriod period) {

    }

    @Override
    public void archiveLeaderboard(Integer gameId, LeaderboardPeriod period) {

    }

    @Override
    public boolean isScoreEligible(Integer gameId, Integer score) {
        return false;
    }

    @Override
    public boolean hasUserSubmittedScore(Integer userId, Integer gameId, LeaderboardPeriod period) {
        return false;
    }

    @Override
    public Page<GameLeaderboardDto> searchLeaderboardEntries(String query, Pageable pageable) {
        return null;
    }

    @Override
    public Map<Integer, Long> getLeaderboardParticipationStats(Integer gameId) {
        return Map.of();
    }

    @Override
    public Map<UserDto, Integer> getTopPerformersGlobal(int limit) {
        return Map.of();
    }

    @Override
    public void notifyRankChanges(Integer gameId, LeaderboardPeriod period) {

    }

    @Override
    public void updateAchievementsFromLeaderboard(Integer gameId) {

    }

    @Override
    public void schedulePeriodicLeaderboardRotation() {

    }

    @Override
    public Instant getCurrentPeriodEnd(LeaderboardPeriod period) {
        return null;
    }

    @Override
    public Instant getNextPeriodStart(LeaderboardPeriod period) {
        return null;
    }
}
