package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.UserAchievementDto;

import java.time.Instant;
import java.util.*;

public class UserAchievementValidator {

    public static List<String> validate(UserAchievementDto userAchievement) {
        List<String> errors = new ArrayList<>();

        if (userAchievement == null) {
            errors.add("User is required");
            errors.add("Achievement is required");
            return errors;
        }

        if (userAchievement.getUser() == null) {
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(userAchievement.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }

        if (userAchievement.getAchievement() == null) {
            errors.add("Achievement is required");
        } else {
            List<String> achievementErrors = AchievementValidator.validate(userAchievement.getAchievement());
            if (!achievementErrors.isEmpty()) {
                errors.add("Achievement validation errors: " + String.join(", ", achievementErrors));
            }
        }

        if (userAchievement.getUnlockedAt() == null) {
            userAchievement.setUnlockedAt(Instant.now());
        } else if (userAchievement.getUnlockedAt().isAfter(Instant.now())) {
            errors.add("Unlock date cannot be in the future");
        } else if (userAchievement.getCreationDate() != null &&
                userAchievement.getUnlockedAt().isBefore(userAchievement.getCreationDate())) {
            errors.add("Unlock date cannot be before creation date");
        }

        return errors;
    }
}
