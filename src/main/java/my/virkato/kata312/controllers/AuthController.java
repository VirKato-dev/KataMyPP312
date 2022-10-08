package my.virkato.kata312.controllers;

import my.virkato.kata312.entities.UserEntity;
import my.virkato.kata312.services.RoleService;
import my.virkato.kata312.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AuthController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private RoleService roleService;

    public AuthController(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/auth/login";
    }

//    @PostMapping("/login")
//    public String processLogin(Model model) {
//        return "redirect:/user";
//    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("error", "мой логин");
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(UserEntity formUser, Model model) {
        String err = "Пароли не совпадают";
        UserEntity user = new UserEntity();
        user.setUsername(formUser.getUsername());
        user.setNickname(formUser.getUsername().toUpperCase());
        user.setPassword(passwordEncoder.encode(formUser.getPassword()));
        user.setRoles(Collections.singleton(roleService.createRole("ROLE_USER")));

        if (formUser.getPassword().equals(formUser.getConfirm())) {
            if (userService.loadUserByUsername(user.getUsername()) == null) {
                userService.createOrUpdate(user);
                System.out.println(user);
                return "redirect:/login";
            }
            err = "Логин занят";
        }
        model.addAttribute("error", err);
        return "auth/register";
    }
}
