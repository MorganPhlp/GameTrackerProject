package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.WinStreakDto;
import com.et4.gametrackerproject.exception.EntityNotFoundException;
import com.et4.gametrackerproject.exception.ErrorCodes;
import com.et4.gametrackerproject.exception.InvalidEntityException;
import com.et4.gametrackerproject.model.WinStreak;
import com.et4.gametrackerproject.repository.GameRepository;
import com.et4.gametrackerproject.repository.UserRepository;
import com.et4.gametrackerproject.repository.WinStreakRepository;
import com.et4.gametrackerproject.services.WinStreakService;
import com.et4.gametrackerproject.validator.WinStreakValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class WinStreakServiceImpl implements WinStreakService {

    private final WinStreakRepository winStreakRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public WinStreakServiceImpl(WinStreakRepository winStreakRepository, UserRepository userRepository, GameRepository gameRepository) {
        this.winStreakRepository = winStreakRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public WinStreakDto createOrUpdateStreak(WinStreakDto dto) {
        List<String> errors = WinStreakValidator.validate(dto);
        if(!errors.isEmpty()) {
            log.error("WinStreak is not valid {}", dto);
            throw new InvalidEntityException("La WinStreak n'est pas valide", ErrorCodes.WIN_STREAK_NOT_VALID, errors);
        }

        log.info("Create WinStreak {}", dto);

        return WinStreakDto.fromEntity(
            winStreakRepository.save(
                WinStreakDto.toEntity(dto)
            )
        );
    }

    @Override
    public WinStreakDto getStreakById(Integer streakId) {
        if(streakId == null) {
            log.error("WinStreak ID is null");
            return null;
        }

        Optional<WinStreak> winStreak = winStreakRepository.findById(streakId);

        log.info("WinStreak : {}", winStreak);

        return Optional.of(WinStreakDto.fromEntity(winStreak.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune WinStreak avec l'ID = " + streakId + " n'a été trouvé dans la BDD",
                        ErrorCodes.WIN_STREAK_NOT_FOUND)
        );
    }

    @Override
    public void resetStreak(Integer streakId) {
        if(streakId == null) {
            log.error("WinStreak ID is null");
            throw new InvalidEntityException("ID de WinStreak invalide", ErrorCodes.WIN_STREAK_NOT_FOUND);
        }

        WinStreak streak = winStreakRepository.findById(streakId).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune WinStreak avec l'ID = " + streakId + " n'a été trouvé dans la BDD",
                        ErrorCodes.WIN_STREAK_NOT_FOUND)
        );

        streak.setCurrentStreak(0);
        streak.setBestStreak(0);
        streak.setLastWin(null);
        winStreakRepository.save(streak);

        log.info("WinStreak avec l'ID = {} a été réinitialisé", streakId);
    }

    @Override
    public WinStreakDto incrementStreak(Integer userId, Integer gameId) {
        if(userId == null || gameId == null) {
            log.error("User ID or Game ID is null");
            throw new InvalidEntityException("ID de l'utilisateur ou du jeu invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        WinStreak streak = winStreakRepository.findByUserIdAndGameId(userId, gameId).orElseGet(() -> {
            WinStreak newStreak = new WinStreak();
            newStreak.setUser(userRepository.findById(userId).orElseThrow(() ->
                    new EntityNotFoundException(
                            "Aucun utilisateur avec l'ID = " + userId + " n'a été trouvé dans la BDD",
                            ErrorCodes.USER_NOT_FOUND)
            ));
            newStreak.setGame(gameRepository.findById(gameId).orElseThrow(() ->
                    new EntityNotFoundException(
                            "Aucun jeu avec l'ID = " + gameId + " n'a été trouvé dans la BDD",
                            ErrorCodes.GAME_NOT_FOUND)
            ));
            newStreak.setCurrentStreak(0);
            newStreak.setBestStreak(0);
            newStreak.setLastWin(null);
            return newStreak;
        });

        streak.setCurrentStreak(streak.getCurrentStreak() + 1);
        streak.setLastWin(Instant.now());

        if(streak.getCurrentStreak() > streak.getBestStreak()) {
            streak.setBestStreak(streak.getCurrentStreak());
        }
        log.info("WinStreak pour l'utilisateur avec l'ID = {} et le jeu avec l'ID = {} a été incrémenté pour être {}", userId, gameId, streak.getCurrentStreak());

        return WinStreakDto.fromEntity(winStreakRepository.save(streak));
    }

    @Override
    public WinStreakDto resetCurrentStreak(Integer userId, Integer gameId) {
        if(userId == null || gameId == null) {
            log.error("User ID or Game ID is null");
            throw new InvalidEntityException("ID de l'utilisateur ou du jeu invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        WinStreak streak = winStreakRepository.findByUserIdAndGameId(userId, gameId).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune WinStreak pour l'utilisateur avec l'ID = " + userId + " et le jeu avec l'ID = " + gameId + " n'a été trouvé dans la BDD",
                        ErrorCodes.WIN_STREAK_NOT_FOUND)
        );

        streak.setCurrentStreak(0);

        log.info("La streak actuelle pour l'utilisateur avec l'ID = {} et le jeu avec l'ID = {} a été réinitialisé", userId, gameId);

        return WinStreakDto.fromEntity(winStreakRepository.save(streak));
    }

    @Override
    public WinStreakDto updateBestStreak(Integer userId, Integer gameId) {
        if(userId == null || gameId == null) {
            log.error("User ID or Game ID is null");
            throw new InvalidEntityException("ID de l'utilisateur ou du jeu invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        WinStreak streak = winStreakRepository.findByUserIdAndGameId(userId, gameId).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune WinStreak pour l'utilisateur avec l'ID = " + userId + " et le jeu avec l'ID = " + gameId + " n'a été trouvé dans la BDD",
                        ErrorCodes.WIN_STREAK_NOT_FOUND)
        );

        if(streak.getCurrentStreak() > streak.getBestStreak()) {
            streak.setBestStreak(streak.getCurrentStreak());

            log.info("La meilleure streak pour l'utilisateur avec l'ID = {} et le jeu avec l'ID = {} a été mise à jour à {}", userId, gameId, streak.getBestStreak());
        }
        return WinStreakDto.fromEntity(winStreakRepository.save(streak));
    }

    @Override
    public WinStreakDto getCurrentStreak(Integer userId, Integer gameId) {
        if(userId == null || gameId == null) {
            log.error("User ID or Game ID is null");
            throw new InvalidEntityException("ID de l'utilisateur ou du jeu invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        return winStreakRepository.findByUserIdAndGameId(userId, gameId).map(WinStreakDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune WinStreak pour l'utilisateur avec l'ID = " + userId + " et le jeu avec l'ID = " + gameId + " n'a été trouvé dans la BDD",
                        ErrorCodes.WIN_STREAK_NOT_FOUND)
        );
    }

    @Override
    public Page<WinStreakDto> getStreaksByUser(Integer userId, Pageable pageable) {
        if(userId == null) {
            log.error("User ID is null");
            throw new InvalidEntityException("ID de l'utilisateur invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        log.info("Récupération des streaks pour l'utilisateur avec l'ID = {}", userId);

        return winStreakRepository.findByUserId(userId, pageable).map(WinStreakDto::fromEntity);
    }

    @Override
    public Page<WinStreakDto> getStreaksByGame(Integer gameId, Pageable pageable) {
        if(gameId == null) {
            log.error("Game ID is null");
            throw new InvalidEntityException("ID du jeu invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        log.info("Récupération des streaks pour le jeu avec l'ID = {}", gameId);

        return winStreakRepository.findByGameId(gameId, pageable).map(WinStreakDto::fromEntity);
    }

    @Override
    public Page<WinStreakDto> getActiveStreaks(Pageable pageable) {
        log.info("Récupération des streaks actifs");

        return winStreakRepository.findByCurrentStreakGreaterThan(0,pageable).map(WinStreakDto::fromEntity);
    }

    @Override
    public Map<Integer, Integer> getGlobalBestStreaks(int limit) {
        if(limit <= 0) {
            log.error("Limit is less than or equal to 0");
            throw new InvalidEntityException("Limite est inférieure ou égale à 0", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        log.info("Récupération des meilleures streaks globales");

        List<WinStreak> bestStreaks = winStreakRepository.findTopBestStreaks(limit);

        Map<Integer, Integer> bestStreaksMap = new LinkedHashMap<>();

        for(WinStreak streak : bestStreaks) {
            bestStreaksMap.put(streak.getUser().getId(), streak.getBestStreak());
        }
        return bestStreaksMap;
    }

    @Override
    public Map<Integer, Integer> getGameBestStreaks(Integer gameId, int limit) {
        if(gameId == null || limit <= 0) {
            log.error("Game ID is null or limit is less than or equal to 0");
            throw new InvalidEntityException("ID du jeu invalide ou limite est inférieure ou égale à 0", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        if(gameRepository.findById(gameId).isEmpty()) {
            log.error("Game with ID = {} not found", gameId);
            throw new EntityNotFoundException("Jeu avec l'ID = " + gameId + " non trouvé", ErrorCodes.GAME_NOT_FOUND);
        }

        log.info("Récupération des meilleures streaks pour le jeu avec l'ID = {}", gameId);

        List<WinStreak> bestStreaks = winStreakRepository.findTopBestStreaksByGame(gameId, limit);

        Map<Integer, Integer> bestStreaksMap = new LinkedHashMap<>();
        for(WinStreak streak : bestStreaks) {
            bestStreaksMap.put(streak.getUser().getId(), streak.getBestStreak());
        }

        return bestStreaksMap;
    }

    @Override
    public boolean isActiveStreak(Integer userId, Integer gameId) {
        if(userId == null || gameId == null) {
            log.error("User ID or Game ID is null");
            throw new InvalidEntityException("ID de l'utilisateur ou du jeu invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        log.info("Vérification de la streak active pour l'utilisateur avec l'ID = {} et le jeu avec l'ID = {}", userId, gameId);

        return winStreakRepository.findByUserIdAndGameId(userId, gameId).map(WinStreak::getCurrentStreak).orElse(0) > 0;
    }

    @Override
    public void resetAllStreaksForGame(Integer gameId) {
        if(gameId == null) {
            log.error("Game ID is null");
            throw new InvalidEntityException("ID du jeu invalide", ErrorCodes.WIN_STREAK_NOT_VALID);
        }

        log.info("Réinitialisation de toutes les streaks pour le jeu avec l'ID = {}", gameId);

        winStreakRepository.findByGameId(gameId).forEach(streak -> {
            streak.setCurrentStreak(0);
            streak.setBestStreak(0);
            streak.setLastWin(null);
            winStreakRepository.save(streak);
        });

        log.info("Toutes les streaks pour le jeu avec l'ID = {} ont été réinitialisées", gameId);
    }
}
