package the.sisters.projet_interactive_todolist.service;

import the.sisters.projet_interactive_todolist.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService  {
    Category create(Category category);

    Optional<Category> readOne(int id);

    List<Category> readAll();

    Category save(Category Category);

    void update(Category Category);
}
