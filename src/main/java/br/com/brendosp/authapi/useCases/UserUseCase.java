package br.com.brendosp.authapi.useCases;

import br.com.brendosp.authapi.dtos.CreateUserRequestDTO;
import br.com.brendosp.authapi.entities.User;
import br.com.brendosp.authapi.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    public final IUserRepository userRepository;

    public final PasswordEncoder passwordEncoder;

    public User create(CreateUserRequestDTO createUserRequestDTO) {
        userRepository.findByEmail(createUserRequestDTO.email()).ifPresent(user -> {
            throw new RuntimeException("Email already in use");
        });

        var user = new User(
            createUserRequestDTO.name(),
            createUserRequestDTO.email(),
            passwordEncoder.encode(createUserRequestDTO.password())
        );

        return userRepository.save(user);
    }
}
