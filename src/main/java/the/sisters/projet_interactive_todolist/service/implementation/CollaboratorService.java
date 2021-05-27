package the.sisters.projet_interactive_todolist.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.model.dto.CollaboratorDTO;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ICollaboratorRepository;
import the.sisters.projet_interactive_todolist.service.ICollaboratorService;

import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService implements ICollaboratorService {
    private final ICollaboratorRepository collaboratorRepository;
    @Autowired
    public CollaboratorService(ICollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    @Override
    public Collaborator create(CollaboratorDTO collaborator) {
        return null;
    }

    @Override
    public Optional<Collaborator> readOne(int id) {
        return collaboratorRepository.findById(id);
    }

    @Override
    public List<Collaborator> readAll() {
        return collaboratorRepository.findAll();
    }

    @Override
    public Collaborator save(Collaborator collaborator) {
        return null;
    }

    @Override
    public void update(Collaborator collaborator) {

    }

    @Override
    public Optional<Collaborator> findByEmail(String email) {
        return collaboratorRepository.findById(collaboratorRepository.findAll().stream().filter(x -> x.getEmail()==email).findFirst().orElse(null).getCollaboratorId());
    }

}
