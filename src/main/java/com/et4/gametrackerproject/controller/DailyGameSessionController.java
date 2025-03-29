package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.DailyGameSessionApi;
import com.et4.gametrackerproject.dto.DailyGameSessionDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.model.User;
import com.et4.gametrackerproject.services.DailyGameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
public class DailyGameSessionController implements DailyGameSessionApi {

    @Autowired
    private DailyGameSessionService dailyGameSessionService;

    public DailyGameSessionController(DailyGameSessionService dailygamesessionservice) {
        this.dailyGameSessionService = dailygamesessionservice;
    }


    @Override
    public DailyGameSessionDto getSessionById(Integer id) {
        return dailyGameSessionService.getSessionById(id);
    }

    @Override
    public DailyGameSessionDto createSession(DailyGameSessionDto sessionDto) {
        return dailyGameSessionService.createSession(sessionDto);
    }

    @Override
    public DailyGameSessionDto updateSession(Integer id, DailyGameSessionDto sessionDto) {
        return dailyGameSessionService.updateSession(id, sessionDto);
    }

    @Override
    public void deleteSession(Integer id) {
        dailyGameSessionService.deleteSession(id);
    }

    @Override
    public DailyGameSessionDto getSessionByUserAndDate(User user, Instant date) {
        return dailyGameSessionService.getSessionByUserAndDate(user, date);
    }

    @Override
    public List<DailyGameSessionDto> getSessionsForUser(User user) {
        return dailyGameSessionService.getSessionsForUser(user);
    }

    @Override
    public List<DailyGameSessionDto> getSessionsForUserBetweenDates(Integer userId, Instant start, Instant end) {
        return dailyGameSessionService.getSessionsForUserBetweenDates(userId, start, end);
    }


    @Override
    public Instant getLastPlayedDate(Integer userId) {
        return dailyGameSessionService.getLastPlayedDate(userId);
    }

    @Override
    public List<DailyGameSessionDto> getSessionByDate(Instant date) {
        return dailyGameSessionService.getSessionByDate(date);
    }

    @Override
    public Map<UserDto, Long> getMostActiveUsers() {
        return dailyGameSessionService.getMostActiveUsers();
    }

    @Override
    public Integer calculateTotalPlaytimeByUser(User user) {
        return dailyGameSessionService.calculateTotalPlaytimeByUser(user);
    }

    @Override
    public Integer calculatePlaytimeByUserInPeriod(User user, Instant startDate, Instant endDate) {
        return dailyGameSessionService.calculatePlaytimeByUserInPeriod(user, startDate, endDate);
    }

    @Override
    public Long countSessionsByUser(User user) {
        return dailyGameSessionService.countSessionsByUser(user);
    }

    @Override
    public Integer countGamesPlayedByUser(User user) {
        return dailyGameSessionService.countGamesPlayedByUser(user);
    }

    @Override
    public DailyGameSessionDto getLongestSessionForUser(User user) {
        return dailyGameSessionService.getLongestSessionForUser(user);
    }

    @Override
    public List<DailyGameSessionDto> getRecentSessionsForUser(User user, int limit) {
        return dailyGameSessionService.getRecentSessionsForUser(user, limit);
    }

    @Override
    public Double calculateAveragePlaytimeByUser(User user) {
        return dailyGameSessionService.calculateAveragePlaytimeByUser(user);
    }


}
