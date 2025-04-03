package com.github.daka7a922.eventsphere.user.service;


import com.github.daka7a922.eventsphere.exception.DomainException;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.model.UserRole;
import com.github.daka7a922.eventsphere.user.repository.UserRepository;
import com.github.daka7a922.eventsphere.web.dto.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void shouldThrowExceptionWhenRegisteringUserWithExistingUsername() {
        // given
        String existingUsername = "existingUser";
        RegisterRequest registerRequest = new RegisterRequest(existingUsername, "password");
        given(userRepository.findByUsername(existingUsername)).willReturn(Optional.of(new User()));

        // when
        DomainException exception = assertThrows(DomainException.class, () -> userService.register(registerRequest));

        // then
        assertThat(exception.getMessage()).isEqualTo("Username already exists");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void shouldRegisterANewUserWithUniqueUsernameAndPassword() {
        // given
        String uniqueUsername = "uniqueUser";
        String password = "password";
        RegisterRequest registerRequest = new RegisterRequest(uniqueUsername, password);
        given(userRepository.findByUsername(uniqueUsername)).willReturn(Optional.empty());

        // when
        User registeredUser = userService.register(registerRequest);

        // then
        assertThat(registeredUser.getUsername()).isEqualTo(uniqueUsername);
        assertThat(registeredUser.getPassword()).isNotEqualTo(password);
        verify(userRepository).save(userArgumentCaptor.capture());
        assertThat(userArgumentCaptor.getValue().getUsername()).isEqualTo(uniqueUsername);
        assertThat(userArgumentCaptor.getValue().getPassword()).isNotEqualTo(password);
        assertThat(userArgumentCaptor.getValue().getRole()).isEqualTo(UserRole.USER);
        assertThat(userArgumentCaptor.getValue().isActive()).isTrue();
        assertThat(userArgumentCaptor.getValue().getCreatedOn()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
        assertThat(userArgumentCaptor.getValue().getUpdatedOn()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
    }
}
