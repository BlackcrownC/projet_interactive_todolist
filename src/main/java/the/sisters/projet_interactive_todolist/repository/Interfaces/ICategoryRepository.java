package the.sisters.projet_interactive_todolist.repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
