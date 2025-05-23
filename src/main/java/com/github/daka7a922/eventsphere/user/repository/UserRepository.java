package com.github.daka7a922.eventsphere.user.repository;

import com.github.daka7a922.eventsphere.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    User getByUsername(String username);

}
