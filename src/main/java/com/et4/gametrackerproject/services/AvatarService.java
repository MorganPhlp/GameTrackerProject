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
    AvatarDto uploadAvatar(AvatarDto avatarDto);
    AvatarDto updateAvatar(Integer id, AvatarDto avatarDto);
    void deleteAvatar(Integer id);

    //Gestion des utilisateurs
    AvatarDto getUserAvatar(Integer userId);
    AvatarDto updateUserAvatar(Integer userId, Integer avatarId);

    //Gestion des fichiers
    AvatarDto uploadAvatarImage(Integer avatarId, MultipartFile file);
    void updateAvatarImage(Integer avatarId, MultipartFile file);

    //Méthodes de vérification
    boolean avatarExists(Integer id);

    //Avatars par défaut
    AvatarDto getDefaultAvatar();
    List<AvatarDto> getAllDefaultAvatars();

    //Administration
    Set<UserDto> getCurrentUsersUsingAvatar(Integer avatarId);
}
