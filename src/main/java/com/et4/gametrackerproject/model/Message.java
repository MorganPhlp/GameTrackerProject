package com.et4.gametrackerproject.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "message")
public class Message extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_read")
    private Boolean isRead = false;
}
