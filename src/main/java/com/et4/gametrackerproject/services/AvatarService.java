package com.et4.gametrackerproject.services;

import com.et4.gametrackerproject.dto.AvatarDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface AvatarService {

    //Méthodes de base
    AvatarDto getAvatarById(Integer id);
    List<AvatarDto> getAllAvatars();

    AvatarDto uploadAvatar(MultipartFile file);
    AvatarDto updateAvatar(Integer id, AvatarDto avatarDto);
    void deleteAvatar(Integer id);

    //Méthodes de vérification
    boolean avatarExists(Integer id);

    //Avatars par défaut
    List<AvatarDto> getAllDefaultAvatars();

    //Administration
    List<AvatarDto> getUnusedAvatars();

    Long getUserCountByAvatarId(Integer avatarId);
    List<AvatarDto> getMostPopularAvatars();
}
