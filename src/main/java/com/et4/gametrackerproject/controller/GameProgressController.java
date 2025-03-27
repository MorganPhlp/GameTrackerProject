package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.GameApi;
import com.et4.gametrackerproject.controller.api.GameProgressApi;
import com.et4.gametrackerproject.dto.GameProgressDto;
import com.et4.gametrackerproject.enums.GameStatus;
import com.et4.gametrackerproject.services.GameProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GameProgressController implements GameProgressApi {

    @Autowired
    private GameProgressService gameProgressService;

    GameProgressController(GameProgressService gameProgressService) {
        this.gameProgressService = gameProgressService;
    }


    @Override
    public GameProgressDto createOrUpdateProgress(GameProgressDto progressDto) {
        return gameProgressService.createOrUpdateProgress(progressDto);
    }

    @Override
    public GameProgressDto getProgressById(Integer progressId) {
        return gameProgressService.getProgressById(progressId);
    }

    @Override
    public void deleteProgress(Integer progressId) {
        gameProgressService.deleteProgress(progressId);
    }

    @Override
    public GameProgressDto startNewGameSession(Integer userId, Integer gameId) {
        return gameProgressService.startNewGameSession(userId, gameId);
    }

    @Override
    public GameProgressDto updateGameplaySession(Integer progressId, Integer scoreDelta, Integer timeDelta) {
        return gameProgressService.updateGameplaySession(progressId, scoreDelta, timeDelta);
    }

    @Override
    public GameProgressDto completeGame(Integer progressId) {
        return gameProgressService.completeGame(progressId);
    }

    @Override
    public GameProgressDto resetProgress(Integer progressId) {
        return gameProgressService.resetProgress(progressId);
    }

    @Override
    public GameProgressDto recordAttempt(Integer progressId, boolean won) {
        return gameProgressService.recordAttempt(progressId, won);
    }

    @Override
    public GameProgressDto updateBestScore(Integer progressId, Integer newScore) {
        return gameProgressService.updateBestScore(progressId, newScore);
    }

    @Override
    public GameProgressDto incrementStreak(Integer progressId) {
        return gameProgressService.incrementStreak(progressId);
    }

    @Override
    public GameProgressDto resetStreak(Integer progressId) {
        return gameProgressService.resetStreak(progressId);
    }

    @Override
    public GameProgressDto getCurrentProgress(Integer userId, Integer gameId) {
        return gameProgressService.getCurrentProgress(userId, gameId);
    }

    @Override
    public List<GameProgressDto> getAllUserProgress(Integer userId) {
        return gameProgressService.getAllUserProgress(userId);
    }
}
