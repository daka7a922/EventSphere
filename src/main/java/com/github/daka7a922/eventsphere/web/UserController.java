package com.github.daka7a922.eventsphere.web;

import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.service.UserService;
import com.github.daka7a922.eventsphere.web.dto.UserEditRequest;
import com.github.daka7a922.eventsphere.web.mapper.DtoMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
