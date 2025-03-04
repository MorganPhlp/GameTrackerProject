package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.FriendshipStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(
        name = "friendship",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id_1", "user_id_2"})
        }
)
public class Friendship extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id_1", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_id_2", nullable = false)
    private User user2;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FriendshipStatus status = FriendshipStatus.PENDING;
}