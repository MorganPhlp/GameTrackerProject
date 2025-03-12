package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.NotificationDto;
import com.et4.gametrackerproject.enums.NotifType;
import com.et4.gametrackerproject.services.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        return null;
    }

    @Override
    public NotificationDto updateNotificationContent(Integer notificationId, String newContent) {
        return null;
    }

    @Override
    public void deleteNotification(Integer notificationId) {

    }

    @Override
    public NotificationDto getNotificationById(Integer notificationId) {
        return null;
    }

    @Override
    public Page<NotificationDto> getUserNotifications(Integer userId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<NotificationDto> getUnreadNotifications(Integer userId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<NotificationDto> getNotificationsByType(Integer userId, NotifType type, Pageable pageable) {
        return null;
    }

    @Override
    public NotificationDto markAsRead(Integer notificationId) {
        return null;
    }

    @Override
    public void markAllAsRead(Integer userId) {

    }

    @Override
    public NotificationDto toggleNotificationPin(Integer notificationId) {
        return null;
    }

    @Override
    public Page<NotificationDto> getNotificationHistory(Integer userId, Instant startDate, Instant endDate, Pageable pageable) {
        return null;
    }

    @Override
    public Page<NotificationDto> searchNotifications(Integer userId, String searchTerm, Pageable pageable) {
        return null;
    }

    @Override
    public Integer getUnreadCount(Integer userId) {
        return 0;
    }

    @Override
    public void cleanExpiredNotifications(int daysThreshold) {

    }

    @Override
    public void batchDeleteNotifications(List<Integer> notificationIds) {

    }

    @Override
    public void batchMarkAsRead(List<Integer> notificationIds) {

    }

    @Override
    public void syncNotificationsAcrossDevices(Integer userId) {

    }
}
