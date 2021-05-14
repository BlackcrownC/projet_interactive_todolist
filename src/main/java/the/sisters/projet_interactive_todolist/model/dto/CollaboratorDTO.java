package the.sisters.projet_interactive_todolist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CollaboratorDTO {
    private Integer collaboratorId;

    private String firstname;

    private String lastname;

    private String email;

    private String passwd;

    private boolean[] categories;

    public CollaboratorDTO() {
        categories = new boolean[4];
    }
}
