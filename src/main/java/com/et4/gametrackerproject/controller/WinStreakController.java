package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.WinStreakApi;
import com.et4.gametrackerproject.dto.WinStreakDto;
import com.et4.gametrackerproject.services.WinStreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WinStreakController implements WinStreakApi {

    @Autowired
    private final WinStreakService winStreakService;

    public WinStreakController(WinStreakService winStreakService) {
        this.winStreakService = winStreakService;
    }

    @Override
    public WinStreakDto createOrUpdateStreak(WinStreakDto streakDto) {
        return winStreakService.createOrUpdateStreak(streakDto);
    }

    @Override
    public WinStreakDto getStreakById(Integer streakId) {
        return winStreakService.getStreakById(streakId);
    }

    @Override
    public void resetStreak(Integer streakId) {
        winStreakService.resetStreak(streakId);
    }

    @Override
    public WinStreakDto incrementStreak(Integer userId, Integer gameId) {
        return winStreakService.incrementStreak(userId, gameId);
    }

    @Override
    public WinStreakDto resetCurrentStreak(Integer userId, Integer gameId) {
        return winStreakService.resetCurrentStreak(userId, gameId);
    }

    @Override
    public WinStreakDto updateBestStreak(Integer userId, Integer gameId) {
        return winStreakService.updateBestStreak(userId, gameId);
    }

    @Override
    public WinStreakDto getCurrentStreak(Integer userId, Integer gameId) {
        return winStreakService.getCurrentStreak(userId, gameId);
    }

    @Override
    public Page<WinStreakDto> getStreaksByUser(Integer userId, Pageable pageable) {
        return winStreakService.getStreaksByUser(userId, pageable);
    }

    @Override
    public Page<WinStreakDto> getStreaksByGame(Integer gameId, Pageable pageable) {
        return winStreakService.getStreaksByGame(gameId, pageable);
    }

    @Override
    public Page<WinStreakDto> getActiveStreaks(Pageable pageable) {
        return winStreakService.getActiveStreaks(pageable);
    }

    @Override
    public Map<Integer, Integer> getGlobalBestStreaks(int limit) {
        return winStreakService.getGlobalBestStreaks(limit);
    }

    @Override
    public Map<Integer, Integer> getGameBestStreaks(Integer gameId, int limit) {
        return winStreakService.getGameBestStreaks(gameId, limit);
    }

    @Override
    public boolean isActiveStreak(Integer userId, Integer gameId) {
        return winStreakService.isActiveStreak(userId, gameId);
    }

    @Override
    public void resetAllStreaksForGame(Integer gameId) {
        winStreakService.resetAllStreaksForGame(gameId);
    }

}
