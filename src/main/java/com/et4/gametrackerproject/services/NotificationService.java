package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.NotificationDto;
import com.et4.gametrackerproject.enums.NotifType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public interface NotificationService {

    //Opérations de base
    NotificationDto createNotification(NotificationDto notificationDto);
    NotificationDto updateNotificationContent(Integer notificationId, String newContent);
    void deleteNotification(Integer notificationId);

    //Récupération
    NotificationDto getNotificationById(Integer notificationId);
    Page<NotificationDto> getUserNotifications(Integer userId, Pageable pageable);
    Page<NotificationDto> getUnreadNotifications(Integer userId, Pageable pageable);
    Page<NotificationDto> getNotificationsByType(Integer userId, NotifType type, Pageable pageable);

    // Gestion des états
    NotificationDto markAsRead(Integer notificationId);
    void markAllAsRead(Integer userId);
    NotificationDto toggleNotificationPin(Integer notificationId);

    // Historique
    Page<NotificationDto> getNotificationHistory(Integer userId, Instant startDate, Instant endDate, Pageable pageable);
    Page<NotificationDto> searchNotifications(Integer userId, String searchTerm, Pageable pageable);

    //Statistiques
    Integer getUnreadCount(Integer userId);

    //Expiration
    void cleanExpiredNotifications(int daysThreshold);

    //Batch operations
    void batchDeleteNotifications(List<Integer> notificationIds);
    void batchMarkAsRead(List<Integer> notificationIds);

    //Intégration
    void syncNotificationsAcrossDevices(Integer userId);
}