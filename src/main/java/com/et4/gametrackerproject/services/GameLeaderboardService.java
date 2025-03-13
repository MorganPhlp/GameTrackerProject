package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameLeaderboardDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface GameLeaderboardService {

    //Opérations de base
    GameLeaderboardDto submitScore(GameLeaderboardDto scoreEntry);
    GameLeaderboardDto updateScore(Integer entryId, Integer newScore);
    void deleteScoreEntry(Integer entryId);

    //Récupération des classements
    Page<GameLeaderboardDto> getLeaderboardForGame(Integer gameId, LeaderboardPeriod period, Pageable pageable);
    Page<GameLeaderboardDto> getFullLeaderboard(LeaderboardPeriod period, Pageable pageable);
    Page<GameLeaderboardDto> getHistoricalLeaderboard(Integer gameId, Instant date, Pageable pageable);

    //Positions des joueurs
    Integer getUserRank(Integer userId, Integer gameId, LeaderboardPeriod period);
    Map<LeaderboardPeriod, Integer> getUserRanksAcrossPeriods(Integer userId, Integer gameId);
    List<GameLeaderboardDto> getSurroundingEntries(Integer userId, Integer gameId, LeaderboardPeriod period, int range);

    // Statistiques
    Map<Integer, Integer> getTopScoresForGame(Integer gameId, int limit);
    Map<String, Long> getScoreDistribution(Integer gameId, LeaderboardPeriod period);
    Map<LeaderboardPeriod, Integer> getPerformanceTrend(Integer userId, Integer gameId);

    // Gestion des classements
    void recalculateLeaderboard(Integer gameId, LeaderboardPeriod period);
    void resetLeaderboard(Integer gameId, LeaderboardPeriod period);
    void freezeLeaderboard(Integer gameId, LeaderboardPeriod period);
    void archiveLeaderboard(Integer gameId, LeaderboardPeriod period);

    //Vérifications
    boolean isScoreEligible(Integer gameId, Integer score);
    boolean hasUserSubmittedScore(Integer userId, Integer gameId, LeaderboardPeriod period);

    //Administration
    Page<GameLeaderboardDto> searchLeaderboardEntries(String query, Pageable pageable);
    Map<Integer, Long> getLeaderboardParticipationStats(Integer gameId);
    Map<UserDto, Integer> getTopPerformersGlobal(int limit);

    //Intégration
    void notifyRankChanges(Integer gameId, LeaderboardPeriod period);
    void updateAchievementsFromLeaderboard(Integer gameId);

    //Gestion du temps
    void schedulePeriodicLeaderboardRotation();
    Instant getCurrentPeriodEnd(LeaderboardPeriod period);
    Instant getNextPeriodStart(LeaderboardPeriod period);

}