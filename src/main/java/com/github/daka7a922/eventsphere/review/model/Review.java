package com.github.daka7a922.eventsphere.review.model;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.user.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String comment;

    private LocalDateTime reviewDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;
}
