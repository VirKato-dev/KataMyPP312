package my.virkato.kata312.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class AuthController {

    @GetMapping
    public String loginForm() {
        return "/auth/login";
    }

    @PostMapping
    public String checking(Model model) {
        List<String> list = new ArrayList<>();
        list.add((String) model.getAttribute("username"));
        list.add((String) model.getAttribute("password"));

        model.addAttribute("messages", list);
        return "redirect:/error";
    }
}
