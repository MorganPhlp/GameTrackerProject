package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.AvatarDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.services.AvatarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
public class AvatarServiceImpl implements AvatarService {
    @Override
    public AvatarDto getAvatarById(Integer id) {
        return null;
    }

    @Override
    public List<AvatarDto> getAllAvatars() {
        return List.of();
    }

    @Override
    public AvatarDto uploadAvatar(AvatarDto avatarDto) {
        return null;
    }

    @Override
    public AvatarDto updateAvatar(Integer id, AvatarDto avatarDto) {
        return null;
    }

    @Override
    public void deleteAvatar(Integer id) {

    }

    @Override
    public AvatarDto getUserAvatar(Integer userId) {
        return null;
    }

    @Override
    public AvatarDto updateUserAvatar(Integer userId, Integer avatarId) {
        return null;
    }

    @Override
    public AvatarDto uploadAvatarImage(Integer avatarId, MultipartFile file) {
        return null;
    }

    @Override
    public void updateAvatarImage(Integer avatarId, MultipartFile file) {

    }

    @Override
    public boolean avatarExists(Integer id) {
        return false;
    }

    @Override
    public AvatarDto getDefaultAvatar() {
        return null;
    }

    @Override
    public List<AvatarDto> getAllDefaultAvatars() {
        return List.of();
    }

    @Override
    public Set<UserDto> getCurrentUsersUsingAvatar(Integer avatarId) {
        return Set.of();
    }
}
