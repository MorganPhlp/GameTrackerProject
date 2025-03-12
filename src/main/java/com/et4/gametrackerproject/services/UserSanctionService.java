package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.UserSanctionDto;
import com.et4.gametrackerproject.enums.SanctionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface UserSanctionService {

    // Opérations de base
    UserSanctionDto applySanction(UserSanctionDto sanctionDto);
    UserSanctionDto updateSanction(Integer sanctionId, UserSanctionDto sanctionDto);
    void removeSanction(Integer sanctionId);

    // Gestion du cycle de vie
    UserSanctionDto liftSanction(Integer sanctionId);
    UserSanctionDto modifySanctionDuration(Integer sanctionId, Instant newEndDate);
    void processExpiredSanctions();

    // Récupération
    UserSanctionDto getSanctionById(Integer sanctionId);
    Page<UserSanctionDto> getActiveSanctionsForUser(Integer userId, Pageable pageable);
    Page<UserSanctionDto> getSanctionsByType(SanctionType type, Pageable pageable);
    Page<UserSanctionDto> getSanctionsHistory(Integer userId, Pageable pageable);

    // Statistiques
    Map<SanctionType, Long> getSanctionTypeDistribution();
    Integer countActiveSanctions(Integer userId);
    Map<String, Object> generateModerationReport(Instant startDate, Instant endDate);
    Double getAverageSanctionDuration(SanctionType type);

    // Automatisation
    void checkAndApplyAutomaticSanctions(Integer userId);
    void escalateSanctionSeverity(Integer sanctionId);
    void applyTemporaryRestrictions(Integer userId);

    // Batch operations
    void bulkApplySanctions(List<Integer> userIds, UserSanctionDto templateSanction);
    void bulkLiftSanctions(List<Integer> sanctionIds);
    void convertWarningsToBans(int daysThreshold);

    // Intégration
    void notifyUserAboutSanction(Integer sanctionId);
    void restrictUserFeatures(Integer userId);

    // Historique
    Page<UserSanctionDto> getSanctionModificationHistory(Integer sanctionId, Pageable pageable);

    // Gestion des durées
    UserSanctionDto pauseSanction(Integer sanctionId);
    UserSanctionDto extendSanction(Integer sanctionId, int days);

    // Relations
    void applyTemplateToUser(Integer templateId, Integer userId);
}