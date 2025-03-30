package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.ReportDto;
import com.et4.gametrackerproject.enums.ReportStatus;
import com.et4.gametrackerproject.enums.ReportType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface ReportApi {

    @PostMapping(value = APP_ROOT + "/report/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer un rapport", description = "Créer un rapport")
    @ApiResponse(responseCode = "200", description = "Rapport créé")
    ReportDto createReport(@RequestBody ReportDto reportDto);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour un rapport", description = "Mettre à jour un rapport")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapport mis à jour"),
            @ApiResponse(responseCode = "404", description = "Rapport non trouvé")
    })
    ReportDto updateReportDetails(@PathVariable Integer reportId,@RequestBody ReportDto reportDto);

    @DeleteMapping(value = APP_ROOT + "/report/{reportId}")
    @Operation(summary = "Supprimer un rapport", description = "Supprimer un rapport")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapport supprimé"),
            @ApiResponse(responseCode = "404", description = "Rapport non trouvé")
    })
    void deleteReport(@PathVariable Integer reportId);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/resolve", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Résoudre un rapport", description = "Résoudre un rapport")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapport résolu"),
            @ApiResponse(responseCode = "404", description = "Rapport non trouvé")
    })
    ReportDto resolveReport(@PathVariable Integer reportId,@RequestBody Integer adminId,@RequestBody String resolutionNotes);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/changeStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Changer le statut d'un rapport", description = "Changer le statut d'un rapport")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Statut du rapport changé"),
            @ApiResponse(responseCode = "404", description = "Rapport non trouvé")
    })
    ReportDto changeReportStatus(@PathVariable Integer reportId,@RequestBody ReportStatus newStatus);

    @GetMapping(value = APP_ROOT + "/report/{reportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un rapport par son ID", description = "Récupérer un rapport par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapport trouvé"),
            @ApiResponse(responseCode = "404", description = "Rapport non trouvé")
    })
    ReportDto getReportById(@PathVariable Integer reportId);

    @GetMapping(value = APP_ROOT + "/report/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les rapports par statut", description = "Récupérer les rapports par statut")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapports trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun rapport trouvé avec ce statut")
    })
    Page<ReportDto> getReportsByStatus(@PathVariable ReportStatus status, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/report/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les rapports par type", description = "Récupérer les rapports par type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapports trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun rapport trouvé avec ce type")
    })
    Page<ReportDto> getReportsByType(@PathVariable ReportType type, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/report/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les rapports d'un utilisateur", description = "Récupérer les rapports d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapports trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun rapport trouvé pour cet utilisateur")
    })
    Page<ReportDto> getUserReports(@PathVariable Integer userId,@RequestBody boolean isReporter, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/report/unresolved", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les rapports non résolus", description = "Récupérer les rapports non résolus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapports non résolus trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun rapport non résolu trouvé")
    })
    Page<ReportDto> getUnresolvedReports(Pageable pageable);

    @PutMapping(value = APP_ROOT + "/report/{reportId}/assign", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Assigner un rapport à un administrateur", description = "Assigner un rapport à un administrateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rapport assigné à l'administrateur"),
            @ApiResponse(responseCode = "404", description = "Rapport ou administrateur non trouvé")
    })
    ReportDto assignReportToAdmin(@PathVariable Integer reportId,@RequestBody Integer adminId);

    // Vérifications

    @GetMapping(value = APP_ROOT + "/report/previous", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vérifier si un utilisateur a déjà signalé un autre utilisateur", description = "Vérifier si un utilisateur a déjà signalé un autre utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vérification réussie"),
            @ApiResponse(responseCode = "404", description = "Aucun rapport trouvé")
    })
    boolean hasPreviousReportsAgainstUser(@RequestBody Integer reporterId,@RequestBody Integer reportedUserId);

    @GetMapping(value = APP_ROOT + "/report/content", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vérifier si un contenu a déjà été signalé", description = "Vérifier si un contenu a déjà été signalé")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vérification réussie"),
            @ApiResponse(responseCode = "404", description = "Aucun rapport trouvé")
    })
    boolean isContentAlreadyReported(@RequestBody Integer contentId,@RequestBody ReportType type);

    @GetMapping(value = APP_ROOT + "/report/{contentId}/{type}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer l'historique des rapports pour un contenu", description = "Récupérer l'historique des rapports pour un contenu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historique des rapports trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun rapport trouvé pour ce contenu")
    })
    Page<ReportDto> getReportHistory(@PathVariable Integer contentId,@PathVariable ReportType type, Pageable pageable);
}
