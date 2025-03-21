package com.github.daka7a922.eventsphere.ticket.model;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(nullable = false, unique = true)
    private String ticketCode;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private BigDecimal price;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

}
