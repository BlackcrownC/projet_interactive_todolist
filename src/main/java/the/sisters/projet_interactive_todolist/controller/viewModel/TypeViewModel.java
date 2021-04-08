package the.sisters.projet_interactive_todolist.controller.viewModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class TypeViewModel {
    private static boolean isManager;//employee(0) or manager(1)

    public TypeViewModel(boolean isManager) {
        this.isManager = isManager;
    }

    public boolean isManager(){
        return this.isManager;
    }

}
