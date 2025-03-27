package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.DailyGameSessionApi;
import com.et4.gametrackerproject.dto.DailyGameSessionDto;
import com.et4.gametrackerproject.model.User;
import com.et4.gametrackerproject.services.DailyGameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

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


}
