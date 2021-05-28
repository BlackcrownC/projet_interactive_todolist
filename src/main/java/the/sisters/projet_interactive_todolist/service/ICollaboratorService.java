package the.sisters.projet_interactive_todolist.service;

import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.model.dto.CollaboratorDTO;

import java.util.List;
import java.util.Optional;

public interface ICollaboratorService {
    Collaborator create(CollaboratorDTO collaborator);

    Optional<Collaborator> readOne(int id);

    List<Collaborator> readAll();

    Collaborator save(Collaborator collaborator);

    void update(Collaborator collaborator);

    void addTaskToCollab(int taskId, int collabId);

    Collaborator findByEmail(String email);
}
