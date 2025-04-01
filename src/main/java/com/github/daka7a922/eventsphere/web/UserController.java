package com.github.daka7a922.eventsphere.web;

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

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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



}
