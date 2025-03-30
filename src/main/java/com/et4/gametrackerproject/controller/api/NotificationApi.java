package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.NotificationDto;
import com.et4.gametrackerproject.enums.NotifType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface NotificationApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/notification/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajouter une notification", description = "Ajouter une notification")
    @ApiResponse(responseCode = "200", description = "Notification ajoutée")
    NotificationDto createNotification(@RequestBody NotificationDto notificationDto);

    @PutMapping(value = APP_ROOT + "/notification/{notificationId}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour une notification", description = "Mettre à jour une notification")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification mise à jour"),
            @ApiResponse(responseCode = "404", description = "Notification non trouvée")
    })
    NotificationDto updateNotificationContent(@PathVariable Integer notificationId,@RequestBody String newContent);

    @DeleteMapping(value = APP_ROOT + "/notification/{notificationId}")
    @Operation(summary = "Supprimer une notification", description = "Supprimer une notification")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification supprimée"),
            @ApiResponse(responseCode = "404", description = "Notification non trouvée")
    })
    void deleteNotification(@PathVariable Integer notificationId);

    @GetMapping(value = APP_ROOT + "/notification/{notificationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer une notification par son ID", description = "Récupérer une notification par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification trouvée"),
            @ApiResponse(responseCode = "404", description = "Notification non trouvée")
    })
    NotificationDto getNotificationById(@PathVariable Integer notificationId);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer toutes les notifications d'un utilisateur", description = "Récupérer toutes les notifications d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifications trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune notification trouvée pour cet utilisateur")
    })
    Page<NotificationDto> getUserNotifications(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/unread", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer toutes les notifications non lues d'un utilisateur", description = "Récupérer toutes les notifications non lues d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifications non lues trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune notification non lue trouvée pour cet utilisateur")
    })
    Page<NotificationDto> getUnreadNotifications(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/type", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer toutes les notifications d'un utilisateur par type", description = "Récupérer toutes les notifications d'un utilisateur par type")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifications trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune notification trouvée pour cet utilisateur")
    })
    Page<NotificationDto> getNotificationsByType(@PathVariable Integer userId,@RequestBody NotifType type, Pageable pageable);

    @PutMapping(value = APP_ROOT + "/notification/{notificationId}/markAsRead", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Marquer une notification comme lue", description = "Marquer une notification comme lue")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notification marquée comme lue"),
            @ApiResponse(responseCode = "404", description = "Notification non trouvée")
    })
    NotificationDto markAsRead(@PathVariable Integer notificationId);

    @PutMapping(value = APP_ROOT + "/notification/user/{userId}/markAllAsRead")
    @Operation(summary = "Marquer toutes les notifications d'un utilisateur comme lues", description = "Marquer toutes les notifications d'un utilisateur comme lues")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Notifications marquées comme lues"),
            @ApiResponse(responseCode = "404", description = "Aucune notification trouvée pour cet utilisateur")
    })
    void markAllAsRead(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer l'historique des notifications d'un utilisateur", description = "Récupérer l'historique des notifications d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historique des notifications trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucune notification trouvée pour cet utilisateur")
    })
    Page<NotificationDto> getNotificationHistory(@PathVariable Integer userId,@RequestBody Instant startDate,@RequestBody Instant endDate, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/unreadCount", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer le nombre de notifications non lues d'un utilisateur", description = "Récupérer le nombre de notifications non lues d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nombre de notifications non lues trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucune notification trouvée pour cet utilisateur")
    })
    Integer getUnreadCount(@PathVariable Integer userId);
}
