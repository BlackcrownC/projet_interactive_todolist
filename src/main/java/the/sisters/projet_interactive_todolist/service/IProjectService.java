package the.sisters.projet_interactive_todolist.service;

import the.sisters.projet_interactive_todolist.model.Project;

public interface IProjectService {
    Project readOne(int projectId);
}
