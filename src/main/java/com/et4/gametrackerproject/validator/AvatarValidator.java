package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.AvatarDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AvatarValidator {
    public static List<String> validate(AvatarDto avatarDto) {
        List<String> errors = new ArrayList<>();

        if (avatarDto == null) {
            errors.add("Photo is required");
            errors.add("Users are required");
            return errors;
        }

        if(!StringUtils.hasLength(avatarDto.getPhoto())){
            errors.add("Photo is required");
        }
        //TODO : Ajouter la validation que c'est une image

        if(avatarDto.getUsers() == null){
            errors.add("Users are required");
        } else {
            for (UserDto user : avatarDto.getUsers()) {
                List<String> userErrors = UserValidator.validate(user);
                if (!userErrors.isEmpty()) {
                    errors.add("User validation errors: " + String.join(", ", userErrors));
                }
            }
        }

        return errors;
    }
}
