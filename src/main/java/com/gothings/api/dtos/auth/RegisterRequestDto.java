package com.gothings.api.dtos.auth;

import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @Size(max = 60)
        String names,
        String username,
        String password
) {
}
