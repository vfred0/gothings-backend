package com.gothings.api.dtos.auth;


public record LoginRequestDto(
        String username,
        String password
) {
}