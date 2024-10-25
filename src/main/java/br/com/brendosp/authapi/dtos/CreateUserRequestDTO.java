package br.com.brendosp.authapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(
    @NotBlank(message = "Name is required")
    @Schema(description = "User's name")
    String name,
    @NotBlank
    @Email(message = "Invalid email")
    @Schema(description = "User's email")
    String email,
    @NotBlank
    @Schema(description = "User's password")
    String password
) {
}
