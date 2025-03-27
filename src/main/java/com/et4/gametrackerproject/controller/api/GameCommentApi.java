package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.GameCommentDto;
import com.et4.gametrackerproject.dto.UserDto;
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
    GameCommentDto createComment(@RequestBody GameCommentDto commentDto);

    @PutMapping(value = APP_ROOT + "/comments/update/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameCommentDto updateCommentContent(@PathVariable Integer commentId,@RequestBody String newContent);

    @DeleteMapping(value = APP_ROOT + "/comments/delete/{commentId}")
    void deleteComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/comments/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    GameCommentDto getCommentById(@PathVariable Integer commentId);

    //Gestion des réponses

    @PostMapping(value = APP_ROOT + "/comments/reply/{parentCommentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GameCommentDto addReplyToComment(@PathVariable Integer parentCommentId,@RequestBody GameCommentDto replyDto);

    @GetMapping(value = APP_ROOT + "/comments/replies/{parentCommentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentDto> getCommentReplies(@PathVariable Integer parentCommentId, Pageable pageable);

    @DeleteMapping(value = APP_ROOT + "/comments/replies/delete/{parentCommentId}")
    void removeAllRepliesFromComment(@PathVariable Integer parentCommentId);


    @GetMapping(value = APP_ROOT + "/comments/likers/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Set<UserDto> getCommentLikers(@PathVariable Integer commentId);

    // Récupération des commentaires

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentDto> getCommentsForGame(@PathVariable Integer gameId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentDto> getCommentsByUser(@PathVariable Integer userId, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/recent/{hours}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameCommentDto> getRecentComments(@PathVariable int hours);

    @GetMapping(value = APP_ROOT + "/comments/search", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentDto> searchComments(@RequestBody String searchTerm, Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/reported", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<GameCommentDto> getReportedComments(Pageable pageable);

    @GetMapping(value = APP_ROOT + "/comments/stats/top/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getTopCommentedGames(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameCommentDto> getCommentsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countCommentsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/comments/user/{userId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countCommentsByUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/comments/replies/{commentId}/count", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countRepliesForComment(@PathVariable Integer commentId);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameCommentDto> getRecentCommentsForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/liked/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getMostLikedCommentsForGame(@PathVariable Integer gameId, @PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/comments/game/{gameId}/discussed/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getMostDiscussedCommentsForGame(@PathVariable Integer gameId, @PathVariable int limit);

}
