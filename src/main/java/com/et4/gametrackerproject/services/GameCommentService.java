package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameCommentDto;
import com.et4.gametrackerproject.dto.GameCommentLikeDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameCommentService {

    // Opérations de base
    GameCommentDto createComment(GameCommentDto commentDto);
    GameCommentDto updateCommentContent(Integer commentId, String newContent);
    void deleteComment(Integer commentId);
    GameCommentDto getCommentById(Integer commentId);

    //Gestion des réponses
    GameCommentDto addReplyToComment(Integer parentCommentId, GameCommentDto replyDto);
    Page<GameCommentDto> getCommentReplies(Integer parentCommentId, Pageable pageable);
    void removeAllRepliesFromComment(Integer parentCommentId);

    Set<UserDto> getCommentLikers(Integer commentId);

    // Récupération des commentaires
    Page<GameCommentDto> getCommentsForGame(Integer gameId, Pageable pageable);
    Page<GameCommentDto> getCommentsByUser(Integer userId, Pageable pageable);
    List<GameCommentDto> getRecentComments(int hours);
    Page<GameCommentDto> searchComments(String searchTerm, Pageable pageable);

    Page<GameCommentDto> getReportedComments(Pageable pageable);

    //Statistiques

    Map<Integer, Long> getTopCommentedGames(int limit);


    List<GameCommentDto> getCommentsForGame(Integer gameId);

    Long countCommentsForGame(Integer gameId);

    Long countCommentsByUser(Integer userId);

    Long countRepliesForComment(Integer commentId);

    List<GameCommentDto> getRecentCommentsForGame(Integer gameId);

    Map<Integer, Long> getMostLikedCommentsForGame(Integer gameId, int limit);

    Map<Integer, Long> getMostDiscussedCommentsForGame(Integer gameId, int limit);
}