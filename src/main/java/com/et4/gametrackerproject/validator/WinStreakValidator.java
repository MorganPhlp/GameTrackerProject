package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.WinStreakDto;

import java.util.ArrayList;
import java.util.List;

public class WinStreakValidator {

    public static List<String> validate(WinStreakDto winStreak) {
        List<String> errors = new ArrayList<>();

        if (winStreak == null) {
            errors.add("User is required");
            errors.add("Game is required");
            return errors;
        }

        if (winStreak.getGame() == null) {
            errors.add("Game is required");
        } else {
            List<String> gameErrors = GameValidator.validate(winStreak.getGame());
            if (!gameErrors.isEmpty()) {
                errors.add("Game validation errors: " + String.join(", ", gameErrors));
            }
        }

        if (winStreak.getUser() == null) {
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(winStreak.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }

        if (winStreak.getCurrentStreak() == null) {
            winStreak.setCurrentStreak(0);
        } else if (winStreak.getCurrentStreak() < 0) {
            errors.add("Current streak must be at least 0");
        }

        if (winStreak.getBestStreak() == null) {
            winStreak.setBestStreak(0);
        } else if (winStreak.getBestStreak() < 0) {
            errors.add("Best streak must be at least 0");
        }

        if (winStreak.getLastWin() == null && (winStreak.getBestStreak() != 0 || winStreak.getCurrentStreak() != 0)) {
            errors.add("Last win is required if best streak or current streak is not 0");
        }

        return errors;
    }
}
