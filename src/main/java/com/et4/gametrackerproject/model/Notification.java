package com.et4.gametrackerproject.model;

import com.et4.gametrackerproject.enums.NotifType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "notification")
public class Notification extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NotifType type;

    @Column(name = "content", columnDefinition = "json", nullable = false)
    private String content;

    @Column(name = "is_read")
    @Builder.Default
    private Boolean isRead = false;
}
