package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.AchievementDto;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AchievementValidator {
    public static List<String> validate(AchievementDto achievementDto) {
        List<String> errors = new ArrayList<>();

        if (achievementDto == null) {
            errors.add("Name is required");
            errors.add("Description is required");
            errors.add("Icon is required");
            errors.add("Conditions are required");
            errors.add("Type is required");
            errors.add("Rarity is required");
            errors.add("Points reward is required and must be greater than or equal to 0");
            return errors;
        }

        if (!StringUtils.hasLength(achievementDto.getName())) {
            errors.add("Name is required");
        }
        if (!StringUtils.hasLength(achievementDto.getDescription())) {
            errors.add("Description is required");
        }
        if(!StringUtils.hasLength(achievementDto.getIcon())) {
            errors.add("Icon is required");
        }
        if(CollectionUtils.isEmpty(achievementDto.getConditions())) {
            errors.add("Conditions are required");
        }
        if(achievementDto.getIsActive() == null) {
            achievementDto.setIsActive(true);
        }
        if (achievementDto.getType() == null) {
            errors.add("Type is required");
        }
        if (achievementDto.getRarity() == null) {
            errors.add("Rarity is required");
        }
        if (achievementDto.getPointsReward() == null) {
            achievementDto.setPointsReward(0);
        } else if (achievementDto.getPointsReward() < 0){
            errors.add("Points reward must be greater than or equal to 0");
        }
        if (achievementDto.getIsSecret() == null) {
            achievementDto.setIsSecret(false);
        }

        return errors;
    }
}
