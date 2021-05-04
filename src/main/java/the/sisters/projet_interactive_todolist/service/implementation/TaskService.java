package the.sisters.projet_interactive_todolist.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.model.Category;
import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.model.dto.TaskDto;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ITaskRepository;
import the.sisters.projet_interactive_todolist.service.ITaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    private final ITaskRepository taskRepository;
    private final CategoryService categoryService;
    @Autowired
    public TaskService(ITaskRepository taskRepository, CategoryService categoryService) {
        this.taskRepository = taskRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Optional<Task> readOne(int id) {
        return taskRepository.findById(id);
    }
    @Override
    public List<Task> readAll() {
        return taskRepository.findAll();
    }
    @Override
    public Task save(TaskDto taskDto, int projectId){
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setEnd(taskDto.getEnd());
        task.setStart(taskDto.getStart());
        task.setCompleted(false);
        task.setProjectId(projectId);
        List<Category> categories = new ArrayList<>();
        if(taskDto.isProgrammer()){ categories.add(categoryService.readAll().stream().filter(x -> x.getCategoryId()==1).findFirst().orElse(null)); }
        if(taskDto.isArtist()){ categories.add(categoryService.readAll().stream().filter(x -> x.getCategoryId()==2).findFirst().orElse(null)); }
        if(taskDto.isDesigner()){ categories.add(categoryService.readAll().stream().filter(x -> x.getCategoryId()==3).findFirst().orElse(null)); }
        task.setCategories(categories);
        return taskRepository.save(task);
    }
}
