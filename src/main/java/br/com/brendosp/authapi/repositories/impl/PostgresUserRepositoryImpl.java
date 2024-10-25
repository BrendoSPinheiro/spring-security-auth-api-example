package br.com.brendosp.authapi.repositories.impl;

import br.com.brendosp.authapi.entities.User;
import br.com.brendosp.authapi.repositories.IUserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@RequiredArgsConstructor
public class PostgresUserRepositoryImpl implements IUserRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcClient.sql("SELECT * FROM auth_api.users WHERE email = :email")
            .param("email", email)
            .query(User.class)
            .optional();
    }

    @Override
    public User save(User user) {
        return jdbcClient.sql("INSERT INTO auth_api.users (id, name, email, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?) RETURNING *")
            .params(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt())
            .query(User.class)
            .single();
    }
}
