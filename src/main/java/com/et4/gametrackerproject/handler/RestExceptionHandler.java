package com.et4.gametrackerproject.handler;

import com.et4.gametrackerproject.exception.EntityNotFoundException;
import com.et4.gametrackerproject.exception.ErrorCodes;
import com.et4.gametrackerproject.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

/**
 * Gestionnaire global d'exceptions pour l'API REST.
 * Cette classe intercepte les exceptions spécifiques lancées par l'application
 * et les convertit en réponses HTTP appropriées avec des messages d'erreur structurés.
 * Cela permet de centraliser la gestion des erreurs et d'assurer une réponse cohérente
 * pour le client, quel que soit l'endroit où l'exception se produit.
 */
@RestControllerAdvice // Applique ce gestionnaire d'exceptions à tous les contrôleurs REST
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Gère les exceptions de type EntityNotFoundException.
     * Ces exceptions sont levées lorsqu'une entité demandée n'est pas trouvée dans la base de données.
     *
     * @param exception L'exception capturée
     * @param webRequest La requête web qui a déclenché l'exception
     * @return Une réponse HTTP 404 (NOT_FOUND) avec un DTO d'erreur détaillé
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {
        final HttpStatus notFound = HttpStatus.NOT_FOUND; // code 404
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorDto, notFound);
    }

    /**
     * Gère les exceptions de type InvalidEntityException.
     * Ces exceptions sont levées lors de la validation des entités, quand les données
     * ne respectent pas les contraintes définies (ex : champs obligatoires manquants).
     *
     * @param exception L'exception capturée
     * @param webRequest La requête web qui a déclenché l'exception
     * @return Une réponse HTTP 400 (BAD_REQUEST) avec un DTO d'erreur contenant les détails des violations
     */
    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(errorDto, badRequest);
    }

    /**
     * Gère les exceptions de type BadCredentialsException.
     * Ces exceptions sont levées par Spring Security lorsque l'authentification échoue
     * en raison d'identifiants incorrects.
     *
     * @param exception L'exception capturée
     * @param webRequest La requête web qui a déclenché l'exception
     * @return Une réponse HTTP 400 (BAD_REQUEST) avec un message d'erreur générique pour éviter
     *         de fournir des informations trop précises sur la nature de l'échec (sécurité).
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception, WebRequest webRequest) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto = ErrorDto.builder()
                .code(ErrorCodes.BAD_CREDENTIALS)
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(Collections.singletonList("Login et/ou mot de passe incorrect"))
                .build();
        return new ResponseEntity<>(errorDto, badRequest);
    }

    /*
     * Note : On pourrait ajouter d'autres gestionnaires d'exceptions pour couvrir davantage de cas,
     * par exemple :
     * * AccessDeniedException pour les problèmes d'autorisation
     * * MethodArgumentNotValidException pour les erreurs de validation des paramètres
     * * Exception générale comme "fallback" pour toute exception non gérée explicitement
     */
}
