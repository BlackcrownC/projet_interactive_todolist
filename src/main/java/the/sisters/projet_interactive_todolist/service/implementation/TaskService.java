package the.sisters.projet_interactive_todolist.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ITaskRepository;
import the.sisters.projet_interactive_todolist.service.ITaskService;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    private final ITaskRepository taskRepository;
    @Autowired
    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> readOne(int id) {
        return taskRepository.findById(id);
    }
    @Override
    public List<Task> readAll() {
        return taskRepository.findAll();
    }
}
