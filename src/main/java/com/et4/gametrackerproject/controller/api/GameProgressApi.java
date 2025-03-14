package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameProgressDto;
import com.et4.gametrackerproject.enums.GameStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameProgressApi {

    // Opérations de base
    @PostMapping(value = APP_ROOT + "/progress/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto createOrUpdateProgress(@RequestBody GameProgressDto progressDto);

    @GetMapping(value = APP_ROOT + "/progress/{progressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto getProgressById(@PathVariable("progressId") Integer progressId);

    @DeleteMapping(value = APP_ROOT + "/progress/{progressId}")
    void deleteProgress(@PathVariable("progressId") Integer progressId);

    //Gestion de la progression
    @PostMapping(value = APP_ROOT + "/progress/start", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto startNewGameSession(@RequestParam("userId") Integer userId, @RequestParam("gameId") Integer gameId);

    @PutMapping(value = APP_ROOT + "/progress/{progressId}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto updateGameplaySession(@PathVariable("progressId") Integer progressId, @RequestParam("scoreDelta") Integer scoreDelta, @RequestParam("timeDelta") Integer timeDelta);

    @PutMapping(value = APP_ROOT + "/progress/{progressId}/complete", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto completeGame(@PathVariable("progressId") Integer progressId);

    @PutMapping(value = APP_ROOT + "/progress/{progressId}/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto resetProgress(@PathVariable("progressId") Integer progressId);

    //Suivi des performances
    @PutMapping(value = APP_ROOT + "/progress/{progressId}/attempt", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto recordAttempt(@PathVariable("progressId") Integer progressId, @RequestParam("won") boolean won);

    @PutMapping(value = APP_ROOT + "/progress/{progressId}/score", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto updateBestScore(@PathVariable("progressId") Integer progressId, @RequestParam("newScore") Integer newScore);

    @PutMapping(value = APP_ROOT + "/progress/{progressId}/streak/increment", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto incrementStreak(@PathVariable("progressId") Integer progressId);

    @PutMapping(value = APP_ROOT + "/progress/{progressId}/streak/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto resetStreak(@PathVariable("progressId") Integer progressId);

    // Récupération des données
    @GetMapping(value = APP_ROOT + "/progress/current", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto getCurrentProgress(@RequestParam("userId") Integer userId, @RequestParam("gameId") Integer gameId);

    @GetMapping(value = APP_ROOT + "/progress/user/{userId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameProgressDto> getAllUserProgress(@PathVariable("userId") Integer userId);

    @GetMapping(value = APP_ROOT + "/progress/user/{userId}/completed", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameProgressDto> getCompletedGames(@PathVariable("userId") Integer userId);

    //Statistiques
    @GetMapping(value = APP_ROOT + "/progress/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Number> getGameStatistics(@RequestParam("userId") Integer userId, @RequestParam("gameId") Integer gameId);

    @GetMapping(value = APP_ROOT + "/progress/playtime", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer getTotalPlayTimeForGame(@RequestParam("userId") Integer userId, @RequestParam("gameId") Integer gameId);

    @GetMapping(value = APP_ROOT + "/progress/winloss", produces = MediaType.APPLICATION_JSON_VALUE)
    Double getWinLossRatio(@RequestParam("userId") Integer userId, @RequestParam("gameId") Integer gameId);

    @GetMapping(value = APP_ROOT + "/progress/user/{userId}/bestscores", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Integer> getBestScoresForUser(@PathVariable("userId") Integer userId);

    //Synchronisation des données
    @PutMapping(value = APP_ROOT + "/progress/{progressId}/data", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto saveProgressData(@PathVariable("progressId") Integer progressId, @RequestBody String progressData);

    @GetMapping(value = APP_ROOT + "/progress/{progressId}/data", produces = MediaType.APPLICATION_JSON_VALUE)
    String loadProgressData(@PathVariable("progressId") Integer progressId);

    @PostMapping(value = APP_ROOT + "/progress/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean validateProgressData(@RequestBody String progressData);

    //Gestion des états
    @PutMapping(value = APP_ROOT + "/progress/{progressId}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    GameProgressDto transitionStatus(@PathVariable("progressId") Integer progressId, @RequestParam("newStatus") GameStatus newStatus);

    @GetMapping(value = APP_ROOT + "/progress/completed", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasCompletedGame(@RequestParam("userId") Integer userId, @RequestParam("gameId") Integer gameId);

    @GetMapping(value = APP_ROOT + "/progress/started", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasStartedGame(@RequestParam("userId") Integer userId, @RequestParam("gameId") Integer gameId);
}