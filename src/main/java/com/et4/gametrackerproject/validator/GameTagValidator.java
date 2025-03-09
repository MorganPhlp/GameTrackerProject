package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.GameTagDto;

import java.util.ArrayList;
import java.util.List;

public class GameTagValidator {
    public static List<String> validate(GameTagDto gameTagDto) {
        List<String> errors = new ArrayList<>();

        if (gameTagDto == null) {
            errors.add("Tag is required");
            errors.add("Game is required");
            return errors;
        }

        if (gameTagDto.getGame() == null) {
            errors.add("Game is required");
        } else {
            List<String> gameErrors = GameValidator.validate(gameTagDto.getGame());
            if (!gameErrors.isEmpty()) {
                errors.add("Game validation errors: " + String.join(", ", gameErrors));
            }
        }

        if (gameTagDto.getTag() == null) {
            errors.add("Tag is required");
        } else {
            List<String> tagErrors = TagValidator.validate(gameTagDto.getTag());
            if (!tagErrors.isEmpty()) {
                errors.add("Tag validation errors: " + String.join(", ", tagErrors));
            }
        }

        return errors;
    }
}
