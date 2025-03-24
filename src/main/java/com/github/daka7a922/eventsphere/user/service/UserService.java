package com.github.daka7a922.eventsphere.user.service;

import com.github.daka7a922.eventsphere.exception.DomainException;
import com.github.daka7a922.eventsphere.security.AuthenticationDetails;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.model.UserRole;
import com.github.daka7a922.eventsphere.user.repository.UserRepository;
import com.github.daka7a922.eventsphere.web.dto.RegisterRequest;
import com.github.daka7a922.eventsphere.web.dto.UserEditRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(@Valid RegisterRequest registerRequest) {

        Optional<User> optionalUser = userRepository.findByUsername(registerRequest.getUsername());

        if (optionalUser.isPresent()) {
            throw new DomainException("Username already exists");
        }

        User user = userRepository.save(initializeUser(registerRequest));

        log.info("Successfully create new user account for username [%s] and id [%s]".formatted(user.getUsername(), user.getId()));

        return user;
    }

    private User initializeUser(RegisterRequest registerRequest) {

        LocalDateTime now = LocalDateTime.now();

        return User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .isActive(true)
                .createdOn(now)
                .updatedOn(now)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new DomainException("User not found"));


        return new AuthenticationDetails(user.getId(), username, user.getPassword(), user.getRole(), user.isActive());
    }

    public User getByUsername(String username) {

        return userRepository.getByUsername(username);
    }

    public User getUserById(UUID id) {

        return userRepository.findById(id).orElseThrow(() -> new DomainException("User with id [%s] does not exist".formatted(id)));
    }

    public void updateUserSettings(UUID id, @Valid UserEditRequest userEditRequest) {

        User user = getUserById(id);

        if (userEditRequest.getFirstName() != null && !userEditRequest.getFirstName().trim().isEmpty()) {
            user.setFirstName(userEditRequest.getFirstName());
        }

        if (userEditRequest.getLastName() != null && !userEditRequest.getLastName().trim().isEmpty()) {
            user.setLastName(userEditRequest.getLastName());
        }

        if (userEditRequest.getEmail() != null && !userEditRequest.getEmail().trim().isEmpty()) {
            user.setEmail(userEditRequest.getEmail());
        }

        if (userEditRequest.getProfilePicture() != null && !userEditRequest.getProfilePicture().trim().isEmpty()) {
            user.setProfilePicture(userEditRequest.getProfilePicture());
        }

        user.setUpdatedOn(LocalDateTime.now());

        userRepository.save(user);

        log.info("Successfully update user profile for user %s with id [%s]".formatted(user.getUsername(),user.getId()));



    }
}
