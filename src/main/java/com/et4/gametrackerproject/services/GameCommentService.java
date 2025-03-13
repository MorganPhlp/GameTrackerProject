package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.GameCommentDto;
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

    //Likes et interactions
    GameCommentDto toggleLikeOnComment(Integer commentId, Integer userId);
    int getCommentLikesCount(Integer commentId);
    boolean hasUserLikedComment(Integer commentId, Integer userId);
    Set<UserDto> getCommentLikers(Integer commentId);

    // Récupération des commentaires
    Page<GameCommentDto> getCommentsForGame(Integer gameId, Pageable pageable);
    Page<GameCommentDto> getCommentsByUser(Integer userId, Pageable pageable);
    List<GameCommentDto> getRecentComments(int hours);
    Page<GameCommentDto> searchComments(String searchTerm, Pageable pageable);

    // Modération
    GameCommentDto moderateComment(Integer commentId, String moderationReason);
    GameCommentDto hideComment(Integer commentId, boolean hidden);
    void reportComment(Integer commentId, Integer reporterId, String reason);
    Page<GameCommentDto> getReportedComments(Pageable pageable);

    //Statistiques

    Map<Integer, Long> getTopCommentedGames(int limit);

    //Gestion de version
    GameCommentDto restoreCommentVersion(Integer commentId, Integer versionNumber);
    List<GameCommentDto> getCommentHistory(Integer commentId);

    //Vérifications
    boolean isCommentOwner(Integer commentId, Integer userId);
    boolean hasReplies(Integer commentId);
}