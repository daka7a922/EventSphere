package com.github.daka7a922.eventsphere.venue.repository;

import com.github.daka7a922.eventsphere.venue.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VenueRepository extends JpaRepository<Venue, UUID> {

}
