package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
