package the.sisters.projet_interactive_todolist.service;

import the.sisters.projet_interactive_todolist.model.Collaborator;
import java.util.Optional;
public interface ICollaboratorService {
    Optional<Collaborator> findByEmail(String email);
}
