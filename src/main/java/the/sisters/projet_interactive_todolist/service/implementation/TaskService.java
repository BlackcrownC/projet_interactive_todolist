package the.sisters.projet_interactive_todolist.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ITaskRepository;
import the.sisters.projet_interactive_todolist.service.ITaskService;

public class TaskService implements ITaskService {
    private final ITaskRepository taskRepository;
    @Autowired
    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
