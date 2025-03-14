package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameLeaderboardDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameLeaderboardApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/leaderboard/submit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameLeaderboardDto submitScore(@RequestBody GameLeaderboardDto scoreEntry);

    @PutMapping(value = APP_ROOT + "/leaderboard/update/{entryId}/{newScore}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameLeaderboardDto updateScore(@PathVariable Integer entryId,@PathVariable Integer newScore);

    @DeleteMapping(value = APP_ROOT + "/leaderboard/delete/{entryId}")
    void deleteScoreEntry(@PathVariable Integer entryId);

    //Récupération des classements

    @GetMapping(value = APP_ROOT + "/leaderboard/game/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameLeaderboardDto> getLeaderboardForGame(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/leaderboard/full/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameLeaderboardDto> getFullLeaderboard(@PathVariable LeaderboardPeriod period, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/leaderboard/historical/{gameId}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameLeaderboardDto> getHistoricalLeaderboard(@PathVariable Integer gameId,@PathVariable Instant date, Pageable pageable);

    //Positions des joueurs

    @GetMapping(value = APP_ROOT + "/leaderboard/user/{userId}/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer getUserRank(@PathVariable Integer userId,@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    @GetMapping(value = APP_ROOT + "/leaderboard/user/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<LeaderboardPeriod, Integer> getUserRanksAcrossPeriods(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/leaderboard/surrounding/{userId}/{gameId}/{period}/{range}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameLeaderboardDto> getSurroundingEntries(@PathVariable Integer userId,@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period,@PathVariable int range);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/leaderboard/top/{gameId}/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Integer> getTopScoresForGame(@PathVariable Integer gameId,@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/leaderboard/distribution/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Long> getScoreDistribution(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    @GetMapping(value = APP_ROOT + "/leaderboard/performance/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<LeaderboardPeriod, Integer> getPerformanceTrend(@PathVariable Integer userId,@PathVariable Integer gameId);

    // Gestion des classements

    @PutMapping(value = APP_ROOT + "/leaderboard/recalculate/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    void recalculateLeaderboard(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    @PutMapping(value = APP_ROOT + "/leaderboard/reset/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    void resetLeaderboard(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    @PutMapping(value = APP_ROOT + "/leaderboard/freeze/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    void freezeLeaderboard(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    @PutMapping(value = APP_ROOT + "/leaderboard/archive/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    void archiveLeaderboard(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    //Vérifications

    @GetMapping(value = APP_ROOT + "/leaderboard/eligible/{gameId}/{score}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isScoreEligible(@PathVariable Integer gameId,@PathVariable Integer score);

    @GetMapping(value = APP_ROOT + "/leaderboard/submitted/{userId}/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasUserSubmittedScore(@PathVariable Integer userId,@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    //Administration

    @GetMapping(value = APP_ROOT + "/leaderboard/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameLeaderboardDto> searchLeaderboardEntries(@RequestBody String query, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/leaderboard/participation/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getLeaderboardParticipationStats(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/leaderboard/top/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<UserDto, Integer> getTopPerformersGlobal(@PathVariable int limit);

    //Intégration

    @PutMapping(value = APP_ROOT + "/leaderboard/notify/{gameId}/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    void notifyRankChanges(@PathVariable Integer gameId,@PathVariable LeaderboardPeriod period);

    @PutMapping(value = APP_ROOT + "/leaderboard/updateAchievements/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateAchievementsFromLeaderboard(@PathVariable Integer gameId);

    //Gestion du temps

    @PutMapping(value = APP_ROOT + "/leaderboard/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    void schedulePeriodicLeaderboardRotation();

    @GetMapping(value = APP_ROOT + "/leaderboard/periodEnd/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    Instant getCurrentPeriodEnd(@PathVariable LeaderboardPeriod period);

    @GetMapping(value = APP_ROOT + "/leaderboard/nextPeriod/{period}", produces = MediaType.APPLICATION_JSON_VALUE)
    Instant getNextPeriodStart(@PathVariable LeaderboardPeriod period);
}
