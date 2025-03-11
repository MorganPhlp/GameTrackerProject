package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;
import com.et4.gametrackerproject.util.JsonConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "achievement")
public class Achievement extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @Column(name = "conditions", columnDefinition = "json")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> conditions;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AchievementType type;

    @Column(name = "points_reward")
    private Integer pointsReward = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "rarity", nullable = false)
    private AchievementRarity rarity;

    @Column(name = "requires_achievement_id")
    private Integer requiresAchievementId;

    @Column(name = "is_secret")
    private Boolean isSecret = false;

    @OneToMany(mappedBy = "achievement", cascade = CascadeType.ALL)
    private Set<UserAchievement> achievementsEarned = new HashSet<>();
}
