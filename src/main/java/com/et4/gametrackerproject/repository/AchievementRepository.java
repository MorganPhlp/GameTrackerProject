package com.et4.gametrackerproject.repository;

import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;
import com.et4.gametrackerproject.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {

    List<Achievement> findByUsers_Id(Integer userId);


    Optional<Achievement> findByName(String name);

    List<Achievement> findByNameContainingIgnoreCase(String name);

    List<Achievement> findByType(AchievementType type);

    List<Achievement> findByRarity(AchievementRarity rarity);
    
    List<Achievement> findByIsActiveTrue();

    List<Achievement> findByIsSecretTrue();

    List<Achievement> findByRequiresAchievementId(Integer achievementId);

    List<Achievement> findByRequiresAchievementIdIsNull();

    List<Achievement> findByPointsRewardGreaterThanEqual(Integer points);

    List<Achievement> findByTypeAndRarity(AchievementType type, AchievementRarity rarity);

    List<Achievement> findByTypeAndIsActiveTrue(AchievementType type);

    @Query("SELECT a FROM Achievement a WHERE a.description LIKE %:keyword%")
    List<Achievement> findByDescriptionContaining(@Param("keyword") String keyword);

    @Query("SELECT a FROM Achievement a WHERE a.id NOT IN (SELECT ua.achievement.id FROM UserAchievement ua WHERE ua.user.id = :userId)")
    List<Achievement> findNotEarnedByUserId(@Param("userId") Integer userId);

    @Query("SELECT a.type, COUNT(a) FROM Achievement a GROUP BY a.type")
    List<Object[]> countByType();


    Map<Achievement, Boolean> findUserAchievementProgress(Integer userId);


    boolean existsUserAchievement(Integer userId, Integer achievementId);
}
