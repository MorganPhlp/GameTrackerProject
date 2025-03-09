package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.GameProgressDto;
import com.et4.gametrackerproject.enums.GameStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GameProgressValidator {

    public static List<String> validate(GameProgressDto gameProgressDto) {
        List<String> errors = new ArrayList<>();

        if (gameProgressDto == null) {
            errors.add("User is required");
            errors.add("Game is required");
            return errors;
        }

        if(gameProgressDto.getUser() == null){
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(gameProgressDto.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }

        if (gameProgressDto.getGame() == null) {
            errors.add("Game is required");
        } else {
            List<String> gameErrors = GameValidator.validate(gameProgressDto.getGame());
            if (!gameErrors.isEmpty()) {
                errors.add("Game validation errors: " + String.join(", ", gameErrors));
            }
        }

        if (gameProgressDto.getStatus() == null) {
            gameProgressDto.setStatus(GameStatus.IN_PROGRESS);
        }

        if (gameProgressDto.getScore() != null && gameProgressDto.getScore() < 0) {
            errors.add("Score cannot be negative");
        }

        if (gameProgressDto.getLastPlayed() != null &&
                gameProgressDto.getLastPlayed().isAfter(Instant.now())) {
            errors.add("Last played date cannot be in the future");
        }

        if (gameProgressDto.getTimePlayed() == null) {
            gameProgressDto.setTimePlayed(0);
        } else if (gameProgressDto.getTimePlayed() < 0) {
            errors.add("Time played cannot be negative");
        }

        if (gameProgressDto.getAttempts() == null) {
            gameProgressDto.setAttempts(0);
        } else if (gameProgressDto.getAttempts() < 0) {
            errors.add("Number of attempts cannot be negative");
        }

        if (gameProgressDto.getWins() == null) {
            gameProgressDto.setWins(0);
        } else if (gameProgressDto.getWins() < 0) {
            errors.add("Number of wins cannot be negative");
        }

        if (gameProgressDto.getLosses() == null) {
            gameProgressDto.setLosses(0);
        } else if (gameProgressDto.getLosses() < 0) {
            errors.add("Number of losses cannot be negative");
        }

        if (gameProgressDto.getBestScore() != null && gameProgressDto.getBestScore() < 0) {
            errors.add("Best score cannot be negative");
        }

        if (gameProgressDto.getCurrentStreak() == null) {
            gameProgressDto.setCurrentStreak(0);
        } else if (gameProgressDto.getCurrentStreak() < 0) {
            errors.add("Current streak cannot be negative");
        }

        if (gameProgressDto.getWins() != null &&
                gameProgressDto.getLosses() != null &&
                gameProgressDto.getAttempts() != null) {
            if (gameProgressDto.getWins() + gameProgressDto.getLosses() > gameProgressDto.getAttempts()) {
                errors.add("Sum of wins and losses cannot exceed total attempts");
            }
        }

        if (gameProgressDto.getBestScore() != null &&
                gameProgressDto.getScore() != null &&
                gameProgressDto.getBestScore() < gameProgressDto.getScore()) {
            errors.add("Best score cannot be less than current score");
        }

        if (gameProgressDto.getStatus() == GameStatus.COMPLETED &&
                gameProgressDto.getAttempts() != null &&
                gameProgressDto.getAttempts() == 0) {
            errors.add("Game with COMPLETED status should have at least one attempt");
        }

        return errors;
    }
}
