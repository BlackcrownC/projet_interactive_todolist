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
        List<Task> tasks = taskService.readAll();

        model.addAttribute("allTask", tasks);

        return  "project/managerView";
    }
    @GetMapping("/project/{project_id}/task/{id}")
    public String getTask(Model model, @PathVariable int id,@PathVariable int project_id){
        String path;
        if(){
            path = "fragment/headerManager";
        }
        else {
            path = "fragment/headerEmployee";
        }
        Optional<Task> task= taskService.readOne(id);
        model.addAttribute("project_id",project_id);
        model.addAttribute("task",task.get());
        model.addAttribute("path", path);

        return "task/taskDetailView";

    }
    @GetMapping("/project/{project_id}/calendar")//for employee
    public String getAllTaskForEmployee(Model model){


        return "project/collaboratorView";

    }
    @GetMapping("/project/{project_id}/addTask")
    public String showAddTaskForm(Model model,@PathVariable int project_id){
        model.addAttribute("project_id",project_id);
        model.addAttribute("task", new TaskDto());
        model.addAttribute("categories", categoryService.readAll());
        return "task/addTaskView";
    }
    @PostMapping("/project/{project_id}/saveTask")
    public String addUser(@Valid TaskDto taskDto, @PathVariable int project_id){
        Task saved = taskService.save(taskDto, project_id);
        return "redirect:/project/task_list)";
    }
    @GetMapping("/project")
    public String getProjects(Model model/*, @AuthenticationPrincipal String email*/){
        Optional<Collaborator> collaborator = collaboratorService.findByEmail("michel@hotmail.ca");
        model.addAttribute("allProject", collaborator.get().getProjects());
        return "/project/projectView";
    }
}
