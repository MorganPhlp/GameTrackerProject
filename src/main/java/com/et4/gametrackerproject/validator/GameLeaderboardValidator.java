package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.GameLeaderboardDto;
import com.et4.gametrackerproject.enums.LeaderboardPeriod;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GameLeaderboardValidator {

    public static List<String> validate(GameLeaderboardDto gameLeaderboardDto) {
        List<String> errors = new ArrayList<>();

        if (gameLeaderboardDto == null) {
            errors.add("User is required");
            errors.add("Score is required");
            errors.add("Date is required");
            errors.add("Leaderboard period is required");
            return errors;
        }

        if (gameLeaderboardDto.getUser() == null) {
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(gameLeaderboardDto.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }

        if (gameLeaderboardDto.getScore() == null) {
            errors.add("Score is required");
        } else if (gameLeaderboardDto.getScore() < 0) {
            errors.add("Score cannot be negative");
        }

        if (gameLeaderboardDto.getRankNumber() != null && gameLeaderboardDto.getRankNumber() <= 0) {
            errors.add("Rank number must be positive");
        }

        if (gameLeaderboardDto.getDate() == null) {
            errors.add("Date is required");
        } else if (gameLeaderboardDto.getDate().isAfter(Instant.now())) {
            errors.add("Leaderboard entry date cannot be in the future");
        }

        if (gameLeaderboardDto.getPeriod() == null) {
            errors.add("Leaderboard period is required");
        }

        if (gameLeaderboardDto.getDate() != null && gameLeaderboardDto.getPeriod() != null) {
            Instant now = Instant.now();
            Instant periodStart = calculatePeriodStart(gameLeaderboardDto.getPeriod(), now);

            if (gameLeaderboardDto.getDate().isBefore(periodStart)) {
                errors.add("Leaderboard entry date must be within the specified period");
            }
        }

        return errors;
    }

    /**
     * Calcule la date de début de la période spécifiée
     */
    private static Instant calculatePeriodStart(LeaderboardPeriod period, Instant now) {
        return switch (period) {
            case DAILY -> now.minus(java.time.Duration.ofDays(1));
            case WEEKLY -> now.minus(java.time.Duration.ofDays(7));
            case MONTHLY -> now.minus(java.time.Duration.ofDays(31));
            case YEARLY -> now.minus(java.time.Duration.ofDays(365));
            default -> Instant.EPOCH;
        };
    }
}
