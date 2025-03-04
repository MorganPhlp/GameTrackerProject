package com.et4.testpgt.model;

import com.et4.testpgt.enums.AchievementRarity;
import com.et4.testpgt.enums.AchievementType;
import com.et4.testpgt.util.JsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "achievement")
public class Achievement extends AbstractEntity{

    @Column(name = "name", nullable = false)
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
    @Column(name = "type")
    private AchievementType type;

    @Column(name = "points_reward")
    private Integer pointsReward = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "rarity")
    private AchievementRarity rarity;

    @Column(name = "requires_achievement_id")
    private Integer requiresAchievementId;

    @Column(name = "is_secret")
    private Boolean isSecret = false;

}
