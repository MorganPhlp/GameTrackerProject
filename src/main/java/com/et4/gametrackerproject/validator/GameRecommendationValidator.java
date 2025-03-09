package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.dto.GameRecommendationDto;
import com.et4.gametrackerproject.dto.UserDto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GameRecommendationValidator {
    public static List<String> validate(GameRecommendationDto recommendation) {
        List<String> errors = new ArrayList<>();

        if (recommendation == null) {
            errors.add("Sender is required");
            errors.add("Receiver is required");
            errors.add("Game is required");
            return errors;
        }

        if (recommendation.getSender() == null) {
            errors.add("Sender is required");
        } else {
            List<String> senderErrors = UserValidator.validate(recommendation.getSender());
            if (!senderErrors.isEmpty()) {
                errors.add("Sender validation errors: " + String.join(", ", senderErrors));
            }
        }

        if (recommendation.getReceiver() == null) {
            errors.add("Receiver is required");
        } else {
            List<String> receiverErrors = UserValidator.validate(recommendation.getReceiver());
            if (!receiverErrors.isEmpty()) {
                errors.add("Receiver validation errors: " + String.join(", ", receiverErrors));
            }
        }

        if (recommendation.getGame() == null) {
            errors.add("Game is required");
        } else {
            List<String> gameErrors = GameValidator.validate(recommendation.getGame());
            if (!gameErrors.isEmpty()) {
                errors.add("Game validation errors: " + String.join(", ", gameErrors));
            }
        }

        if (recommendation.getMessage() != null && recommendation.getMessage().length() > 500) {
            errors.add("Recommendation message cannot exceed 500 characters");
        }

        if (recommendation.getSender() != null &&
                recommendation.getReceiver() != null &&
                recommendation.getSender().getId() != null &&
                recommendation.getSender().getId().equals(recommendation.getReceiver().getId())) {
            errors.add("Sender and receiver cannot be the same user");
        }

        return errors;
    }
}
