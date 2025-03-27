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

    @PutMapping(value = APP_ROOT + "/sanctions/modify/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserSanctionDto modifySanctionDuration(@PathVariable Integer sanctionId,@RequestBody Instant newEndDate);

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

    @GetMapping(value = APP_ROOT + "/sanctions/count/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer countActiveSanctions(@PathVariable Integer userId);
}
