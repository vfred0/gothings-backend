package com.gothings.api.dtos.article;

import com.gothings.api.dtos.UserDto;
import com.gothings.data.enums.Category;
import com.gothings.data.enums.Gender;
import com.gothings.data.enums.State;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleResponseDto {
    UUID id;
    String title;
    String description;
    LocalDateTime date;
    Category category;
    State state;
    Gender gender;
    UserDto user;
    Set<String> images;
    Integer likes;
    Integer dislikes;
}
