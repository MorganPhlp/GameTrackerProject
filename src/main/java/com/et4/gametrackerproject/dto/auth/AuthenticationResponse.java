package com.et4.gametrackerproject.dto.auth;

import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data Transfer Object) représentant la réponse envoyée au client après une authentification réussie.
 * Ce DTO encapsule le token JWT (JSON Web Token) qui sera utilisé par le client pour les requêtes
 * ultérieures nécessitant une authentification. Ce token contient les informations d'identité
 * de l'utilisateur et est signé pour garantir son intégrité.
 */
@Data       // Génère automatiquement getters, setters, equals, hashCode, et toString
@Builder    // Permet la création d'instances avec le pattern Builder
public class AuthenticationResponse {

    /**
     * Token d'accès JWT qui sera utilisé pour authentifier les requêtes ultérieures.
     * Ce token doit être inclus dans l'en-tête Authorization des requêtes HTTP
     * avec le préfixe "Bearer ".
     * Exemple d'utilisation côté client :
     * Authorization : Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
     */
    private String accessToken;

    /*
     * Note : On pourrait enrichir cette réponse avec des informations supplémentaires comme :
     * * Un refreshToken pour obtenir un nouveau token sans ré-authentification
     * * La durée de validité du token (tokenExpiresIn)
     * * Des informations basiques sur l'utilisateur connecté (id, nom, rôles, etc.)
     */

    // Exemple de champs supplémentaires que vous pourriez ajouter:
    // private String refreshToken;
    // private Long tokenExpiresIn;
    // private String tokenType = "Bearer";
    // private UserDTO user;
}
