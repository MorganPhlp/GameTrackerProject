package com.et4.gametrackerproject.services.auth;

import com.et4.gametrackerproject.dto.UserDto;
import com.et4.gametrackerproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service d'authentification qui implémente l'interface UserDetailsService de Spring Security.
 * Cette classe fait le pont entre la logique d'authentification de Spring Security
 * et les utilisateurs stockés dans votre base de données.
 */
@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    /**
     * Service qui gère les opérations liées aux utilisateurs.
     * Il est utilisé pour récupérer les données des utilisateurs depuis la base de données.
     */
    @Autowired
    private UserService userService;

    /**
     * Charge un utilisateur par son nom d'utilisateur (email dans ce cas).
     * Cette méthode est appelée par Spring Security lors du processus d'authentification
     * pour vérifier les identifiants de l'utilisateur.
     *
     * @param email L'email de l'utilisateur (utilisé comme nom d'utilisateur)
     * @return Un objet UserDetails contenant les informations de l'utilisateur nécessaires
     *         pour l'authentification et l'autorisation
     * @throws UsernameNotFoundException si l'utilisateur n'est pas trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Récupère les informations de l'utilisateur depuis le service utilisateur
        UserDto user = userService.getUserByEmail(email);

        // Si l'utilisateur n'est pas trouvé, userService.getUserByEmail() devrait lancer
        // une exception UsernameNotFoundException (vérifiez l'implémentation)

        // Crée un objet UserDetails de Spring Security à partir des données de l'utilisateur
        // Le constructeur prend:
        // 1. Le nom d'utilisateur (email dans ce cas)
        // 2. Le mot de passe de l'utilisateur (qui sera vérifié par Spring Security)
        // 3. Une liste d'autorités/rôles (vide dans cet exemple - ArrayList<>())
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());

        // NOTE: Pour ajouter des rôles ou des autorisations, vous devriez remplacer
        // new ArrayList<>() par une collection d'objets GrantedAuthority, par exemple:
        //
        // List<GrantedAuthority> authorities = new ArrayList<>();
        // authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
