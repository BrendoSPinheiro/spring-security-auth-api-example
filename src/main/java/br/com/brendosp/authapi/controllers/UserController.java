package br.com.brendosp.authapi.controllers;

import br.com.brendosp.authapi.dtos.CreateUserRequestDTO;
import br.com.brendosp.authapi.dtos.CreateUserResponseDTO;
import br.com.brendosp.authapi.entities.User;
import br.com.brendosp.authapi.useCases.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserUseCase userUseCase;

    @GetMapping("/me")
    @Operation(summary = "Get user")
    public ResponseEntity<?> getUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create user")
    public ResponseEntity<CreateUserResponseDTO> create(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        User user = userUseCase.create(createUserRequestDTO);

        var response = new CreateUserResponseDTO(
            UUID.randomUUID(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );

        return ResponseEntity.ok(response);
    }

}
