package the.sisters.projet_interactive_todolist.repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import the.sisters.projet_interactive_todolist.model.Project;

public interface IProjectRepository extends JpaRepository<Project, Integer> {
}
