package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.enums.NotifType;
import com.et4.gametrackerproject.model.Notification;
import com.et4.gametrackerproject.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

    // Recherches de base
    List<Notification> findByUser(User user);

    Page<Notification> findByUser(User user, Pageable pageable);

    List<Notification> findByUserOrderByCreationDateDesc(User user);

    Page<Notification> findByUserOrderByCreationDateDesc(User user, Pageable pageable);

    List<Notification> findByUserAndType(User user, NotifType type);

    Page<Notification> findByUserAndType(User user, NotifType type, Pageable pageable);

    // Trouver les notifications non lues
    List<Notification> findByUserAndIsReadFalse(User user);

    Page<Notification> findByUserAndIsReadFalse(User user, Pageable pageable);

    List<Notification> findByUserAndIsReadFalseOrderByCreationDateDesc(User user);

    Page<Notification> findByUserAndIsReadFalseOrderByCreationDateDesc(User user, Pageable pageable);

    // Trouver les notifications par type et statut de lecture
    List<Notification> findByUserAndTypeAndIsRead(User user, NotifType type, Boolean isRead);

    Page<Notification> findByUserAndTypeAndIsRead(User user, NotifType type, Boolean isRead, Pageable pageable);

    // Compter les notifications non lues
    Long countByUserAndIsReadFalse(User user);

    // Marquer toutes les notifications d'un utilisateur comme lues
    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.user = :user AND n.isRead = false")
    int markAllNotificationsAsRead(@Param("user") User user);

    // Marquer toutes les notifications d'un certain type comme lues
    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.user = :user AND n.type = :type AND n.isRead = false")
    int markNotificationsAsReadByType(@Param("user") User user, @Param("type") NotifType type);

    // Marquer une notification spécifique comme lue
    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.id = :notificationId")
    int markNotificationAsRead(@Param("notificationId") Integer notificationId);

    // Trouver les notifications dans une période de temps
    List<Notification> findByUserAndCreationDateBetween(
            User user,
            Instant startDate,
            Instant endDate);

    Page<Notification> findByUserAndCreationDateBetween(
            User user,
            Instant startDate,
            Instant endDate,
            Pageable pageable);

    // Supprimer les notifications anciennes
    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.user = :user AND n.isRead = true AND n.creationDate < :date")
    int deleteOldReadNotifications(@Param("user") User user, @Param("date") Instant date);

    // Obtenir les 5 dernières notifications non lues d'un utilisateur
    @Query("SELECT n FROM Notification n " +
            "WHERE n.user = :user AND n.isRead = false " +
            "ORDER BY n.creationDate DESC")
    List<Notification> findTop5RecentUnreadNotifications(
            @Param("user") User user,
            Pageable pageable);

    // Supprimer toutes les notifications d'un utilisateur
    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.user = :user")
    int deleteAllUserNotifications(@Param("user") User user);
}
