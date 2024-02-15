package com.gothings.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gothings.data.enums.Role;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    UUID id;
    @Size(max = 50) String photo;
    @Size(max = 50) String names;
    @Size(max = 50) String username;
    @Size(max = 250) String about;
    @Size(max = 10) String numberWhatsapp;
    Instant createdAt;
    Set<Role> roles;
}