package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.DailyGameSessionDto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DailyGameSessionValidator {
    public static List<String> validate(DailyGameSessionDto dailyGameSessionDto) {
        List<String> errors = new ArrayList<>();

        if(dailyGameSessionDto == null){
            errors.add("User is required");
            errors.add("Date is required");
            return errors;
        }

        if(dailyGameSessionDto.getUser() == null){
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(dailyGameSessionDto.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }

        if(dailyGameSessionDto.getDate() == null){
            errors.add("Date is required");
        } else if(dailyGameSessionDto.getDate().isAfter(Instant.now())){
            errors.add("Date cannot be in the future");
        }

        if(dailyGameSessionDto.getTotalTimePlayed() == null){
            dailyGameSessionDto.setTotalTimePlayed(0);
        } else if(dailyGameSessionDto.getTotalTimePlayed() < 0){
            errors.add("Total time played must be greater than 0");
        }

        if(dailyGameSessionDto.getGamesPlayed() == null){
            dailyGameSessionDto.setGamesPlayed(0);
        } else if(dailyGameSessionDto.getGamesPlayed() < 0){
            errors.add("Games played must be greater than 0");
        }

        if(dailyGameSessionDto.getUniqueGamesPlayed() == null){
            dailyGameSessionDto.setUniqueGamesPlayed(0);
        } else if(dailyGameSessionDto.getUniqueGamesPlayed() < 0){
            errors.add("Unique games played must be greater than 0");
        }

        if (dailyGameSessionDto.getUniqueGamesPlayed() != null &&
                dailyGameSessionDto.getGamesPlayed() != null &&
                dailyGameSessionDto.getUniqueGamesPlayed() > dailyGameSessionDto.getGamesPlayed()) {
            errors.add("Unique games played cannot be greater than total games played");
        }

        return errors;
    }
}
