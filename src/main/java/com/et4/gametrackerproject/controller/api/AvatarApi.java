package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.AvatarDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

@Tag(name = "Avatar API", description = "API pour les avatars")
public interface AvatarApi {

    //Méthodes de base
    @GetMapping(value = APP_ROOT + "/avatars/{idAvatar}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie un avatar par son ID", description = "Renvoie un avatar par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'avatar a été trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar n'a été trouvé avec cet id")
    })
    AvatarDto getAvatarById(@PathVariable("idAvatar") Integer id);


    @GetMapping(value = APP_ROOT + "/avatars/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des avatars", description = "Renvoie la liste des avatars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des avatar a été trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar n'a été trouvé dans la BDD")
    })
    List<AvatarDto> getAllAvatars();

    @PostMapping(value = APP_ROOT + "/avatars/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crée un nouvel avatar", description = "Crée un nouvel avatar")
    @ApiResponse(responseCode = "200", description = "L'avatar a été créé")
    AvatarDto uploadAvatar(MultipartFile multipartFile);

    @PutMapping(value = APP_ROOT + "/avatars/update/{idAvatar}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Met à jour un avatar", description = "Met à jour un avatar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'avatar a été mis à jour"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar n'a été trouvé avec cet id")
    })
    AvatarDto updateAvatar(@PathVariable("idAvatar") Integer id,@RequestBody AvatarDto avatarDto);

    @DeleteMapping(value = APP_ROOT + "/avatars/delete/{idAvatar}")
    @Operation(summary = "Supprime un avatar", description = "Supprime un avatar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'avatar a été supprimé"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar n'a été trouvé avec cet id")
    })
    void deleteAvatar(@PathVariable("idAvatar") Integer id);


    @GetMapping(value = APP_ROOT + "/avatars/exists/{idAvatar}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Vérifie si un avatar existe", description = "Vérifie si un avatar existe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'avatar existe"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar n'a été trouvé avec cet id")
    })
    boolean avatarExists(@PathVariable("idAvatar") Integer id);

    @GetMapping(value = APP_ROOT + "/avatars/default/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des avatars par défaut", description = "Renvoie la liste des avatars par défaut")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des avatars par défaut a été trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar par défaut n'a été trouvé dans la BDD")
    })
    List<AvatarDto> getAllDefaultAvatars();

    @GetMapping(value = APP_ROOT + "/avatars/unused", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des avatars non utilisés", description = "Renvoie la liste des avatars non utilisés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des avatars non utilisés a été trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar non utilisé n'a été trouvé dans la BDD")
    })
    List<AvatarDto> getUnusedAvatars();

    @GetMapping(value = APP_ROOT + "/avatars/userCount/{idAvatar}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie le nombre d'utilisateurs ayant cet avatar", description = "Renvoie le nombre d'utilisateurs ayant cet avatar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le nombre d'utilisateurs a été trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar n'a été trouvé avec cet id")
    })
    Long getUserCountByAvatarId(@PathVariable Integer idAvatar);

    @GetMapping(value = APP_ROOT + "/avatars/mostPopular", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoie la liste des avatars les plus populaires", description = "Renvoie la liste des avatars les plus populaires")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des avatars les plus populaires a été trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun avatar n'a été trouvé dans la BDD")
    })
    List<AvatarDto> getMostPopularAvatars();
}
