package my.virkato.kata312.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/error")
public class ErrorsController {

    @GetMapping
    public String showError(HttpServletResponse response, Model model) {
        model.addAttribute("response", response);
        model.addAttribute("messages", response.getHeaderNames());
        return "error";
    }
}
