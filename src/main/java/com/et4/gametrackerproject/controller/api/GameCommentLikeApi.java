package com.et4.gametrackerproject.controller.api;


import com.et4.gametrackerproject.dto.GameCommentLikeDto;
import com.et4.gametrackerproject.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface GameCommentLikeApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/game-comment-likes/toggle/{userId}/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Créer un commentaire like ",description = "Créer un commentaire like")
    @ApiResponse(responseCode = "200", description = "commentaire like créé")
    GameCommentLikeDto addCommentLike(@PathVariable Integer userId,@PathVariable Integer commentId);

    @DeleteMapping(value = APP_ROOT + "/game-comment-likes/remove/{likeId}")
    @Operation(summary = "Supprimer un commentaire like",description = "Supprimer un commentaire like")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaire like supprimé"),
            @ApiResponse(responseCode = "404", description = "commentaire like non trouvé")
    })
    void removeLike(@PathVariable Integer likeId);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/{likeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un commentaire like par son ID",description = "Récupérer un commentaire like par son ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaire like trouvé"),
            @ApiResponse(responseCode = "404", description = "commentaire like non trouvé")
    })
    GameCommentLikeDto getLikeById(@PathVariable Integer likeId);

    // Récupération

    @GetMapping(value = APP_ROOT + "/game-comment-likes/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les likes d'un commentaire",description = "Récupérer les likes d'un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "likes de commentaire trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun like trouvé pour ce commentaire")
    })
    Page<GameCommentLikeDto> getLikesForComment(@PathVariable Integer commentId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les likes d'un utilisateur",description = "Récupérer les likes d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "likes d'utilisateur trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun like trouvé pour cet utilisateur")
    })
    Page<GameCommentLikeDto> getLikesByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/count/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer le nombre de likes d'un commentaire",description = "Récupérer le nombre de likes d'un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "nombre de likes trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun like trouvé pour ce commentaire")
    })
    Long getLikeCountForComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/most-liked/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les commentaires les plus aimés",description = "Récupérer les commentaires les plus aimés")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "commentaires les plus aimés trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun commentaire trouvé")
    })
    Map<Integer, Long> getMostLikedComments(@PathVariable int limit);

    //Modération

    @GetMapping(value = APP_ROOT + "/game-comment-likes/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les likes récents",description = "Récupérer les likes récents")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "likes récents trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun like trouvé")
    })
    Page<GameCommentLikeDto> getRecentLikes(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/admin/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer tous les likes",description = "Récupérer tous les likes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "tous les likes trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun like trouvé")
    })
    Page<GameCommentLikeDto> getAllLikes(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/comment/{commentId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer les utilisateurs qui ont aimé un commentaire",description = "Récupérer les utilisateurs qui ont aimé un commentaire")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "utilisateurs trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur trouvé")
    })
    List<UserDto> getUsersWhoLikedComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/game-comment-likes/user/{userId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer le nombre de likes d'un utilisateur",description = "Récupérer le nombre de likes d'un utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "nombre de likes trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun like trouvé pour cet utilisateur")
    })
    Long countLikesByUser(@PathVariable Integer userId);

}
