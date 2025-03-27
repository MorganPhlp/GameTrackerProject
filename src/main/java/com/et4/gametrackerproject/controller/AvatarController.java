package com.et4.gametrackerproject.controller;

import com.et4.gametrackerproject.controller.api.AvatarApi;
import com.et4.gametrackerproject.dto.AvatarDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
public class AvatarController implements AvatarApi {

    @Autowired
    private AvatarService avatarService;

    public AvatarController(AvatarService avatarservice) {
        this.avatarService = avatarservice;
    }

    @Override
    public AvatarDto getAvatarById(Integer id) {
        return avatarService.getAvatarById(id);
    }

    @Override
    public List<AvatarDto> getAllAvatars() {
        return avatarService.getAllAvatars();
    }

    @Override
    public AvatarDto uploadAvatar(MultipartFile multipartFile) {
        return avatarService.uploadAvatar(multipartFile);
    }

    @Override
    public AvatarDto updateAvatar(Integer id, AvatarDto avatarDto) {
        return avatarService.updateAvatar(id, avatarDto);
    }

    @Override
    public void deleteAvatar(Integer id) {
        avatarService.deleteAvatar(id);
    }

    @Override
    public boolean avatarExists(Integer id) {
        return avatarService.avatarExists(id);
    }

    @Override
    public List<AvatarDto> getAllDefaultAvatars() {
        return avatarService.getAllDefaultAvatars();
    }


}
