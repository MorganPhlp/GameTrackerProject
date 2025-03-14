package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameProgressDto;
import com.et4.gametrackerproject.enums.GameStatus;

import java.util.List;
import java.util.Map;

public interface GameProgressService {

    // Opérations de base
    GameProgressDto createOrUpdateProgress(GameProgressDto progressDto);
    GameProgressDto getProgressById(Integer progressId);
    void deleteProgress(Integer progressId);

    //Gestion de la progression
    GameProgressDto startNewGameSession(Integer userId, Integer gameId);
    GameProgressDto updateGameplaySession(Integer progressId, Integer scoreDelta, Integer timeDelta);
    GameProgressDto completeGame(Integer progressId);
    GameProgressDto resetProgress(Integer progressId);

    //Suivi des performances
    GameProgressDto recordAttempt(Integer progressId, boolean won);
    GameProgressDto updateBestScore(Integer progressId, Integer newScore);
    GameProgressDto incrementStreak(Integer progressId);
    GameProgressDto resetStreak(Integer progressId);

    // Récupération des données
    GameProgressDto getCurrentProgress(Integer userId, Integer gameId);
    List<GameProgressDto> getAllUserProgress(Integer userId);
    List<GameProgressDto> getCompletedGames(Integer userId);

    //Statistiques
    Map<String, Number> getGameStatistics(Integer userId, Integer gameId);
    Integer getTotalPlayTimeForGame(Integer userId, Integer gameId);
    Double getWinLossRatio(Integer userId, Integer gameId);
    Map<Integer, Integer> getBestScoresForUser(Integer userId);

    //Synchronisation des données
    GameProgressDto saveProgressData(Integer progressId, String progressData);
    String loadProgressData(Integer progressId);
    boolean validateProgressData(String progressData);

    //Gestion des états
    GameProgressDto transitionStatus(Integer progressId, GameStatus newStatus);
    boolean hasCompletedGame(Integer userId, Integer gameId);
    boolean hasStartedGame(Integer userId, Integer gameId);
}