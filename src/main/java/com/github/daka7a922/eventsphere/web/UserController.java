package com.github.daka7a922.eventsphere.web;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.service.EventService;
import com.github.daka7a922.eventsphere.security.AuthenticationDetails;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.service.UserService;
import com.github.daka7a922.eventsphere.web.dto.UserEditRequest;
import com.github.daka7a922.eventsphere.web.mapper.DtoMapper;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final EventService eventService;

    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/{id}/user-settings")
    public ModelAndView getUserProfilePage(@PathVariable UUID id){

        User user = userService.getUserById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-settings");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userEditRequest", DtoMapper.mapUserToUserEditRequest(user));
        modelAndView.addObject("activePage", "user-settings");

        return modelAndView;
    }

    @PutMapping("/{id}/user-settings")
    public ModelAndView updateUserProfile(@PathVariable UUID id, @Valid UserEditRequest userEditRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            User user = userService.getUserById(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user-settings");
            modelAndView.addObject("user", user);
            modelAndView.addObject("userEditRequest", userEditRequest);
            modelAndView.addObject("activePage", "user-settings");
            return modelAndView;
        }

        userService.updateUserSettings(id, userEditRequest);

        return new ModelAndView("redirect:/users/" + id + "/user-settings");

    }

    @GetMapping("/user-management")
    public ModelAndView getUserManagementPage(@AuthenticationPrincipal AuthenticationDetails userDetails){

        User user = userService.getByUsername(userDetails.getUsername());
        List<User> allUsers = userService.getAllUsers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-management");
        modelAndView.addObject("user", user);
        modelAndView.addObject("allUsers", allUsers);
        modelAndView.addObject("activePage", "user-management");


        return modelAndView;
    }

    @PutMapping("/{id}/role")
    public String changeUserRole(@PathVariable("id") UUID id, @RequestParam("role") String role) {

        userService.changeUserRole(id, role);

        return "redirect:/users/user-management";
    }

    @PutMapping("/save-event/{id}")
    public String saveEvent(@PathVariable("id") UUID id, @AuthenticationPrincipal AuthenticationDetails userDetails) {

        User user = userService.getByUsername(userDetails.getUsername());

        Event event = eventService.getById(id);

        user.getSavedEvents().add(event);

        userService.save(user);

        return "redirect:/events/event-details/" + event.getId();
    }

    @GetMapping("/saved-events")
    public ModelAndView getSavedEvents(@AuthenticationPrincipal AuthenticationDetails userDetails){

        User user = userService.getByUsername(userDetails.getUsername());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("saved-events");
        modelAndView.addObject("user", user);
        modelAndView.addObject("activePage", "saved-events");
        return modelAndView;
    }



}
