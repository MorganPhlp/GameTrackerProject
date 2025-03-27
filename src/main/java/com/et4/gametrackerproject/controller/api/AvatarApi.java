package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.AvatarDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface AvatarApi {

    //Méthodes de base
    @GetMapping(value = APP_ROOT + "/avatars/{idAvatar}", produces = MediaType.APPLICATION_JSON_VALUE)
    AvatarDto getAvatarById(@PathVariable("idAvatar") Integer id);

    @GetMapping(value = APP_ROOT + "/avatars/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AvatarDto> getAllAvatars();

    @PostMapping(value = APP_ROOT + "/avatars/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    AvatarDto uploadAvatar(MultipartFile multipartFile);

    @PutMapping(value = APP_ROOT + "/avatars/update/{idAvatar}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    AvatarDto updateAvatar(@PathVariable("idAvatar") Integer id,@RequestBody AvatarDto avatarDto);

    @DeleteMapping(value = APP_ROOT + "/avatars/delete/{idAvatar}")
    void deleteAvatar(@PathVariable("idAvatar") Integer id);

    //Méthodes de vérification

    @GetMapping(value = APP_ROOT + "/avatars/exists/{idAvatar}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean avatarExists(@PathVariable("idAvatar") Integer id);

    //Avatars par défaut
    @GetMapping(value = APP_ROOT + "/avatars/default/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AvatarDto> getAllDefaultAvatars();
}
