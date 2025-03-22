package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.WinStreakDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface WinStreakService {

    // Opérations de base
    WinStreakDto createOrUpdateStreak(WinStreakDto streakDto);
    WinStreakDto getStreakById(Integer streakId);
    void resetStreak(Integer streakId);

    // Gestion des séries
    WinStreakDto incrementStreak(Integer userId, Integer gameId);
    WinStreakDto resetCurrentStreak(Integer userId, Integer gameId);
    WinStreakDto updateBestStreak(Integer userId, Integer gameId);

    // Récupération
    WinStreakDto getCurrentStreak(Integer userId, Integer gameId);
    Page<WinStreakDto> getStreaksByUser(Integer userId, Pageable pageable);
    Page<WinStreakDto> getStreaksByGame(Integer gameId, Pageable pageable);
    Page<WinStreakDto> getActiveStreaks(Pageable pageable);

    // Statistiques
    Map<Integer, Integer> getGlobalBestStreaks(int limit);
    Map<Integer, Integer> getGameBestStreaks(Integer gameId, int limit);

    // Vérifications
    boolean isActiveStreak(Integer userId, Integer gameId);

    // Batch operations
    void resetAllStreaksForGame(Integer gameId);
}