package my.virkato.kata312.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @PostMapping
    public String onlyGetMapping() {
        return "redirect:/";
    }

}
