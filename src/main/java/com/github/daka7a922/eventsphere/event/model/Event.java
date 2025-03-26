package com.github.daka7a922.eventsphere.event.model;

import com.github.daka7a922.eventsphere.ticket.model.Ticket;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.List;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private LocalDateTime dateAndTime;

    private String eventPicture;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private Integer availableTickets;

    @ManyToOne
    private Venue venue;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Ticket> PurchasedTickets = new ArrayList<>();

}
