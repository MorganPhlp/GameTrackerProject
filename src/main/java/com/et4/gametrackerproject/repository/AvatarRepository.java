package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Integer> {
    // Recherche par URL/chemin de la photo
    Optional<Avatar> findByPhoto(String photo);

    // Recherche des avatars par correspondance partielle de l'URL/chemin de la photo
    List<Avatar> findByPhotoContaining(String photoPattern);

    // Recherche des avatars qui sont utilisés par des utilisateurs
    @Query("SELECT DISTINCT a FROM Avatar a WHERE SIZE(a.users) > 0")
    List<Avatar> findUsedAvatars();

    // Recherche des avatars qui ne sont pas utilisés par des utilisateurs
    @Query("SELECT a FROM Avatar a WHERE SIZE(a.users) = 0")
    List<Avatar> findUnusedAvatars();

    // Compter combien d'utilisateurs utilisent un certain avatar
    @Query("SELECT COUNT(u) FROM User u WHERE u.avatar.id = :avatarId")
    Long countUsersByAvatarId(@Param("avatarId") Integer avatarId);

    // Trouver les avatars les plus populaires (triés par nombre d'utilisateurs décroissant)
    @Query("SELECT a, COUNT(u) as userCount FROM Avatar a LEFT JOIN a.users u GROUP BY a ORDER BY userCount DESC")
    List<Object[]> findAvatarsByPopularity();

    // Rechercher des avatars par ID d'utilisateur
    @Query("SELECT a FROM Avatar a JOIN a.users u WHERE u.id = :userId")
    Optional<Avatar> findByUserId(@Param("userId") Integer userId);
}
