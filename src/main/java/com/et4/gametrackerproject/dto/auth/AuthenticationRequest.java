package com.et4.gametrackerproject.dto.auth;

import lombok.Builder;
import lombok.Data;

/*
 * Imports facultatifs qui pourraient être utiles si vous ajoutez de la validation
 * import jakarta.validation.constraints.NotBlank;
 * import jakarta.validation.constraints.Size;
 */

/**
 * DTO (Data Transfer Object) utilisé pour encapsuler les informations d'authentification
 * envoyées par le client lors d'une tentative de connexion.
 * Ce DTO est utilisé par l'endpoint d'authentification pour recevoir le login et le mot
 * de passe de l'utilisateur de manière structurée.
 */
@Data       // Génère automatiquement getters, setters, equals, hashCode, et toString
@Builder    // Permet la création d'instances avec le pattern Builder
public class AuthenticationRequest {

    /**
     * Identifiant de l'utilisateur (nom d'utilisateur ou email selon l'implémentation).
     * Si vous utilisez la validation Bean Validation, vous pourriez ajouter :
     * @NotBlank(message = "Le login est obligatoire")
     */
    private String login;

    /**
     * Mot de passe de l'utilisateur en texte brut.
     * Ce mot de passe sera vérifié par le système d'authentification.
     * Si vous utilisez la validation Bean Validation, vous pourriez ajouter :
     * @NotBlank(message = "Le mot de passe est obligatoire")
     * @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
     */
    private String password;

    /*
     * Note : Ce DTO est utilisé uniquement pour la transmission des données et ne doit pas
     * être stocké ou conservé après l'authentification pour des raisons de sécurité,
     * en particulier en raison du mot de passe en texte brut qu'il contient.
     */
}
