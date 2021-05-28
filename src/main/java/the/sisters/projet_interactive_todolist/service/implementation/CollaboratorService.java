package the.sisters.projet_interactive_todolist.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.model.Task;
import the.sisters.projet_interactive_todolist.model.dto.CollaboratorDTO;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ICollaboratorRepository;
import the.sisters.projet_interactive_todolist.service.ICollaboratorService;

import java.util.List;
import java.util.Optional;

@Service
public class CollaboratorService implements ICollaboratorService {
    private final ICollaboratorRepository collaboratorRepository;
    private final TaskService taskService;
    @Autowired
    public CollaboratorService(ICollaboratorRepository collaboratorRepository, TaskService taskService) {
        this.collaboratorRepository = collaboratorRepository;
        this.taskService = taskService;
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
