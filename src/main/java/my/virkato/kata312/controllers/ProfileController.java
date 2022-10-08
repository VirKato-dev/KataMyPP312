package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ProfileController {

    private UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showProfile(Model model, @AuthenticationPrincipal UserEntity userEntity) {
        model.addAttribute("user", userEntity);
        return "user/profile";
    }
}
