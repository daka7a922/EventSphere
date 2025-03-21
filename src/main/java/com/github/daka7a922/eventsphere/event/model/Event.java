package com.github.daka7a922.eventsphere.event.model;

import com.github.daka7a922.eventsphere.ticket.model.Ticket;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
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

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne
    private Venue venue;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

}
