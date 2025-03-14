package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.MessageDto;
import com.et4.gametrackerproject.services.MessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public MessageDto sendMessage(MessageDto messageDto) {
        return null;
    }

    @Override
    public MessageDto updateMessageContent(Integer messageId, String newContent) {
        return null;
    }

    @Override
    public void deleteMessage(Integer messageId) {

    }

    @Override
    public MessageDto getMessageById(Integer messageId) {
        return null;
    }

    @Override
    public Page<MessageDto> getConversation(Integer user1Id, Integer user2Id, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MessageDto> getMessagesBySender(Integer senderId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MessageDto> getMessagesByReceiver(Integer receiverId, Pageable pageable) {
        return null;
    }

    @Override
    public MessageDto markAsRead(Integer messageId) {
        return null;
    }

    @Override
    public void markConversationAsRead(Integer user1Id, Integer user2Id) {

    }

    @Override
    public int countUnreadMessages(Integer userId) {
        return 0;
    }

    @Override
    public Page<MessageDto> getMessageHistory(Integer userId, Instant from, Instant to, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MessageDto> searchMessages(Integer userId, String query, Pageable pageable) {
        return null;
    }

    @Override
    public List<MessageDto> getRecentMessages(Integer userId, int hours) {
        return List.of();
    }

    @Override
    public void deleteConversation(Integer user1Id, Integer user2Id) {

    }

    @Override
    public void encryptMessageContent(Integer messageId) {

    }

    @Override
    public void batchDeleteMessages(List<Integer> messageIds) {

    }

    @Override
    public void archiveOldMessages(int monthsThreshold) {

    }

    @Override
    public void exportUserMessages(Integer userId, String outputPath) {

    }

    @Override
    public void notifyNewMessage(Integer messageId) {

    }

    @Override
    public void pushUnreadNotifications(Integer userId) {

    }

    @Override
    public void blockMessagesFromUser(Integer blockerId, Integer blockedId) {

    }

    @Override
    public void unblockUser(Integer userId, Integer unblockedId) {

    }

    @Override
    public boolean isCommunicationAllowed(Integer senderId, Integer receiverId) {
        return false;
    }

    @Override
    public void setConversationMute(Integer userId, Integer conversationPartnerId, boolean muted) {

    }
}
