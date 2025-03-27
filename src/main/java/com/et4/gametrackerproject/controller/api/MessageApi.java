package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface MessageApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/message/send", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MessageDto sendMessage(@RequestBody MessageDto messageDto);

    @PutMapping(value = APP_ROOT + "/message/{messageId}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    MessageDto updateMessageContent(@PathVariable Integer messageId,@RequestBody String newContent);

    @DeleteMapping(value = APP_ROOT + "/message/{messageId}")
    void deleteMessage(@PathVariable Integer messageId);

    //Récupération

    @GetMapping(value = APP_ROOT + "/message/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    MessageDto getMessageById(@PathVariable Integer messageId);

    @GetMapping(value = APP_ROOT + "/message/conversation/{user1Id}/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MessageDto> getConversation(@PathVariable Integer user1Id,@PathVariable Integer user2Id, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/sender/{senderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MessageDto> getMessagesBySender(@PathVariable Integer senderId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/receiver/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MessageDto> getMessagesByReceiver(@PathVariable Integer receiverId, Pageable pageable);

    //Gestion des états

    @PutMapping(value = APP_ROOT + "/message/{messageId}/mark-read", produces = MediaType.APPLICATION_JSON_VALUE)
    MessageDto markAsRead(@PathVariable Integer messageId);

    @PutMapping(value = APP_ROOT + "/message/conversation/{user1Id}/{user2Id}/mark-read", produces = MediaType.APPLICATION_JSON_VALUE)
    void markConversationAsRead(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @GetMapping(value = APP_ROOT + "/message/unread/count/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    int countUnreadMessages(@PathVariable Integer userId);

    //Historique

    @GetMapping(value = APP_ROOT + "/message/history/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MessageDto> getMessageHistory(@PathVariable Integer userId,@RequestBody Instant from,@RequestBody Instant to, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/search/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<MessageDto> searchMessages(@PathVariable Integer userId,@RequestBody String query, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/recent/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MessageDto> getRecentMessages(@PathVariable Integer userId,@RequestBody int hours);

    // Modération

    @DeleteMapping(value = APP_ROOT + "/message/conversation/{user1Id}/{user2Id}")
    void deleteConversation(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    // Sécurité

    @PutMapping(value = APP_ROOT + "/message/{messageId}/encrypt", produces = MediaType.APPLICATION_JSON_VALUE)
    void encryptMessageContent(@PathVariable Integer messageId);
}
