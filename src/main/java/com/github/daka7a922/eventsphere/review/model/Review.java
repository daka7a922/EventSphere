package com.github.daka7a922.eventsphere.review.model;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String comment;

    private LocalDateTime reviewDate;

//    @ManyToOne
//    private User user;

//    @ManyToOne
//    private Event event;
}
