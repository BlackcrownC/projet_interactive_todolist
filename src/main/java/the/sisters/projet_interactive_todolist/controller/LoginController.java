package the.sisters.projet_interactive_todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import the.sisters.projet_interactive_todolist.service.implementation.CollaboratorService;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final CollaboratorService collaboratorService;

    public LoginController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @GetMapping
    public String login(Model model) {
        return "login/login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        return "login/signup";
    }
}
