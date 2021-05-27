package the.sisters.projet_interactive_todolist.service;

import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.model.dto.TaskDto;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    Optional<Task> readOne(int id);
    List<Task> readAll();
    Task save(TaskDto taskDto);
}
