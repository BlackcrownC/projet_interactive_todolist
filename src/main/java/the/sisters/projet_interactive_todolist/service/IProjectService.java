package the.sisters.projet_interactive_todolist.service;

import the.sisters.projet_interactive_todolist.model.Project;
import java.util.Optional;

public interface IProjectService {
    Optional<Project> readOne(int projectId);
}
