package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.DailyGameSessionDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.model.User;
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

    @GetMapping(value = APP_ROOT + "/sessions/user/{user}/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto getSessionByUserAndDate(@PathVariable User user, @PathVariable Instant date);

    @GetMapping(value = APP_ROOT + "/sessions/user/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DailyGameSessionDto> getSessionsForUser(@PathVariable User user);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/between/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DailyGameSessionDto> getSessionsForUserBetweenDates(@PathVariable Integer userId,@PathVariable Instant start,@PathVariable Instant end);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/last", produces = MediaType.APPLICATION_JSON_VALUE)
    Instant getLastPlayedDate(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/sessions/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DailyGameSessionDto> getSessionByDate(@PathVariable Instant date);

    @GetMapping(value = APP_ROOT + "/sessions/most-active-users", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<UserDto, Long> getMostActiveUsers();

    @GetMapping(value = APP_ROOT + "/sessions/total-playtime/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer calculateTotalPlaytimeByUser(@PathVariable User user);

    @GetMapping(value = APP_ROOT + "/sessions/playtime/{user}/between/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer calculatePlaytimeByUserInPeriod(@PathVariable User user, @PathVariable Instant start, @PathVariable Instant end);

    @GetMapping(value = APP_ROOT + "/sessions/count/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countSessionsByUser(@PathVariable User user);

    @GetMapping(value = APP_ROOT + "/sessions/games-played/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer countGamesPlayedByUser(@PathVariable User user);

    @GetMapping(value = APP_ROOT + "/sessions/longest/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    DailyGameSessionDto getLongestSessionForUser(@PathVariable User user);

    @GetMapping(value = APP_ROOT + "/sessions/recent/{user}/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DailyGameSessionDto> getRecentSessionsForUser(@PathVariable User user, @PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/sessions/average-playtime/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    Double calculateAveragePlaytimeByUser(@PathVariable User user);


}
