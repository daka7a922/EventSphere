package com.github.daka7a922.eventsphere.venue.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

   @Column(nullable = false)
    private String address;

    private String city;

    private String country;

    private String description;

    @Enumerated(EnumType.STRING)
    private VenueType type;

}
