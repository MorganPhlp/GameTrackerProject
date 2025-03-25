package com.et4.gametrackerproject.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

/*
Classe abstraite qui sert de base pour chaque entité qui a les champs communs à chaque entité
Permet aussi un audit automatique des entités
 */

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data // Permet de générer automatiquement getters, setters, toString, equals, hashCode
@MappedSuperclass // SuperClasse pour entités JPA
@EntityListeners(AuditingEntityListener.class) // Ecoute automatiquement les entités
public class AbstractEntity implements Serializable {

    @Id // Indique que clé primaire pour JPA
    @GeneratedValue // Génére automatiquement l'id
    private Integer id;

    @CreatedDate // Automatise remplissage date de création entité
    @Column(name = "creationDate", nullable = false, updatable = false) // Configuration colonne BD
    @JsonIgnore // Exclut le champ de sérialisation JSON
    private Instant creationDate;

    @LastModifiedDate // Automatise maj date de modification
    @Column(name = "lastModifiedDate", nullable = false)
    @JsonIgnore
    private Instant lastModifiedDate;
}