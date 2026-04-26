package mk.ukim.finki.emclab.service.domain;
import mk.ukim.finki.emclab.model.domain.User;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

// lab3

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User register(User user);

    User login(String username, String password);
}

