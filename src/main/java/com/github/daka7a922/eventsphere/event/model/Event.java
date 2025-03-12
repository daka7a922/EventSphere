package com.github.daka7a922.eventsphere.event.model;

import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private String location;

    private Date date;

    private LocalTime time;

    @ManyToOne
    private User organizer;

    @OneToOne
    private Venue venue;
}
