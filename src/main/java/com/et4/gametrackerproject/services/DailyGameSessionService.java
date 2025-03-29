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



    DailyGameSessionDto getSessionByUserAndDate(User user, Instant date);

    List<DailyGameSessionDto> getSessionsForUser(User user);

    List<DailyGameSessionDto> getSessionsForUserBetweenDates(Integer userId, Instant start, Instant end);


    List<DailyGameSessionDto> getSessionByDate(Instant date);


    Map<UserDto, Long> getMostActiveUsers();

    Integer calculateTotalPlaytimeByUser(User user);

    Integer calculatePlaytimeByUserInPeriod(User user, Instant startDate, Instant endDate);

    Long countSessionsByUser(User user);

    Integer countGamesPlayedByUser(User user);

    DailyGameSessionDto getLongestSessionForUser(User user);

    List<DailyGameSessionDto> getRecentSessionsForUser(User user, int limit);

    Double calculateAveragePlaytimeByUser(User user);

    //Utilitaires
    Instant getLastPlayedDate(Integer userId);

}