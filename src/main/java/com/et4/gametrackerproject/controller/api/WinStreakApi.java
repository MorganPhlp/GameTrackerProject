package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.WinStreakDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface WinStreakApi {

    // Opérations de base

    @PostMapping(value = APP_ROOT + "/streaks/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    WinStreakDto createOrUpdateStreak(@RequestBody WinStreakDto streakDto);

    @GetMapping(value = APP_ROOT + "/streaks/{streakId}", produces = MediaType.APPLICATION_JSON_VALUE)
    WinStreakDto getStreakById(@PathVariable Integer streakId);

    @DeleteMapping(value = APP_ROOT + "/streaks/delete/{streakId}")
    void resetStreak(@PathVariable Integer streakId);

    // Gestion des séries

    @PutMapping(value = APP_ROOT + "/streaks/increment/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    WinStreakDto incrementStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @PutMapping(value = APP_ROOT + "/streaks/reset/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    WinStreakDto resetCurrentStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @PutMapping(value = APP_ROOT + "/streaks/update/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    WinStreakDto updateBestStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/streaks/current/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    WinStreakDto getCurrentStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/streaks/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<WinStreakDto> getStreaksByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/streaks/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<WinStreakDto> getStreaksByGame(@PathVariable Integer gameId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/streaks/active", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<WinStreakDto> getActiveStreaks(Pageable pageable);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/streaks/global/best/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Integer> getGlobalBestStreaks(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/streaks/game/best/{gameId}/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Integer> getGameBestStreaks(@PathVariable Integer gameId,@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/streaks/user/ranking/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer getUserRanking(@PathVariable Integer userId,@PathVariable Integer gameId);

    // Vérifications

    @GetMapping(value = APP_ROOT + "/streaks/active/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isActiveStreak(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/streaks/reward/eligible/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isEligibleForReward(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/streaks/validate/{streakId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean validateStreakConsistency(@PathVariable Integer streakId);

    // Batch operations

    @PutMapping(value = APP_ROOT + "/streaks/batch/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void batchUpdateStreaks(@RequestBody List<WinStreakDto> streaks);

    @DeleteMapping(value = APP_ROOT + "/streaks/reset/all/{gameId}")
    void resetAllStreaksForGame(@PathVariable Integer gameId);

    // Intégration

    @PutMapping(value = APP_ROOT + "/streaks/notify/{streakId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void notifyStreakMilestone(@PathVariable Integer streakId);

    @PutMapping(value = APP_ROOT + "/streaks/user/{userId}/game/{gameId}/ranking/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateUserRanking(@PathVariable Integer userId,@PathVariable Integer gameId);

    // Sécurité

    @GetMapping(value = APP_ROOT + "/streaks/anomalies/detect/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void detectAnomalousStreaks(@PathVariable Integer gameId);
}
