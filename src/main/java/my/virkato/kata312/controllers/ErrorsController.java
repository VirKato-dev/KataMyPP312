package my.virkato.kata312.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorsController implements ErrorController {

    /***
     * При любой ошибке выходим из аккаунта
     * @return выход их аккаунта
     */
    public String showError() {
        return "redirect:/login?logout";
    }
}
