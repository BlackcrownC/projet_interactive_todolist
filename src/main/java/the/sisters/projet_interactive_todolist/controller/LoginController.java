package the.sisters.projet_interactive_todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.model.dto.CollaboratorDTO;
import the.sisters.projet_interactive_todolist.service.ICollaboratorService;
import the.sisters.projet_interactive_todolist.service.implementation.CollaboratorService;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final ICollaboratorService collaboratorService;

    @Autowired
    public LoginController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @GetMapping("")
    public String login(Model model) {
        return "login/login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("newCollaborator", new CollaboratorDTO());
        return "login/signUp";
    }

    @PostMapping("/newaccount")
    public String newAccount(CollaboratorDTO collaborator) {
        collaboratorService.create(collaborator);
        return "redirect:/login";
    }
}
