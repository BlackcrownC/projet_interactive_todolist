package the.sisters.projet_interactive_todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "collaborator")
public class Collaborator {
    @Id
    @Column(name = "collaboratorId")
    private Integer collaboratorId;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "passwd")
    private String passwd;

    @ManyToMany
    @JoinTable(
            name = "collaboratorCategory",
            joinColumns = @JoinColumn(name = "collaboratorId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<Category> categories;
    @ManyToMany
    @JoinTable(
            name = "collaboratorTask",
            joinColumns = @JoinColumn(name = "collaboratorId"),
            inverseJoinColumns = @JoinColumn(name = "taskId"))
    private List<Task> tasks;

    @ManyToMany
    @JoinTable(
            name = "projectcollaborator",
            joinColumns = @JoinColumn(name = "collaboratorId"),
            inverseJoinColumns = @JoinColumn(name = "projectId"))
    private List<Project> projects;


}
