package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;
import com.et4.gametrackerproject.exception.EntityNotFoundException;
import com.et4.gametrackerproject.exception.ErrorCodes;
import com.et4.gametrackerproject.model.Achievement;
import com.et4.gametrackerproject.repository.AchievementRepository;
import com.et4.gametrackerproject.services.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    public AchievementServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public List<AchievementDto> getAllAchievements() {
        List<Achievement> achievements = achievementRepository.findAll();

        if (achievements.isEmpty()) {
            log.error("Aucun achievement trouvé");
            throw new EntityNotFoundException("Aucun achievement trouvé",
                    ErrorCodes.ACHIEVEMENT_NOT_FOUND);
        }

        // Convertir chaque achievement en DTO et les retourner sous forme de liste
        return achievements.stream()
                .map(AchievementDto::fromEntity)
                .collect(Collectors.toList());
    }


    private static final Logger log = LoggerFactory.getLogger(AchievementServiceImpl.class);

    @Override
    public AchievementDto getAchievementById(Integer id) {
        if (id == null){
            log.error("Article ID is null");
            return null;
        }

        Optional<Achievement> achievement = achievementRepository.findById(id);

        AchievementDto dto = AchievementDto.fromEntity(achievement.get());

        return Optional.of(dto).orElseThrow(() ->
        new EntityNotFoundException("Aucun artcle avec l'ID "+ id + "Trouvée",
                ErrorCodes.ACHIEVEMENT_NOT_FOUND)
                );
    }

    @Override
    public List<AchievementDto> getAchievementsByType(AchievementType type) {
        if (type == null) {
            log.error("Le type d'achievement est null");
            throw new IllegalArgumentException("Le type d'achievement ne peut pas être null");
        }

        List<Achievement> achievements = achievementRepository.findByType(type);

        if (achievements.isEmpty()) {
            log.warn("Aucun achievement trouvé pour le type : " + type);
            throw new EntityNotFoundException("Aucun achievement trouvé pour le type : " + type,
                    ErrorCodes.ACHIEVEMENT_NOT_FOUND);
        }

        return achievements.stream()
                .map(AchievementDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AchievementDto> getAchievementsByRarity(AchievementRarity rarity) {
        if (rarity == null) {
            log.error("La rareté de l'achievement est null");
            throw new IllegalArgumentException("La rareté de l'achievement ne peut pas être null");
        }

        List<Achievement> achievements = achievementRepository.findByRarity(rarity);

        if (achievements.isEmpty()) {
            log.warn("Aucun achievement trouvé pour la rareté : " + rarity);
            throw new EntityNotFoundException("Aucun achievement trouvé pour la rareté : " + rarity,
                    ErrorCodes.ACHIEVEMENT_NOT_FOUND);
        }

        return achievements.stream()
                .map(AchievementDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AchievementDto> getActiveAchievements() {
        List<Achievement> achievements = achievementRepository.findByIsActiveTrue();

        if (achievements.isEmpty()) {
            log.warn("Aucun achievement actif trouvé");
            throw new EntityNotFoundException("Aucun achievement actif trouvé",
                    ErrorCodes.ACHIEVEMENT_NOT_FOUND);
        }

        return achievements.stream()
                .map(AchievementDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AchievementDto> getVisibleAchievementsForUser(Integer userId) {
        if (userId == null) {
            log.error("L'ID utilisateur est null");
            throw new IllegalArgumentException("L'ID utilisateur ne peut pas être null");
        }

        Optional<Achievement> achievements = achievementRepository.findById(userId);

        if (achievements.isEmpty()) {
            log.warn("Aucun achievement visible trouvé pour l'utilisateur ID : " + userId);
            throw new EntityNotFoundException("Aucun achievement visible trouvé pour l'utilisateur ID : " + userId,
                    ErrorCodes.ACHIEVEMENT_NOT_FOUND);
        }

        return achievements.stream()
                .map(AchievementDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public AchievementDto createAchievement(AchievementDto achievementDto) {
        if (achievementDto == null) {
            log.error("L'achievement à créer est null");
            throw new IllegalArgumentException("L'achievement ne peut pas être null");
        }

        Achievement achievement = AchievementDto.toEntity(achievementDto);
        achievement = achievementRepository.save(achievement);

        return AchievementDto.fromEntity(achievement);
    }

    @Override
    public AchievementDto updateAchievement(Integer id, AchievementDto achievementDto) {
        if (id == null) {
            log.error("L'ID de l'achievement à mettre à jour est null");
            throw new IllegalArgumentException("L'ID de l'achievement ne peut pas être null");
        }

        if (achievementDto == null) {
            log.error("Les nouvelles données de l'achievement sont null");
            throw new IllegalArgumentException("Les données de mise à jour ne peuvent pas être null");
        }

        Optional<Achievement> existingAchievement = achievementRepository.findById(id);
        if (existingAchievement.isEmpty()) {
            log.error("Aucun achievement trouvé avec l'ID : " + id);
            throw new EntityNotFoundException("Aucun achievement trouvé avec l'ID " + id,
                    ErrorCodes.ACHIEVEMENT_NOT_FOUND);
        }

        Achievement updatedAchievement = AchievementDto.toEntity(achievementDto);
        updatedAchievement.setId(id); // S'assurer que l'ID reste le même
        updatedAchievement = achievementRepository.save(updatedAchievement);

        return AchievementDto.fromEntity(updatedAchievement);
    }

    @Override
    public Set<AchievementDto> getUnlockedAchievementsForUser(Integer userId) {
        if (userId == null) {
            log.error("L'ID utilisateur est null");
            throw new IllegalArgumentException("L'ID utilisateur ne peut pas être null");
        }

        List<Achievement> unlockedAchievements = achievementRepository.findByUsers_Id(userId);

        if (unlockedAchievements.isEmpty()) {
            log.warn("Aucun achievement débloqué trouvé pour l'utilisateur ID : " + userId);
            return Set.of();
        }

        return unlockedAchievements.stream()
                .map(AchievementDto::fromEntity)
                .collect(Collectors.toSet());
    }


    @Override
    public Map<AchievementDto, Boolean> getUserAchievementProgress(Integer userId) {
        if (userId == null) {
            log.error("L'ID utilisateur est null");
            throw new IllegalArgumentException("L'ID utilisateur ne peut pas être null");
        }

        Map<Achievement, Boolean> achievementProgress = achievementRepository.findUserAchievementProgress(userId);

        if (achievementProgress.isEmpty()) {
            log.warn("Aucune progression d'achievement trouvée pour l'utilisateur ID : " + userId);
            return Map.of();
        }

        return achievementProgress.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> AchievementDto.fromEntity(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    @Override
    public boolean hasUserUnlockedAchievement(Integer userId, Integer achievementId) {
        if (userId == null || achievementId == null) {
            log.error("L'ID utilisateur ou l'ID de l'achievement est null");
            throw new IllegalArgumentException("L'ID utilisateur et l'ID de l'achievement ne peuvent pas être null");
        }

        return achievementRepository.existsUserAchievement(userId, achievementId);
    }


    @Override
    public AchievementDto unlockAchievementForUser(Integer userId, Integer achievementId) {
        return null;
    }

    @Override
    public int calculateUserAchievementPoints(Integer userId) {
        return 0;
    }

    @Override
    public void checkUserProgress(Integer userId, String contextType, Map<String, Object> contextData) {

    }

    @Override
    public void checkAchievements() {

    }

    @Override
    public void checkGameCompletionAchievements(Integer userId, Integer gameId, Map<String, Object> gameStats) {

    }

    @Override
    public Map<AchievementRarity, Long> getGlobalAchievementStats() {
        return Map.of();
    }

    @Override
    public List<AchievementDto> getSecretAchievements() {
        return List.of();
    }
}
