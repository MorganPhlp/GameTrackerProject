package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.GameRatingDto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GameRatingValidator {
    public static List<String> validate(GameRatingDto gameRatingDto) {
        List<String> errors = new ArrayList<>();

        if (gameRatingDto == null) {
            errors.add("User is required");
            errors.add("Rating value is required");
            errors.add("Game is required");
            return errors;
        }

        if (gameRatingDto.getUser() == null) {
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(gameRatingDto.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }

        if (gameRatingDto.getGame() == null) {
            errors.add("Game is required");
        } else {
            List<String> gameErrors = GameValidator.validate(gameRatingDto.getGame());
            if (!gameErrors.isEmpty()) {
                errors.add("Game validation errors: " + String.join(", ", gameErrors));
            }
        }

        if (gameRatingDto.getRating() == null) {
            errors.add("Rating value is required");
        } else if (gameRatingDto.getRating() < 1 || gameRatingDto.getRating() > 5) {
            errors.add("Rating must be between " + 1 + " and " + 5);
        }

        return errors;
    }
}
