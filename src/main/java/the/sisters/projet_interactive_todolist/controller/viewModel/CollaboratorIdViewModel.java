package the.sisters.projet_interactive_todolist.controller.viewModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CollaboratorIdViewModel {
    private static int id;

    public CollaboratorIdViewModel(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
