package com.github.daka7a922.eventsphere.user.service;

import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.model.UserRole;
import com.github.daka7a922.eventsphere.web.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public UserInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        //initializing Admin and Organizer accounts for testing purposes

        if (!userService.getAllUsers().isEmpty()){
            return;
        }

        RegisterRequest registerAdmin = RegisterRequest.builder()
                .username("admin")
                .password("123123")
                .build();


        User adminUser = userService.register(registerAdmin);
        userService.updateRole(adminUser,UserRole.ADMIN);

        RegisterRequest registerOrganizer = RegisterRequest.builder()
                .username("EventSphere")
                .password("123123")
                .build();

        User organizerUser = userService.register(registerOrganizer);
        userService.updateRole(organizerUser,UserRole.EVENT_ORGANIZER);


    }


}
