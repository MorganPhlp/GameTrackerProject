package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.NotificationApi;
import com.et4.gametrackerproject.dto.NotificationDto;
import com.et4.gametrackerproject.enums.NotifType;
import com.et4.gametrackerproject.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class NotificationController implements NotificationApi {

    @Autowired
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        return notificationService.createNotification(notificationDto);
    }

    @Override
    public NotificationDto updateNotificationContent(Integer notificationId, String newContent) {
        return notificationService.updateNotificationContent(notificationId, newContent);
    }

    @Override
    public void deleteNotification(Integer notificationId) {
        notificationService.deleteNotification(notificationId);
    }

    @Override
    public NotificationDto getNotificationById(Integer notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @Override
    public Page<NotificationDto> getUserNotifications(Integer userId, Pageable pageable) {
        return notificationService.getUserNotifications(userId, pageable);
    }

    @Override
    public Page<NotificationDto> getUnreadNotifications(Integer userId, Pageable pageable) {
        return notificationService.getUnreadNotifications(userId, pageable);
    }

    @Override
    public Page<NotificationDto> getNotificationsByType(Integer userId, NotifType type, Pageable pageable) {
        return notificationService.getNotificationsByType(userId, type, pageable);
    }

    @Override
    public NotificationDto markAsRead(Integer notificationId) {
        return notificationService.markAsRead(notificationId);
    }

    @Override
    public void markAllAsRead(Integer userId) {
        notificationService.markAllAsRead(userId);
    }

    @Override
    public Page<NotificationDto> getNotificationHistory(Integer userId, Instant startDate, Instant endDate, Pageable pageable) {
        return notificationService.getNotificationHistory(userId, startDate, endDate, pageable);
    }

    @Override
    public Integer getUnreadCount(Integer userId) {
        return notificationService.getUnreadCount(userId);
    }
}
