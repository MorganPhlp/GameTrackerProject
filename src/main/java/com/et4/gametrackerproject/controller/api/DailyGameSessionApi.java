package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.DailyGameSessionDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface DailyGameSessionApi {

    //Opérations de base

    @GetMapping(value = APP_ROOT + "/sessions/{idSession}", produces = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto getSessionById(@PathVariable("idSession") Integer id);

    @PostMapping(value = APP_ROOT + "/sessions/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto createSession(@RequestBody DailyGameSessionDto sessionDto);

    @PutMapping(value = APP_ROOT + "/sessions/update/{idSession}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto updateSession(@PathVariable("idSession") Integer id,@RequestBody DailyGameSessionDto sessionDto);

    @DeleteMapping(value = APP_ROOT + "/sessions/delete/{idSession}")
    void deleteSession(@PathVariable("idSession") Integer id);

    //Récupération des sessions

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto getSessionByUserAndDate(@PathVariable Integer userId,@PathVariable Instant date);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DailyGameSessionDto> getSessionsForUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/between/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DailyGameSessionDto> getSessionsForUserBetweenDates(@PathVariable Integer userId,@PathVariable Instant start,@PathVariable Instant end);

    //Statistiques de jeu

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/total", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer calculateTotalPlayTimeForUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/average", produces = MediaType.APPLICATION_JSON_VALUE)
    Double calculateDailyAveragePlayTime(@PathVariable Integer userId);

    //Gestion des sessions
    
    @PutMapping(value = APP_ROOT + "/sessions/user/{userId}/game/{gameId}/record/{duration}", produces = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto recordPlaySession(@PathVariable Integer userId,@PathVariable Integer gameId,@PathVariable("duration") Integer durationSeconds);
    
    @PutMapping(value = APP_ROOT + "/sessions/user/{userId}/game/{gameId}/increment", produces = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto incrementGamesPlayed(@PathVariable Integer userId,@PathVariable Integer gameId);
    
    @PutMapping(value = APP_ROOT + "/sessions/user/{userId}/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    void resetDailySession(@PathVariable Integer userId);

    //Méthodes de rapport
    
    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/weekly", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> generateWeeklyReport(@PathVariable Integer userId);
    
    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/monthly", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> generateMonthlyReport(@PathVariable Integer userId);

    //Administration


    @PutMapping(value = APP_ROOT + "/sessions/recalculate/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void recalculateSessionStats(@PathVariable("sessionId") Integer sessionId);

    @DeleteMapping(value = APP_ROOT + "/sessions/clean/{daysThreshold}")
    void cleanOldSessions(@PathVariable int daysThreshold);

    //Utilitaires

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/date/{date}/exists", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean hasSessionForDate(@PathVariable Integer userId,@PathVariable Instant date);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/last", produces = MediaType.APPLICATION_JSON_VALUE)
    Instant getLastPlayedDate(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/first/{timestamp}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isFirstSessionOfDay(@PathVariable Integer userId,@PathVariable Instant timestamp);
}
