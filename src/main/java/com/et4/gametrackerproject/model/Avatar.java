package com.et4.gametrackerproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "avatar")
public class Avatar extends AbstractEntity{

    @Column(name = "photo", nullable = false)
    private String photo;

    @OneToMany(mappedBy = "avatar")
    private Set<User> users = new HashSet<>();
}
