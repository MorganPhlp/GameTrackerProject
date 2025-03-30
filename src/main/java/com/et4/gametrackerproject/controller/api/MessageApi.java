package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.MessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Envoyer un message", description = "Envoyer un message")
    @ApiResponse(responseCode = "200", description = "Message envoyé")
    MessageDto sendMessage(@RequestBody MessageDto messageDto);

    @PutMapping(value = APP_ROOT + "/message/{messageId}/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour le contenu d'un message", description = "Mettre à jour le contenu d'un message")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message mis à jour"),
            @ApiResponse(responseCode = "404", description = "Message non trouvé")
    })
    MessageDto updateMessageContent(@PathVariable Integer messageId,@RequestBody String newContent);

    @DeleteMapping(value = APP_ROOT + "/message/{messageId}")
    @Operation(summary = "Supprimer un message", description = "Supprimer un message")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message supprimé"),
            @ApiResponse(responseCode = "404", description = "Message non trouvé")
    })
    void deleteMessage(@PathVariable Integer messageId);

    @GetMapping(value = APP_ROOT + "/message/{messageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un message par son ID", description = "Récupérer un message par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message trouvé"),
            @ApiResponse(responseCode = "404", description = "Message non trouvé")
    })
    MessageDto getMessageById(@PathVariable Integer messageId);

    @GetMapping(value = APP_ROOT + "/message/conversation/{user1Id}/{user2Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer la conversation entre deux utilisateurs", description = "Récupérer la conversation entre deux utilisateurs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conversation trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucune conversation trouvée entre ces deux utilisateurs")
    })
    Page<MessageDto> getConversation(@PathVariable Integer user1Id,@PathVariable Integer user2Id, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/sender/{senderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les messages envoyés par un utilisateur", description = "Récupérer les messages envoyés par un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Messages trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun message trouvé pour cet utilisateur")
    })
    Page<MessageDto> getMessagesBySender(@PathVariable Integer senderId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/receiver/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les messages reçus par un utilisateur", description = "Récupérer les messages reçus par un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Messages trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun message trouvé pour cet utilisateur")
    })
    Page<MessageDto> getMessagesByReceiver(@PathVariable Integer receiverId, Pageable pageable);

    //Gestion des états

    @PutMapping(value = APP_ROOT + "/message/{messageId}/mark-read", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Marquer un message comme lu", description = "Marquer un message comme lu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message marqué comme lu"),
            @ApiResponse(responseCode = "404", description = "Message non trouvé")
    })
    MessageDto markAsRead(@PathVariable Integer messageId);

    @PutMapping(value = APP_ROOT + "/message/conversation/{user1Id}/{user2Id}/mark-read", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Marquer une conversation comme lue", description = "Marquer une conversation comme lue")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conversation marquée comme lue"),
            @ApiResponse(responseCode = "404", description = "Aucune conversation trouvée entre ces deux utilisateurs")
    })
    void markConversationAsRead(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @GetMapping(value = APP_ROOT + "/message/unread/count/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Compter le nombre de messages non lus pour un utilisateur", description = "Compter le nombre de messages non lus pour un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nombre de messages non lus trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun message trouvé pour cet utilisateur")
    })
    int countUnreadMessages(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/message/history/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer l'historique des messages d'un utilisateur", description = "Récupérer l'historique des messages d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Historique des messages trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun message trouvé pour cet utilisateur")
    })
    Page<MessageDto> getMessageHistory(@PathVariable Integer userId,@RequestBody Instant from,@RequestBody Instant to, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/search/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher des messages dans l'historique d'un utilisateur", description = "Rechercher des messages dans l'historique d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Messages trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun message trouvé pour cet utilisateur")
    })
    Page<MessageDto> searchMessages(@PathVariable Integer userId,@RequestBody String query, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/message/recent/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les messages récents d'un utilisateur", description = "Récupérer les messages récents d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Messages récents trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun message trouvé pour cet utilisateur")
    })
    List<MessageDto> getRecentMessages(@PathVariable Integer userId,@RequestBody int hours);

    @DeleteMapping(value = APP_ROOT + "/message/conversation/{user1Id}/{user2Id}")
    @Operation(summary = "Supprimer une conversation entre deux utilisateurs", description = "Supprimer une conversation entre deux utilisateurs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conversation supprimée"),
            @ApiResponse(responseCode = "404", description = "Aucune conversation trouvée entre ces deux utilisateurs")
    })
    void deleteConversation(@PathVariable Integer user1Id,@PathVariable Integer user2Id);

    @PutMapping(value = APP_ROOT + "/message/{messageId}/encrypt", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Chiffrer le contenu d'un message", description = "Chiffrer le contenu d'un message")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Message chiffré"),
            @ApiResponse(responseCode = "404", description = "Message non trouvé")
    })
    void encryptMessageContent(@PathVariable Integer messageId);
}
