package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.GameCommentApi;
import com.et4.gametrackerproject.dto.GameCommentDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.services.GameCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class GameCommentController implements GameCommentApi {

    @Autowired
    private GameCommentService gameCommentService;

    public GameCommentController(GameCommentService gameCommentService) {
        this.gameCommentService = gameCommentService;
    }


    @Override
    public GameCommentDto createComment(GameCommentDto commentDto) {
        return gameCommentService.createComment(commentDto);
    }

    @Override
    public GameCommentDto updateCommentContent(Integer commentId, String newContent) {
        return gameCommentService.updateCommentContent(commentId, newContent);
    }

    @Override
    public void deleteComment(Integer commentId) {
        gameCommentService.deleteComment(commentId);
    }

    @Override
    public GameCommentDto getCommentById(Integer commentId) {
        return gameCommentService.getCommentById(commentId);
    }

    @Override
    public GameCommentDto addReplyToComment(Integer parentCommentId, GameCommentDto replyDto) {
        return gameCommentService.addReplyToComment(parentCommentId, replyDto);
    }

    @Override
    public Page<GameCommentDto> getCommentReplies(Integer parentCommentId, Pageable pageable) {
        return gameCommentService.getCommentReplies(parentCommentId, pageable);
    }

    @Override
    public void removeAllRepliesFromComment(Integer parentCommentId) {
        gameCommentService.removeAllRepliesFromComment(parentCommentId);
    }

    @Override
    public Set<UserDto> getCommentLikers(Integer commentId) {
        return gameCommentService.getCommentLikers(commentId);
    }

    @Override
    public Page<GameCommentDto> getCommentsForGame(Integer gameId, Pageable pageable) {
        return gameCommentService.getCommentsForGame(gameId, pageable);
    }

    @Override
    public Page<GameCommentDto> getCommentsByUser(Integer userId, Pageable pageable) {
        return gameCommentService.getCommentsByUser(userId, pageable);
    }

    @Override
    public List<GameCommentDto> getRecentComments(int hours) {
        return gameCommentService.getRecentComments(hours);
    }

    @Override
    public Page<GameCommentDto> searchComments(String searchTerm, Pageable pageable) {
        return gameCommentService.searchComments(searchTerm, pageable);
    }

    @Override
    public Page<GameCommentDto> getReportedComments(Pageable pageable) {
        return gameCommentService.getReportedComments(pageable);
    }

    @Override
    public Map<Integer, Long> getTopCommentedGames(int limit) {
        return gameCommentService.getTopCommentedGames(limit);
    }

    @Override
    public List<GameCommentDto> getCommentsForGame(Integer gameId) {
        return gameCommentService.getCommentsForGame(gameId);
    }

    @Override
    public Long countCommentsForGame(Integer gameId) {
        return gameCommentService.countCommentsForGame(gameId);
    }

    @Override
    public Long countCommentsByUser(Integer userId) {
        return gameCommentService.countCommentsByUser(userId);
    }

    @Override
    public Long countRepliesForComment(Integer commentId) {
        return gameCommentService.countRepliesForComment(commentId);
    }

    @Override
    public List<GameCommentDto> getRecentCommentsForGame(Integer gameId) {
        return gameCommentService.getRecentCommentsForGame(gameId);
    }

    @Override
    public Map<Integer, Long> getMostLikedCommentsForGame(Integer gameId, int limit) {
        return gameCommentService.getMostLikedCommentsForGame(gameId, limit);
    }

    @Override
    public Map<Integer, Long> getMostDiscussedCommentsForGame(Integer gameId, int limit) {
        return gameCommentService.getMostDiscussedCommentsForGame(gameId, limit);
    }
}
