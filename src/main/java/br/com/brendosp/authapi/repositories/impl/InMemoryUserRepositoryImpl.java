package br.com.brendosp.authapi.repositories.impl;

import br.com.brendosp.authapi.entities.User;
import br.com.brendosp.authapi.repositories.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepositoryImpl implements IUserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }
}
