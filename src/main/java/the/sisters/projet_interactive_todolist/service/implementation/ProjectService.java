package the.sisters.projet_interactive_todolist.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import the.sisters.projet_interactive_todolist.repository.Interfaces.IProjectRepository;
import the.sisters.projet_interactive_todolist.service.IProjectService;

public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;
    @Autowired
    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
