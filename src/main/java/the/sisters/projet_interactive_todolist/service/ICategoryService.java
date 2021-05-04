package the.sisters.projet_interactive_todolist.service;

import the.sisters.projet_interactive_todolist.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService  {
    List<Category> readAll();
    Optional<Category> readOne(int id);
}
