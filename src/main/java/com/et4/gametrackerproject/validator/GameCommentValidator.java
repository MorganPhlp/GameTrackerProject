package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.GameCommentDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameCommentValidator {

    public static List<String> validate(GameCommentDto gameCommentDto) {
        List<String> errors = new ArrayList<>();

        if (gameCommentDto == null) {
            errors.add("User is required");
            errors.add("Game is required");
            errors.add("Comment content cannot be empty");
            return errors;
        }

        if (gameCommentDto.getUser() == null) {
            errors.add("User is required");
        } else {
            List<String> userErrors = UserValidator.validate(gameCommentDto.getUser());
            if (!userErrors.isEmpty()) {
                errors.add("User validation errors: " + String.join(", ", userErrors));
            }
        }
        if (gameCommentDto.getGame() == null) {
            errors.add("Game is required");
        } else {
            List<String> gameErrors = GameValidator.validate(gameCommentDto.getGame());
            if (!gameErrors.isEmpty()) {
                errors.add("Game validation errors: " + String.join(", ", gameErrors));
            }
        }

        if (!StringUtils.hasText(gameCommentDto.getContent())) {
            errors.add("Comment content cannot be empty");
        } else if (gameCommentDto.getContent().length() > 1000) { // Limite arbitraire
            errors.add("Comment content cannot exceed 1000 characters");
        }

        if (gameCommentDto.getParentComment() != null) {
            if (gameCommentDto.getParentComment().getParentComment() != null) { // Pas de commentaires imbriqués à plus de 2 niveaux
                errors.add("Cannot reply to a reply comment (only 2 levels of comments are allowed)");
            }
        }

        if (gameCommentDto.getLikes() == null) {
            gameCommentDto.setLikes(Set.of());
        }
        if (gameCommentDto.getReplies() == null) {
            gameCommentDto.setReplies(Set.of());
        }

        return errors;
    }
}
