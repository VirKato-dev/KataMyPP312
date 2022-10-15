package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.UserService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/user")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = "logout")
    public String logout() {
        return "redirect:/logout";
    }

    @GetMapping()
    public String showProfile(Model model, @AuthenticationPrincipal UserEntity userEntity) {
        model.addAttribute("user", userEntity);
        Collection<UserEntity> users = Collections.singleton(userEntity);
        model.addAttribute("users", users);
        return "user/profile";
    }

    @GetMapping("/{id}")
    public String showProfileFromAdmin(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.get(id));
        Collection<UserEntity> users = Collections.singleton(userService.get(id));
        model.addAttribute("users", users);
        return "user/profile";
    }
}
