package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //--- READ

    /***
     * Получить полные данные о пользователе
     */
    @GetMapping
    public String index(Model model, Principal principal) {
        UserEntity user = userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", Collections.singleton(user));
        return "pages/user";
    }

}
