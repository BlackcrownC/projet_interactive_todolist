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
import java.util.*;

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
        taskDto.setProjectId(0);
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
        boolean isManager;
        if(collaboratorService.findByEmail("michel_racicot12@hotmail.ca").getCategories().contains(categoryService.readOne(0).get())){
            isManager=true;
        }
        else {
            isManager=false;
        }
        Optional<Task> task= taskService.readOne(id);
        model.addAttribute("project_id",project_id);
        model.addAttribute("task",task.get());
        model.addAttribute("isManager", isManager);

        return "task/taskDetailView";

    }

    @GetMapping("/project/{project_id}/assignTask/{id}")
    public String assignTask(Model model, @PathVariable int id,@PathVariable int project_id){
        List<Collaborator> allCollaborators = collaboratorService.readAll();
        List<Collaborator> collaborators = new ArrayList<Collaborator>();
        Task task= taskService.readOne(id).get();
        for(Collaborator c : allCollaborators){
            if(!c.getTasks().contains(task)){
                for(Category co:task.getCategories()){
                    if(c.getCategories().contains(co)){
                        collaborators.add(c);
                        break;
                    }

                }
            }

        }
        model.addAttribute("collaborators",collaborators);

        model.addAttribute("project_id",project_id);
        model.addAttribute("task",task);

        return "task/assignTaskView";
    }
    @PostMapping("/project/{project_id}/assignTask/{id}/{collaboratorId}")
    public String postAssignTask(@PathVariable int id,@PathVariable int project_id,@PathVariable int collaboratorId){
        collaboratorService.addTaskToCollab(id,collaboratorId);


        return "redirect:/project/0/assignTask/"+id;
    }
    @PostMapping("/project/{project_id}/task/addTime/{id}")
    public String postAddWeek(@PathVariable int id,@PathVariable int project_id){
        Task task = taskService.readOne(id).get();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(task.getEnd());
        calendar.add(calendar.DAY_OF_YEAR, 7);
        Date date = calendar.getTime();
        task.setEnd(date);
        taskService.save(task);
        return "redirect:/project/0/task_list/";
    }
    @PostMapping("/project/0/task/completed/{id}")
    public String postCompletedTask(@PathVariable int id){
        Task task = taskService.readOne(id).get();
        task.setCompleted(true);
        taskService.save(task);
        if(collaboratorService.findByEmail("michel_racicot12@hotmail.ca").getCategories().contains(categoryService.readOne(0).get())){
            return "redirect:/project/0/task_list/";
        }
        else return "redirect:/project/0/employee/";

    }
}
