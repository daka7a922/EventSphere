package com.github.daka7a922.eventsphere.event.repository;

import com.github.daka7a922.eventsphere.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    Event getEventById(UUID id);
}
