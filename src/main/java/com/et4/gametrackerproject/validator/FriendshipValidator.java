package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.FriendshipDto;
import com.et4.gametrackerproject.enums.FriendshipStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendshipValidator {
    public static List<String> validate(FriendshipDto friendshipDto) {
        List<String> errors = new ArrayList<>();

        if(friendshipDto == null){
            errors.add("User1 is required");
            errors.add("User2 is required");
            return errors;
        }

        if(friendshipDto.getUser1() == null){
            errors.add("User1 is required");
        } else {
            List<String> userErrors = UserValidator.validate(friendshipDto.getUser1());
            if (!userErrors.isEmpty()) {
                errors.add("User1 validation errors: " + String.join(", ", userErrors));
            }
        }
        if(friendshipDto.getUser2() == null){
            errors.add("User2 is required");
        } else {
            List<String> userErrors = UserValidator.validate(friendshipDto.getUser2());
            if (!userErrors.isEmpty()) {
                errors.add("User2 validation errors: " + String.join(", ", userErrors));
            }
        }

        if (friendshipDto.getUser1() != null && friendshipDto.getUser2() != null &&
                friendshipDto.getUser1().getId() != null && friendshipDto.getUser2().getId() != null) {
            if (Objects.equals(friendshipDto.getUser1().getId(), friendshipDto.getUser2().getId())) {
                errors.add("User1 and User2 cannot be the same person");
            }
        }

        if(friendshipDto.getStatus() == null){
            friendshipDto.setStatus(FriendshipStatus.PENDING);
        }

        return errors;
    }
}
