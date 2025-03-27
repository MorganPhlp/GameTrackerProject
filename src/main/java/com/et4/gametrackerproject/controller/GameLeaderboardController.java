package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.GameLeaderboardApi;
import com.et4.gametrackerproject.dto.GameLeaderboardDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.enums.LeaderboardPeriod;
import com.et4.gametrackerproject.services.GameLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
public class GameLeaderboardController implements GameLeaderboardApi {

    @Autowired
    private GameLeaderboardService gameLeaderboardService;

    public GameLeaderboardController(GameLeaderboardService gameLeaderboardService) {
        this.gameLeaderboardService = gameLeaderboardService;
    }


    @Override
    public GameLeaderboardDto submitScore(GameLeaderboardDto scoreEntry) {
        return gameLeaderboardService.submitScore(scoreEntry);
    }

    @Override
    public GameLeaderboardDto updateScore(Integer entryId, Integer newScore) {
        return gameLeaderboardService.updateScore(entryId, newScore);
    }

    @Override
    public void deleteScoreEntry(Integer entryId) {
        gameLeaderboardService.deleteScoreEntry(entryId);
    }

    @Override
    public Page<GameLeaderboardDto> getLeaderboardForGame(Integer gameId, LeaderboardPeriod period, Pageable pageable) {
        return gameLeaderboardService.getLeaderboardForGame(gameId, period, pageable);
    }

    @Override
    public void resetLeaderboard(Integer gameId, LeaderboardPeriod period) {
        gameLeaderboardService.resetLeaderboard(gameId, period);
    }
}
