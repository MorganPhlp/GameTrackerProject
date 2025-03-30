package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameCommentDto;
import com.et4.gametrackerproject.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameCommentApi {

    // Opérations de base

    @PostMapping(value = APP_ROOT + "/comments/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer un commentaire",description = "Créer un commentaire pour un jeu")
    @ApiResponse(responseCode = "200", description = "commentaire créé")
    GameCommentDto createComment(@RequestBody GameCommentDto commentDto);

    @PutMapping(value = APP_ROOT + "/comments/update/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour le contenu d'un commentaire",description = "Mettre à jour le contenu d'un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaire mis à jour"),
            @ApiResponse(responseCode = "404", description = "commentaire non trouvé")
    })
    GameCommentDto updateCommentContent(@PathVariable Integer commentId,@RequestBody String newContent);

    @DeleteMapping(value = APP_ROOT + "/comments/delete/{commentId}")
    @Operation(summary = "Supprimer un commentaire",description = "Supprimer un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaire supprimé"),
            @ApiResponse(responseCode = "404", description = "commentaire non trouvé")
    })
    void deleteComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un commentaire par son ID",description = "Récupérer un commentaire par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaire trouvé"),
            @ApiResponse(responseCode = "404", description = "commentaire non trouvé")
    })
    GameCommentDto getCommentById(@PathVariable Integer commentId);

    @PostMapping(value = APP_ROOT + "/comments/reply/{parentCommentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ajouter une réponse à un commentaire",description = "Ajouter une réponse à un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "réponse ajoutée"),
            @ApiResponse(responseCode = "404", description = "commentaire parent non trouvé")
    })
    GameCommentDto addReplyToComment(@PathVariable Integer parentCommentId,@RequestBody GameCommentDto replyDto);

    @GetMapping(value = APP_ROOT + "/comments/replies/{parentCommentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les réponses d'un commentaire",description = "Récupérer les réponses d'un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "réponses trouvées"),
            @ApiResponse(responseCode = "404", description = "commentaire parent non trouvé")
    })
    Page<GameCommentDto> getCommentReplies(@PathVariable Integer parentCommentId, Pageable pageable);

    @DeleteMapping(value = APP_ROOT + "/comments/replies/delete/{parentCommentId}")
    @Operation(summary = "Supprimer toutes les réponses d'un commentaire",description = "Supprimer toutes les réponses d'un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "réponses supprimées"),
            @ApiResponse(responseCode = "404", description = "commentaire parent non trouvé")
    })
    void removeAllRepliesFromComment(@PathVariable Integer parentCommentId);


    @GetMapping(value = APP_ROOT + "/comments/likers/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les utilisateurs qui ont aimé un commentaire",description = "Récupérer les utilisateurs qui ont aimé un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "utilisateurs trouvés"),
            @ApiResponse(responseCode = "404", description = "commentaire non trouvé")
    })
    Set<UserDto> getCommentLikers(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires d'un jeu",description = "Récupérer les commentaires d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "jeu non trouvé")
    })
    Page<GameCommentDto> getCommentsForGame(@PathVariable Integer gameId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires d'un utilisateur",description = "Récupérer les commentaires d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "utilisateur non trouvé")
    })
    Page<GameCommentDto> getCommentsByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/recent/{hours}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires récents",description = "Récupérer les commentaires récents")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "aucun commentaire trouvé")
    })
    List<GameCommentDto> getRecentComments(@PathVariable int hours);

    @GetMapping(value = APP_ROOT + "/comments/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher des commentaires",description = "Rechercher des commentaires par mot-clé")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "aucun commentaire trouvé")
    })
    Page<GameCommentDto> searchComments(@RequestBody String searchTerm, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/reported", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires signalés",description = "Récupérer les commentaires signalés")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "aucun commentaire trouvé")
    })
    Page<GameCommentDto> getReportedComments(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/stats/top/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les jeux les plus commentés",description = "Récupérer les jeux les plus commentés")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "jeux trouvés"),
            @ApiResponse(responseCode = "404", description = "aucun jeu trouvé")
    })
    Map<Integer, Long> getTopCommentedGames(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer tous les commentaires d'un jeu",description = "Récupérer tous les commentaires d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "jeu non trouvé")
    })
    List<GameCommentDto> getCommentsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Compter le nombre de commentaires d'un jeu",description = "Compter le nombre de commentaires d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "nombre de commentaires trouvé"),
            @ApiResponse(responseCode = "404", description = "jeu non trouvé")
    })
    Long countCommentsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/comments/user/{userId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Compter le nombre de commentaires d'un utilisateur",description = "Compter le nombre de commentaires d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "nombre de commentaires trouvé"),
            @ApiResponse(responseCode = "404", description = "utilisateur non trouvé")
    })
    Long countCommentsByUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/comments/replies/{commentId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Compter le nombre de réponses d'un commentaire",description = "Compter le nombre de réponses d'un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "nombre de réponses trouvé"),
            @ApiResponse(responseCode = "404", description = "commentaire non trouvé")
    })
    Long countRepliesForComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires récents d'un jeu",description = "Récupérer les commentaires récents d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "jeu non trouvé")
    })
    List<GameCommentDto> getRecentCommentsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/liked/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires les plus aimés d'un jeu",description = "Récupérer les commentaires les plus aimés d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "jeu non trouvé")
    })
    Map<Integer, Long> getMostLikedCommentsForGame(@PathVariable Integer gameId, @PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/discussed/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires les plus discutés d'un jeu",description = "Récupérer les commentaires les plus discutés d'un jeu")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires trouvés"),
            @ApiResponse(responseCode = "404", description = "jeu non trouvé")
    })
    Map<Integer, Long> getMostDiscussedCommentsForGame(@PathVariable Integer gameId, @PathVariable int limit);

}
