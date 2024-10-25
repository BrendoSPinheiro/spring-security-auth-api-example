package br.com.brendosp.authapi.repositories;

import br.com.brendosp.authapi.entities.User;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByEmail(String email);

    User save(User user);
}
