package the.sisters.projet_interactive_todolist.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.model.Category;
import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.model.dto.CollaboratorDTO;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ICollaboratorRepository;
import the.sisters.projet_interactive_todolist.service.ICollaboratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService implements ICollaboratorService {
    private final ICollaboratorRepository collaboratorRepository;
    private final TaskService taskService;
    private final CategoryService categoryService;
    @Autowired
    public CollaboratorService(ICollaboratorRepository collaboratorRepository, TaskService taskService, CategoryService categoryService) {
        this.collaboratorRepository = collaboratorRepository;
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @Override
    public Collaborator create(CollaboratorDTO collaborator) {
        Collaborator newCollab = new Collaborator();

        newCollab.setFirstname(collaborator.getFirstname());
        newCollab.setLastname(collaborator.getLastname());
        newCollab.setEmail(collaborator.getEmail());
        newCollab.setPasswd(BCrypt.hashpw(collaborator.getPasswd(), BCrypt.gensalt()));

        List<Category> categories = new ArrayList<>();

        if (collaborator.getCategories()[0])
            categories.add(categoryService.readOne(0).get());
        if (collaborator.getCategories()[1])
            categories.add(categoryService.readOne(1).get());
        if (collaborator.getCategories()[2])
            categories.add(categoryService.readOne(2).get());

        if (collaborator.getCategories()[3]) {
            newCollab.setRoles("MANAGER");
            categories.add(categoryService.readOne(3).get());
        }
        else
            newCollab.setRoles("USER");

        newCollab.setCategories(categories);

        return collaboratorRepository.save(newCollab);
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
        return collaboratorRepository.save(collaborator);
    }

    @Override
    public void update(Collaborator collaborator) {
        save(collaborator);
    }
    @Override
    public void addTaskToCollab(int taskId, int collabId){
        Collaborator collaborator=  readOne(collabId).get();
        List<Task> tasks = collaborator.getTasks();
        tasks.add(taskService.readOne(taskId).get());
        collaborator.setTasks(tasks);
        save(collaborator);
    }

    @Override
    public Collaborator findByEmail(String email) {
        collaboratorRepository.findAll().stream().forEach( x -> System.out.println(x.getEmail()));
        return collaboratorRepository.findOneByEmail(email).get();
        //return collaboratorRepository.findAll().stream().filter(x -> x.getEmail()==email).findFirst().get();
    }

}
