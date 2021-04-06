package the.sisters.projet_interactive_todolist.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ICategoryRepository;
import the.sisters.projet_interactive_todolist.service.ICategoryService;

public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
