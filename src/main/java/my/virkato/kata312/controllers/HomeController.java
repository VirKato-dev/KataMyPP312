package my.virkato.kata312.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    /***
     * Показать приветственную страницу
     */
    @GetMapping
    public String index() {
        return "home";
    }

}
