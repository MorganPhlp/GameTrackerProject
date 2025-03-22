package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.UserSanctionDto;
import com.et4.gametrackerproject.enums.SanctionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface UserSanctionApi {

    // Opérations de base

    @PostMapping(value = APP_ROOT + "/sanctions/apply", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto applySanction(@RequestBody UserSanctionDto sanctionDto);

    @PutMapping(value = APP_ROOT + "/sanctions/update/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto updateSanction(@PathVariable Integer sanctionId,@RequestBody UserSanctionDto sanctionDto);

    @DeleteMapping(value = APP_ROOT + "/sanctions/delete/{sanctionId}")
    void removeSanction(@PathVariable Integer sanctionId);

    // Gestion du cycle de vie

    @PutMapping(value = APP_ROOT + "/sanctions/lift/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto liftSanction(@PathVariable Integer sanctionId);

    @PutMapping(value = APP_ROOT + "/sanctions/modify/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto modifySanctionDuration(@PathVariable Integer sanctionId,@RequestBody Instant newEndDate);

    @PutMapping(value = APP_ROOT + "/sanctions/process")
    void processExpiredSanctions();

    // Récupération

    @GetMapping(value = APP_ROOT + "/sanctions/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto getSanctionById(@PathVariable Integer sanctionId);

    @GetMapping(value = APP_ROOT + "/sanctions/active/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserSanctionDto> getActiveSanctionsForUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/sanctions/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserSanctionDto> getSanctionsByType(@PathVariable SanctionType type, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/sanctions/history/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserSanctionDto> getSanctionsHistory(@PathVariable Integer userId, Pageable pageable);

    // Statistiques

    @GetMapping(value = APP_ROOT + "/sanctions/distribution", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<SanctionType, Long> getSanctionTypeDistribution();

    @GetMapping(value = APP_ROOT + "/sanctions/count/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer countActiveSanctions(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/sanctions/report", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> generateModerationReport(@RequestBody Instant startDate,@RequestBody Instant endDate);

    @GetMapping(value = APP_ROOT + "/sanctions/average/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    Double getAverageSanctionDuration(@PathVariable SanctionType type);

    // Automatisation

    @PutMapping(value = APP_ROOT + "/sanctions/check/{userId}")
    void checkAndApplyAutomaticSanctions(@PathVariable Integer userId);

    @PutMapping(value = APP_ROOT + "/sanctions/escalate/{sanctionId}")
    void escalateSanctionSeverity(@PathVariable Integer sanctionId);

    @PutMapping(value = APP_ROOT + "/sanctions/restrict/{userId}")
    void applyTemporaryRestrictions(@PathVariable Integer userId);

    // Batch operations

    @PutMapping(value = APP_ROOT + "/sanctions/bulk/apply", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void bulkApplySanctions(@RequestBody List<Integer> userIds,@RequestBody UserSanctionDto templateSanction);

    @PutMapping(value = APP_ROOT + "/sanctions/bulk/lift", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void bulkLiftSanctions(@RequestBody List<Integer> sanctionIds);

    @PutMapping(value = APP_ROOT + "/sanctions/convert/{daysThreshold}")
    void convertWarningsToBans(@PathVariable int daysThreshold);

    // Intégration

    @PutMapping(value = APP_ROOT + "/sanctions/notify/{sanctionId}")
    void notifyUserAboutSanction(@PathVariable Integer sanctionId);

    @PutMapping(value = APP_ROOT + "/sanctions/restrict/{userId}")
    void restrictUserFeatures(@PathVariable Integer userId);

    // Historique

    @GetMapping(value = APP_ROOT + "/sanctions/history/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<UserSanctionDto> getSanctionModificationHistory(@PathVariable Integer sanctionId, Pageable pageable);

    // Gestion des durées

    @PutMapping(value = APP_ROOT + "/sanctions/pause/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto pauseSanction(@PathVariable Integer sanctionId);

    @PutMapping(value = APP_ROOT + "/sanctions/extend/{sanctionId}/{days}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto extendSanction(@PathVariable Integer sanctionId,@PathVariable int days);

    // Relations

    @PutMapping(value = APP_ROOT + "/sanctions/template/{templateId}/{userId}")
    void applyTemplateToUser(@PathVariable Integer templateId,@PathVariable Integer userId);
}
