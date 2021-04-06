package the.sisters.projet_interactive_todolist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "taskId")
    private Integer taskId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start")
    private java.sql.Date start;

    @Column(name = "end")
    private java.sql.Date end;

    @Column(name = "completed")
    private Byte completed;

    @Column(name = "projectId")
    private Integer projectId;

    @ManyToMany
    @JoinTable(
            name = "taskcategory",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Category> categories;
    @ManyToMany
    @JoinTable(
            name = "course_like",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Collaborator> collaborators;

    public Integer getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getStart() {
        return this.start;
    }

    public void setStart(java.sql.Date start) {
        this.start = start;
    }

    public java.sql.Date getEnd() {
        return this.end;
    }

    public void setEnd(java.sql.Date end) {
        this.end = end;
    }

    public Byte getCompleted() {
        return this.completed;
    }

    public void setCompleted(Byte completed) {
        this.completed = completed;
    }

    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
