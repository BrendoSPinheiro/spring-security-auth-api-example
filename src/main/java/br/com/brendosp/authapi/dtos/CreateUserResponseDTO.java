package br.com.brendosp.authapi.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateUserResponseDTO(
    @Schema(description = "User's id")
    UUID id,
    @Schema(description = "User's name")
    String name,
    @Schema(description = "User's email")
    String email,
    @Schema(description = "User's creation date")
    LocalDateTime createdAt,
    @Schema(description = "User's update date")
    LocalDateTime updatedAt
) {
}
