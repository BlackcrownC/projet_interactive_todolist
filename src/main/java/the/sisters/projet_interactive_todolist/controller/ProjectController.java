package the.sisters.projet_interactive_todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import the.sisters.projet_interactive_todolist.controller.viewModel.CollaboratorIdViewModel;
import the.sisters.projet_interactive_todolist.controller.viewModel.TypeViewModel;
import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.service.implementation.TaskService;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    private final TaskService taskService;

    public ProjectController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/project/task_list")//for manager
    public String getAllTaskForManager(Model model){
        List<Task> tasks = taskService.readAll();

        model.addAttribute("allTask", tasks);

        return  "project/managerView";
    }

    @GetMapping("/project/task/{id}")
    public String getTask(Model model, @PathVariable int id, TypeViewModel typeViewModel){
        String path;
        if(typeViewModel.isManager()){
            path = "fragment/headerManager";
        }
        else {
            path = "fragment/headerEmployee";
        }
        Optional<Task> task= taskService.readOne(id);

        model.addAttribute("task",task.get());
        model.addAttribute("path", path);

        return "task/taskDetailView";

    }
    @GetMapping("/project/calendar")//for employee
    public String getAllTaskForEmployee(Model model){


        return "project/collaboratorView";

    }
    @GetMapping("/project/addTask")
    public String addTask(Model model){
        return "task/addTaskView";
    }
}
