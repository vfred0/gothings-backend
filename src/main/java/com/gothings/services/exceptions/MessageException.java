package com.gothings.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageException {

    USER_NOT_FOUND("Usuario no encontrado"),
    ARTICLE_NOT_FOUND("Artículo no encontrado"),
    USERNAME_ALREADY_EXISTS("El nombre de usuario ya existe"),
    ROLE_NOT_FOUND("Rol no encontrado"),
    NOT_FOUND_ENTITY("Entidad no encontrada");

    private final String message;
}