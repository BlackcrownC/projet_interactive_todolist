package the.sisters.projet_interactive_todolist.repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import the.sisters.projet_interactive_todolist.model.Collaborator;

import java.util.Optional;

public interface ICollaboratorRepository extends JpaRepository<Collaborator, Integer> {
    Optional<Collaborator> findOneByEmail(String email);
}
