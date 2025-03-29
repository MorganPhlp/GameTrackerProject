package com.et4.gametrackerproject.config;

import com.et4.gametrackerproject.filters.JwtRequestFilter;
import com.et4.gametrackerproject.services.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration principale de la sécurité de l'application.
 * Cette classe définit les règles d'authentification et d'autorisation.
 */
@Configuration // Indique à Spring que c'est une classe de configuration
@EnableWebSecurity // Active la configuration de sécurité Web
public class SecurityConfiguration {

    /**
     * Service qui charge les détails des utilisateurs depuis la base de données.
     * Utilisé pour vérifier les identifiants lors de l'authentification.
     */
    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    /**
     * Filtre qui intercepte chaque requête HTTP pour vérifier et valider les JWT.
     */
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * Configure la chaîne de filtres de sécurité.
     * Définit les règles d'accès aux différentes URLs de l'application.
     *
     * @param http L'objet HttpSecurity à configurer
     * @return La chaîne de filtres de sécurité configurée
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive la protection CSRF car nous utilisons des JWT
                // (Les JWT fournissent une protection contre CSRF par leur conception)
                .csrf(AbstractHttpConfigurer::disable)

                // Configuration des règles d'autorisation pour les requêtes HTTP
                .authorizeHttpRequests(auth -> auth
                        // Les endpoints d'authentification et Swagger UI sont accessibles sans authentification
                        .requestMatchers("/**/authenticate",
                                // Endpoints Swagger/OpenAPI pour la documentation de l'API
                                "/v2/api-docs",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/**",
                                "v3/api-docs/**").permitAll()
                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()
                )

                // Configuration de la gestion des sessions
                // STATELESS signifie que le serveur ne maintient pas d'état de session,
                // Cela est cohérent avec l'utilisation de JWT (authentification sans état)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Définit le fournisseur d'authentification à utiliser
                // (Vérifiera les identifiants des utilisateurs)
                .authenticationProvider(authenticationProvider())

                // Ajoute notre filtre JWT avant le filtre d'authentification standard
                // Cela permet de vérifier les JWT avant que Spring Security ne cherche
                // à authentifier l'utilisateur via d'autres mécanismes
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Configure le fournisseur d'authentification.
     * Il utilise notre service de détails utilisateur pour vérifier les identifiants
     * et notre encodeur de mot de passe pour vérifier les mots de passe.
     *
     * @return Le fournisseur d'authentification configuré
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // Définit le service qui charge les données des utilisateurs
        daoAuthenticationProvider.setUserDetailsService(applicationUserDetailsService);
        // Définit l'encodeur de mot de passe pour vérifier les mots de passe
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Expose le gestionnaire d'authentification de Spring Security.
     * Ce gestionnaire est utilisé pour authentifier les utilisateurs
     * lors du processus de connexion.
     *
     * @param authenticationConfiguration La configuration d'authentification
     * @return Le gestionnaire d'authentification
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Définit l'encodeur de mot de passe à utiliser dans l'application.
     * BCrypt est un algorithme de hachage sécurisé pour les mots de passe.
     *
     * @return L'encodeur de mot de passe
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
