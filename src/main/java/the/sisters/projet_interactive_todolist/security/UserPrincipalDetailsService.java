package the.sisters.projet_interactive_todolist.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import the.sisters.projet_interactive_todolist.model.Collaborator;
import the.sisters.projet_interactive_todolist.repository.Interfaces.ICollaboratorRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final ICollaboratorRepository userRepository;

    public UserPrincipalDetailsService(ICollaboratorRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collaborator user = this.userRepository.findOneByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("User login not found"));
        return new UserPrincipal(user);
    }
}
