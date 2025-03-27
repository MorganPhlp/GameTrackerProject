package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.AchievementDto;
import com.et4.gametrackerproject.enums.AchievementRarity;
import com.et4.gametrackerproject.enums.AchievementType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface AchievementApi {

    //Méthodes de récupération

    @GetMapping(value =APP_ROOT + "/achievements/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getAllAchievements();

    @GetMapping(value = APP_ROOT + "/achievements/{idAchievement}", produces = MediaType.APPLICATION_JSON_VALUE)
    AchievementDto getAchievementById(@PathVariable("idAchievement") Integer id);

    @GetMapping(value = APP_ROOT + "/achievements/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getAchievementsByType(@PathVariable AchievementType type);

    @GetMapping(value = APP_ROOT + "/achievements/rarity/{rarity}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getAchievementsByRarity(@PathVariable AchievementRarity rarity);

    @GetMapping(value = APP_ROOT + "/achievements/active", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getActiveAchievements();

    //Gestion des achievements

    @PostMapping(value = APP_ROOT + "/achievements/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    AchievementDto createAchievement(@RequestBody AchievementDto achievementDto);

    @PutMapping(value = APP_ROOT + "/achievements/update/{idAchievement}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    AchievementDto updateAchievement(@PathVariable("idAchievement") Integer id,@RequestBody AchievementDto achievementDto);

    @GetMapping(value = APP_ROOT + "/achievements/secrets", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AchievementDto> getSecretAchievements();
}
