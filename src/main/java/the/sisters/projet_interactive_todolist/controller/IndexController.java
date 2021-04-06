package the.sisters.projet_interactive_todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {
    public IndexController(){}

    @GetMapping("/")
    public String index(Model model){


        return "index/index";
    }


}

