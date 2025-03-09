package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.FavoriteGameDto;

import java.util.ArrayList;
import java.util.List;

public class FavoriteGameValidator {
    public static List<String> validate(FavoriteGameDto favoriteGameDto) {
        List<String> errors = new ArrayList<>();

        if(favoriteGameDto == null){
            errors.add("User is required");
            errors.add("Game is required");
            return errors;
        }

        if(favoriteGameDto.getUser() == null){
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(favoriteGameDto.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }
        if(favoriteGameDto.getGame() == null){
            errors.add("Game is required");
        } else {
            List<String> gameErrors = GameValidator.validate(favoriteGameDto.getGame());
            if (!gameErrors.isEmpty()) {
                errors.add("Game validation errors: " + String.join(", ", gameErrors));
            }
        }

        return errors;
    }
}
