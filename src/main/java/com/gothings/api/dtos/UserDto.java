package com.gothings.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    @Size(max = 250) String about;
    @Size(max = 10) String numberWhatsapp;
}