package com.et4.gametrackerproject.controller.api;

import com.et4.gametrackerproject.dto.FavoriteGameDto;
import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.et4.gametrackerproject.utils.Constants.APP_ROOT;

public interface FavoriteGameApi {

    //Opérations de base

    @PostMapping(value = APP_ROOT + "/favorites/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    FavoriteGameDto addToFavorites(@RequestBody FavoriteGameDto favoriteDto);

    @DeleteMapping(value = APP_ROOT + "/favorites/remove/{favoriteId}")
    void removeFromFavorites(@PathVariable Integer favoriteId);

    //Récupération

    @GetMapping(value = APP_ROOT + "/favorites/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameDto> getFavoriteGamesForUser(@PathVariable Integer userId);

    @GetMapping(value = APP_ROOT + "/favorites/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> getUsersWhoFavoritedGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/favorites/exists/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    boolean isGameFavoritedByUser(@PathVariable Integer userId,@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/favorites/{favoriteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    FavoriteGameDto getFavoriteById(@PathVariable Integer favoriteId);

    // Gestion des listes

    @DeleteMapping(value = APP_ROOT + "/favorites/clear/{userId}")
    void clearUserFavorites(@PathVariable Integer userId);

    //Statistiques

    @GetMapping(value = APP_ROOT + "/favorites/count/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Long getTotalFavoritesCountForGame(@PathVariable Integer gameId);

    @GetMapping(value = APP_ROOT + "/favorites/mostFavorited/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Long> getMostFavoritedGames(@PathVariable int limit);

    @GetMapping(value = APP_ROOT + "/favorites/count/category", produces = MediaType.APPLICATION_JSON_VALUE)
    Map<Integer, Long> getFavoriteCountByGameCategory();

    //Vérifier si un jeu est favori pour un utilisateur spécifique
    @GetMapping(value = APP_ROOT + "/favorites/find/{userId}/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<FavoriteGameDto> findFavoriteByUserAndGame(@PathVariable Integer userId, @PathVariable Integer gameId);

    //Récupérer les jeux favoris ajoutés récemment par un utilisateur
    @GetMapping(value = APP_ROOT + "/favorites/recent/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FavoriteGameDto> getRecentlyAddedFavoritesForUser(@PathVariable Integer userId);

    //Trouver les favoris communs entre deux utilisateurs
    @GetMapping(value = APP_ROOT + "/favorites/common/{userId1}/{userId2}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GameDto> getCommonFavoriteGames(@PathVariable Integer userId1, @PathVariable Integer userId2);

    //Compter le nombre de jeux favoris pour un utilisateur
    @GetMapping(value = APP_ROOT + "/favorites/count/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Long countFavoritesByUser(@PathVariable Integer userId);

    //Supprimer un jeu favori pour un utilisateur spécifique
    @DeleteMapping(value = APP_ROOT + "/favorites/delete/{userId}/{gameId}")
    void deleteFavoriteByUserAndGame(@PathVariable Integer userId, @PathVariable Integer gameId);


}
