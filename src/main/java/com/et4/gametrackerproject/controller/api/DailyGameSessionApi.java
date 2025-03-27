package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.DailyGameSessionDto;
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

    DailyGameSessionDto getSessionByUserAndDate(User user, Instant date);

    List<DailyGameSessionDto> getSessionsForUser(User user);

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/between/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<DailyGameSessionDto> getSessionsForUserBetweenDates(@PathVariable Integer userId,@PathVariable Instant start,@PathVariable Instant end);

    //Statistiques de jeu

    @GetMapping(value = APP_ROOT + "/sessions/user/{userId}/last", produces = MediaType.APPLICATION_JSON_VALUE)
    Instant getLastPlayedDate(@PathVariable Integer userId);

    }
