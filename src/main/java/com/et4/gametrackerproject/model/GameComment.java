package com.et4.gametrackerproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "gamecomment")
public class GameComment extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private GameComment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private Set<GameComment> replies = new HashSet<>();

    @OneToMany(mappedBy = "comment")
    private Set<GameCommentLike> likes = new HashSet<>();
}
