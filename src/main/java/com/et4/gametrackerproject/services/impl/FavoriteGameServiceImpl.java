package com.et4.gametrackerproject.services.impl;

import com.et4.gametrackerproject.dto.FavoriteGameDto;
import com.et4.gametrackerproject.dto.GameDto;
import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.exception.EntityNotFoundException;
import com.et4.gametrackerproject.model.FavoriteGame;
import com.et4.gametrackerproject.model.Game;
import com.et4.gametrackerproject.model.User;
import com.et4.gametrackerproject.repository.FavoriteGameRepository;
import com.et4.gametrackerproject.services.FavoriteGameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteGameServiceImpl implements FavoriteGameService {

    private final FavoriteGameRepository favoriteGameRepository;

    public FavoriteGameServiceImpl(FavoriteGameRepository favoriteGameRepository) {
        this.favoriteGameRepository = favoriteGameRepository;
    }

    @Override
    public List<GameDto> getFavoriteGamesForUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("L'ID utilisateur ne peut être null");
        }

        List<FavoriteGame> favorites = favoriteGameRepository.findByUser(User.builder().id(userId).build());
        return favorites.stream()
                .map(fav -> GameDto.fromEntity(fav.getGame()))
                .toList();
    }

    @Override
    public List<UserDto> getUsersWhoFavoritedGame(Integer gameId) {
        if (gameId == null) {
            throw new IllegalArgumentException("L'ID du jeu ne peut être null");
        }

        List<FavoriteGame> favorites = favoriteGameRepository.findByGame(Game.builder().id(gameId).build());
        return favorites.stream()
                .map(fav -> UserDto.fromEntity(fav.getUser()))
                .toList();
    }

    @Override
    public FavoriteGameDto getFavoriteById(Integer favoriteId) {
        if (favoriteId == null) {
            throw new IllegalArgumentException("L'ID du favori ne peut être null");
        }

        FavoriteGame favoriteGame = favoriteGameRepository.findById(favoriteId)
                .orElseThrow(() -> new EntityNotFoundException("Favori introuvable"));

        return FavoriteGameDto.fromEntity(favoriteGame);
    }

    @Override
    public Long getTotalFavoritesCountForGame(Integer gameId) {
        if (gameId == null) {
            throw new IllegalArgumentException("L'ID du jeu ne peut être null");
        }
        return favoriteGameRepository.countByGame(Game.builder().id(gameId).build());
    }

    @Override
    public Map<String, Long> getMostFavoritedGames(int limit) {
        return favoriteGameRepository.findMostPopularGames().stream()
                .limit(limit)
                .collect(Collectors.toMap(
                        obj -> ((Game) obj[0]).getName(),
                        obj -> (Long) obj[1]
                ));
    }

    //Compter les favoris par catégorie de jeu
    @Override
    public Map<Integer, Long> getFavoriteCountByGameCategory() {
        return favoriteGameRepository.findMostPopularGames().stream()
                .collect(Collectors.toMap(
                        obj -> ((Game) obj[0]).getCategory().getDeclaringClass().getModifiers(),
                        obj -> (Long) obj[1],
                        Long::sum
                ));
    }

    //=============================================== AJOUT/ DELETE ==================================

    @Override
    public FavoriteGameDto addToFavorites(FavoriteGameDto favoriteDto) {
        if (favoriteDto == null || favoriteDto.getGame() == null) {
            throw new IllegalArgumentException("Les informations du favori sont invalides");
        }

        FavoriteGame favoriteGame = FavoriteGame.builder()
                .user(User.builder().id(favoriteDto.getUser().getId()).build())
                .game(Game.builder().id(favoriteDto.getGame().getId()).build())
                .build();

        FavoriteGame savedFavorite = favoriteGameRepository.save(favoriteGame);
        return FavoriteGameDto.fromEntity(savedFavorite);
    }

    @Override
    public void removeFromFavorites(Integer favoriteId) {
        if (favoriteId == null) {
            throw new IllegalArgumentException("L'ID du favori ne peut être null");
        }
        favoriteGameRepository.deleteById(favoriteId);
    }

    @Override
    public void clearUserFavorites(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("L'ID utilisateur ne peut être null");
        }

        favoriteGameRepository.deleteAll(favoriteGameRepository.findByUser(User.builder().id(userId).build()));
    }

    @Override
    public boolean isGameFavoritedByUser(Integer userId, Integer gameId) {
        if (userId == null || gameId == null) {
            throw new IllegalArgumentException("L'ID utilisateur et l'ID du jeu ne peuvent être null");
        }

        return favoriteGameRepository.existsByUserAndGame(
                User.builder().id(userId).build(),
                Game.builder().id(gameId).build()
        );
    }

    //Vérifier si un jeu est favori pour un utilisateur spécifique
    @Override
    public Optional<FavoriteGameDto> findFavoriteByUserAndGame(Integer userId, Integer gameId) {
        if (userId == null || gameId == null) {
            throw new IllegalArgumentException("L'ID utilisateur et l'ID du jeu doivent être fournis");
        }
        Optional<FavoriteGame> favoriteOpt = favoriteGameRepository.findByUserAndGame(
                User.builder().id(userId).build(),
                Game.builder().id(gameId).build()
        );
        return favoriteOpt.map(FavoriteGameDto::fromEntity);
    }

    //Récupérer les jeux favoris ajoutés récemment par un utilisateur
    @Override
    public List<FavoriteGameDto> getRecentlyAddedFavoritesForUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("L'ID utilisateur ne peut être null");
        }
        List<FavoriteGame> favorites = favoriteGameRepository.findRecentlyAddedFavoritesByUser(
                User.builder().id(userId).build()
        );
        if (favorites.isEmpty()) {
            throw new EntityNotFoundException("Aucun favori récent trouvé pour l'utilisateur " + userId);
        }
        return favorites.stream()
                .map(FavoriteGameDto::fromEntity)
                .collect(Collectors.toList());
    }

    //Trouver les favoris communs entre deux utilisateurs
    @Override
    public List<GameDto> getCommonFavoriteGames(Integer userId1, Integer userId2) {
        if (userId1 == null || userId2 == null) {
            throw new IllegalArgumentException("Les deux IDs utilisateurs doivent être fournis");
        }
        List<Game> commonGames = favoriteGameRepository.findCommonFavoriteGames(userId1, userId2);
        if (commonGames.isEmpty()) {
            throw new EntityNotFoundException("Aucun jeu favori commun trouvé entre les utilisateurs " + userId1 + " et " + userId2);
        }
        return commonGames.stream()
                .map(GameDto::fromEntity)
                .collect(Collectors.toList());
    }

    //Compter le nombre de jeux favoris pour un utilisateur
    @Override
    public Long countFavoritesByUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("L'ID utilisateur ne peut être null");
        }
        return favoriteGameRepository.countByUser(User.builder().id(userId).build());
    }

    //Supprimer un jeu favori pour un utilisateur spécifique
    @Override
    public void deleteFavoriteByUserAndGame(Integer userId, Integer gameId) {
        if (userId == null || gameId == null) {
            throw new IllegalArgumentException("L'ID utilisateur et l'ID du jeu doivent être fournis");
        }
        User user = User.builder().id(userId).build();
        Game game = Game.builder().id(gameId).build();
        // Optionally, check if the favorite exists before deletion:
        Optional<FavoriteGame> favoriteOpt = favoriteGameRepository.findByUserAndGame(user, game);
        if (favoriteOpt.isEmpty()) {
            throw new EntityNotFoundException("Favori introuvable pour la suppression");
        }
        favoriteGameRepository.deleteByUserAndGame(user, game);
    }

}
