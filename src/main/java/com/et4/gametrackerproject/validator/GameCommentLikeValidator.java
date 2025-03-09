package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.GameCommentLikeDto;
import java.util.ArrayList;
import java.util.List;

public class GameCommentLikeValidator {

    public static List<String> validate(GameCommentLikeDto gameCommentLikeDto) {
        List<String> errors = new ArrayList<>();

        if (gameCommentLikeDto == null) {
            errors.add("User is required");
            errors.add("Comment is required");
            return errors;
        }

        if (gameCommentLikeDto.getUser() == null) {
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(gameCommentLikeDto.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }
        if (gameCommentLikeDto.getComment() == null) {
            errors.add("Comment is required");
        } else {
            List<String> commentErrors = GameCommentValidator.validate(gameCommentLikeDto.getComment());
            if (!commentErrors.isEmpty()) {
                errors.add("Comment validation errors: " + String.join(", ", commentErrors));
            }
        }

        return errors;
    }
}
