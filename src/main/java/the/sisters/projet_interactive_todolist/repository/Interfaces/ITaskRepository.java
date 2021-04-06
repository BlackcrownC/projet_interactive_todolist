package the.sisters.projet_interactive_todolist.repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import the.sisters.projet_interactive_todolist.model.Task;

public interface ITaskRepository extends JpaRepository<Task, Integer> {
}
