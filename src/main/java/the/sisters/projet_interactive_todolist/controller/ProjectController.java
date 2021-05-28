package the.sisters.projet_interactive_todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.model.dto.TaskDto;
import the.sisters.projet_interactive_todolist.service.implementation.CategoryService;
import the.sisters.projet_interactive_todolist.service.implementation.CollaboratorService;
import the.sisters.projet_interactive_todolist.service.implementation.ProjectService;
import the.sisters.projet_interactive_todolist.service.implementation.TaskService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Controller
public class ProjectController {

    private final TaskService taskService;
    private final CategoryService categoryService;
    private final ProjectService projectService;
    private final CollaboratorService collaboratorService;

    public ProjectController(TaskService taskService, CategoryService categoryService, ProjectService projectService, CollaboratorService collaboratorService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
        this.projectService = projectService;
        this.collaboratorService = collaboratorService;
    }

    @GetMapping("/project/{project_id}/task_list")//for manager
    public String getAllTaskForManager(Model model){
        List<Task> tasksWithNoCollab = taskService.readAll();
        List<Collaborator> collaborators = collaboratorService.readAll();
        for(Collaborator c : collaborators){
            for(Task task : c.getTasks()){
                if(tasksWithNoCollab.contains(task)){
                    tasksWithNoCollab.remove(task);
                }
            }
        }

        model.addAttribute("collaborators",collaborators);
        model.addAttribute("tasksWithNoCollab", tasksWithNoCollab);
        model.addAttribute("currentDate", new Date());

        return  "project/managerView";
    }

    @GetMapping("/project/{project_id}/employee")//for employee
    public String getAllTaskForEmployee(Model model/*, @AuthenticationPrincipal String email*/){
        Collaborator collaborator = collaboratorService.findByEmail("michel_racicot12@hotmail.ca");
        List<Task> tasks = collaborator.getTasks();

        model.addAttribute("tasks", tasks);

        model.addAttribute("currentDate", new Date());

        return "project/collaboratorView";

    }
    @GetMapping("/project")
    public String getProjects(Model model/*, @AuthenticationPrincipal String email*/){
        Collaborator collaborator = collaboratorService.findByEmail("michel_racicot12@hotmail.ca");
        model.addAttribute("allProject", collaborator.getProjects());
        return "/project/projectView";
    }
}
