package com.github.daka7a922.eventsphere.ticket.model;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Event event;

    @ManyToOne
    private User owner;

}
