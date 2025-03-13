package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.GameCommentDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.services.GameCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GameCommentServiceImpl implements GameCommentService {
    @Override
    public GameCommentDto createComment(GameCommentDto commentDto) {
        return null;
    }

    @Override
    public GameCommentDto updateCommentContent(Integer commentId, String newContent) {
        return null;
    }

    @Override
    public void deleteComment(Integer commentId) {

    }

    @Override
    public GameCommentDto getCommentById(Integer commentId) {
        return null;
    }

    @Override
    public GameCommentDto addReplyToComment(Integer parentCommentId, GameCommentDto replyDto) {
        return null;
    }

    @Override
    public Page<GameCommentDto> getCommentReplies(Integer parentCommentId, Pageable pageable) {
        return null;
    }

    @Override
    public void removeAllRepliesFromComment(Integer parentCommentId) {

    }

    @Override
    public GameCommentDto toggleLikeOnComment(Integer commentId, Integer userId) {
        return null;
    }

    @Override
    public int getCommentLikesCount(Integer commentId) {
        return 0;
    }

    @Override
    public boolean hasUserLikedComment(Integer commentId, Integer userId) {
        return false;
    }

    @Override
    public Set<UserDto> getCommentLikers(Integer commentId) {
        return Set.of();
    }

    @Override
    public Page<GameCommentDto> getCommentsForGame(Integer gameId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GameCommentDto> getCommentsByUser(Integer userId, Pageable pageable) {
        return null;
    }

    @Override
    public List<GameCommentDto> getRecentComments(int hours) {
        return List.of();
    }

    @Override
    public Page<GameCommentDto> searchComments(String searchTerm, Pageable pageable) {
        return null;
    }

    @Override
    public GameCommentDto moderateComment(Integer commentId, String moderationReason) {
        return null;
    }

    @Override
    public GameCommentDto hideComment(Integer commentId, boolean hidden) {
        return null;
    }

    @Override
    public void reportComment(Integer commentId, Integer reporterId, String reason) {

    }

    @Override
    public Page<GameCommentDto> getReportedComments(Pageable pageable) {
        return null;
    }

    @Override
    public Map<Integer, Long> getTopCommentedGames(int limit) {
        return Map.of();
    }

    @Override
    public GameCommentDto restoreCommentVersion(Integer commentId, Integer versionNumber) {
        return null;
    }

    @Override
    public List<GameCommentDto> getCommentHistory(Integer commentId) {
        return List.of();
    }

    @Override
    public boolean isCommentOwner(Integer commentId, Integer userId) {
        return false;
    }

    @Override
    public boolean hasReplies(Integer commentId) {
        return false;
    }
}
