package the.sisters.projet_interactive_todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import the.sisters.projet_interactive_todolist.model.Category;
import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.model.dto.TaskDto;
import the.sisters.projet_interactive_todolist.service.implementation.CategoryService;
import the.sisters.projet_interactive_todolist.service.implementation.CollaboratorService;
import the.sisters.projet_interactive_todolist.service.implementation.ProjectService;
import the.sisters.projet_interactive_todolist.service.implementation.TaskService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final CategoryService categoryService;
    private final ProjectService projectService;
    private final CollaboratorService collaboratorService;

    public TaskController(TaskService taskService, CategoryService categoryService, ProjectService projectService, CollaboratorService collaboratorService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
        this.projectService = projectService;
        this.collaboratorService = collaboratorService;
    }


    @PostMapping("/project/{project_id}/saveTask")
    public String addUser(@Valid TaskDto taskDto, @PathVariable int project_id){
        taskDto.setProjectId(project_id);
        System.out.println(taskDto.toString());
        Task saved = taskService.save(taskDto);
        System.out.println("ok");
        return "redirect:/project/"+project_id+"/task_list";
    }
    @GetMapping("/project/{project_id}/addTask")
    public String showAddTaskForm(Model model, @PathVariable int project_id){
        model.addAttribute("project_id",project_id);
        model.addAttribute("newTask", new TaskDto());
        return "task/addTaskView";
    }
    @GetMapping("/project/{project_id}/task/{id}")
    public String getTask(Model model, @PathVariable int id,@PathVariable int project_id){
        String path=null;
        if(true){
            path = "fragment/headerManager::top-nav";
        }
        else {
            path = "fragment/headerEmployee::top-nav";
        }
        Optional<Task> task= taskService.readOne(id);
        model.addAttribute("project_id",project_id);
        model.addAttribute("task",task.get());
        model.addAttribute("path", path);

        return "task/taskDetailView";

    }

    @GetMapping("/project/{project_id}/assignTask/{id}")
    public String assignTask(Model model, @PathVariable int id,@PathVariable int project_id){
        List<Collaborator> allCollaborators = collaboratorService.readAll();
        List<Collaborator> collaborators = new ArrayList<Collaborator>();
        Optional<Task> task= taskService.readOne(id);
        for(Collaborator c : allCollaborators){
            if(!c.getTasks().contains(task)){
                for(Category co:task.get().getCategories()){
                    if(c.getCategories().contains(co)){
                        collaborators.add(c);
                        break;
                    }

                }
            }

        }
        model.addAttribute("collaborators",collaborators);

        model.addAttribute("project_id",project_id);
        model.addAttribute("task",task.get());

        return "task/assignTaskView";
    }
    @PostMapping("/project/{project_id}/assignTask/{id}/{collaboratorId}")
    public String postAssignTask(Model model, @PathVariable int id,@PathVariable int project_id,@PathVariable int collarboratorId){
        List<Collaborator> allCollaborators = collaboratorService.readAll();
        List<Collaborator> collaborators = new ArrayList<Collaborator>();
        Optional<Task> task= taskService.readOne(id);
        for(Collaborator c : allCollaborators){
            if(!c.getTasks().contains(task)){
                for(Category co:task.get().getCategories()){
                    if(c.getCategories().contains(co)){
                        collaborators.add(c);
                        break;
                    }

                }
            }

        }
        model.addAttribute("collaborators",collaborators);

        model.addAttribute("project_id",project_id);
        model.addAttribute("task",task.get());

        return "redirect:task/assignTaskView";
    }

}
