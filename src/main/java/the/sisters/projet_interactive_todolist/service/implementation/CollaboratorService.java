package the.sisters.projet_interactive_todolist.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ICollaboratorRepository;
import the.sisters.projet_interactive_todolist.service.ICollaboratorService;

@Service
public class CollaboratorService implements ICollaboratorService {
    private final ICollaboratorRepository collaboratorRepository;
    @Autowired
    public CollaboratorService(ICollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }
}
