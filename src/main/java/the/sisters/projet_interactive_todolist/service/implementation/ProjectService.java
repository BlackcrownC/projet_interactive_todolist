package the.sisters.projet_interactive_todolist.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.model.Project;
import the.sisters.projet_interactive_todolist.repository.Interfaces.IProjectRepository;
import the.sisters.projet_interactive_todolist.service.IProjectService;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;
    @Autowired
    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> readOne(int projectId) {
        return projectRepository.findById(projectId);
    }
}
