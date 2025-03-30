package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.UserSanctionDto;
import com.et4.gametrackerproject.enums.SanctionType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Appliquer une sanction", description = "Appliquer une sanction à un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sanction appliquée avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur lors de l'application de la sanction")
    })
    UserSanctionDto applySanction(@RequestBody UserSanctionDto sanctionDto);

    @PutMapping(value = APP_ROOT + "/sanctions/update/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour une sanction", description = "Mettre à jour une sanction existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sanction mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Sanction non trouvée")
    })
    UserSanctionDto updateSanction(@PathVariable Integer sanctionId,@RequestBody UserSanctionDto sanctionDto);

    @DeleteMapping(value = APP_ROOT + "/sanctions/delete/{sanctionId}")
    @Operation(summary = "Supprimer une sanction", description = "Supprimer une sanction existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sanction supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Sanction non trouvée")
    })
    void removeSanction(@PathVariable Integer sanctionId);

    // Gestion du cycle de vie

    @PutMapping(value = APP_ROOT + "/sanctions/modify/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifier une sanction", description = "Modifier une sanction existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sanction modifiée avec succès"),
            @ApiResponse(responseCode = "404", description = "Sanction non trouvée")
    })
    UserSanctionDto modifySanctionDuration(@PathVariable Integer sanctionId,@RequestBody Instant newEndDate);

    // Récupération

    @GetMapping(value = APP_ROOT + "/sanctions/{sanctionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer une sanction par son ID", description = "Récupérer une sanction par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sanction trouvée"),
            @ApiResponse(responseCode = "404", description = "Sanction non trouvée")
    })
    UserSanctionDto getSanctionById(@PathVariable Integer sanctionId);

    @GetMapping(value = APP_ROOT + "/sanctions/active/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les sanctions actives d'un utilisateur", description = "Récupérer les sanctions actives d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sanctions actives trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune sanction active trouvée pour cet utilisateur")
    })
    Page<UserSanctionDto> getActiveSanctionsForUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/sanctions/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les sanctions par type", description = "Récupérer les sanctions par type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sanctions trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune sanction trouvée pour ce type")
    })
    Page<UserSanctionDto> getSanctionsByType(@PathVariable SanctionType type, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/sanctions/history/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer l'historique des sanctions d'un utilisateur", description = "Récupérer l'historique des sanctions d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historique des sanctions trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucune sanction trouvée pour cet utilisateur")
    })
    Page<UserSanctionDto> getSanctionsHistory(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/sanctions/count/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Compter le nombre de sanctions actives d'un utilisateur", description = "Compter le nombre de sanctions actives d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nombre de sanctions compté"),
            @ApiResponse(responseCode = "404", description = "Aucune sanction trouvée pour cet utilisateur")
    })
    Integer countActiveSanctions(@PathVariable Integer userId);
}
