package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.NotificationDto;
import com.et4.gametrackerproject.enums.NotifType;
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
    NotificationDto createNotification(@RequestBody NotificationDto notificationDto);

    @PutMapping(value = APP_ROOT + "/notification/{notificationId}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    NotificationDto updateNotificationContent(@PathVariable Integer notificationId,@RequestBody String newContent);

    @DeleteMapping(value = APP_ROOT + "/notification/{notificationId}")
    void deleteNotification(@PathVariable Integer notificationId);

    //Récupération

    @GetMapping(value = APP_ROOT + "/notification/{notificationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    NotificationDto getNotificationById(@PathVariable Integer notificationId);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<NotificationDto> getUserNotifications(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/unread", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<NotificationDto> getUnreadNotifications(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/type", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<NotificationDto> getNotificationsByType(@PathVariable Integer userId,@RequestBody NotifType type, Pageable pageable);

    // Gestion des états

    @PutMapping(value = APP_ROOT + "/notification/{notificationId}/markAsRead", produces = MediaType.APPLICATION_JSON_VALUE)
    NotificationDto markAsRead(@PathVariable Integer notificationId);

    @PutMapping(value = APP_ROOT + "/notification/user/{userId}/markAllAsRead")
    void markAllAsRead(@PathVariable Integer userId);

    // Historique

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<NotificationDto> getNotificationHistory(@PathVariable Integer userId,@RequestBody Instant startDate,@RequestBody Instant endDate, Pageable pageable);

    //Statistiques

    @GetMapping(value = APP_ROOT + "/notification/user/{userId}/unreadCount", produces = MediaType.APPLICATION_JSON_VALUE)
    Integer getUnreadCount(@PathVariable Integer userId);
}
