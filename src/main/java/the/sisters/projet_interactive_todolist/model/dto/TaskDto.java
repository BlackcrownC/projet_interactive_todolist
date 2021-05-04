package the.sisters.projet_interactive_todolist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int taskId;
    private String name;
    private String description;
    private Date start;
    private Date end;
    private Integer projectId;
    private boolean programmer;
    private boolean artist;
    private boolean designer;

}
