package com.et4.gametrackerproject.validator;

import com.et4.gametrackerproject.dto.MessageDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageValidator {
    public static List<String> validate(MessageDto message) {
        List<String> errors = new ArrayList<>();

        if (message == null) {
            errors.add("Sender is required");
            errors.add("Receiver is required");
            errors.add("Message content is required");
            return errors;
        }

        if (message.getSender() == null) {
            errors.add("Sender is required");
        } else {
            List<String> senderErrors = UserValidator.validate(message.getSender());
            if (!senderErrors.isEmpty()) {
                errors.add("Sender validation errors: " + String.join(", ", senderErrors));
            }
        }

        if (message.getReceiver() == null) {
            errors.add("Receiver is required");
        } else {
            List<String> receiverErrors = UserValidator.validate(message.getReceiver());
            if (!receiverErrors.isEmpty()) {
                errors.add("Receiver validation errors: " + String.join(", ", receiverErrors));
            }
        }

        if (!StringUtils.hasLength(message.getContent())) {
            errors.add("Message content is required");
        } else if (message.getContent().length() > 500) { // Limite arbitraire
            errors.add("Message content cannot exceed 500 characters");
        }

        if(message.getIsRead() == null){
            message.setIsRead(false);
        }

        if (message.getSender() != null &&
                message.getReceiver() != null &&
                message.getSender().getId() != null &&
                message.getSender().getId().equals(message.getReceiver().getId())) {
            errors.add("Sender and receiver cannot be the same user");
        }

        return errors;
    }
}
