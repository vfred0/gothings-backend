package com.gothings.api.dtos.article;

import com.gothings.data.enums.Category;
import com.gothings.data.enums.Gender;
import com.gothings.data.enums.State;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class ArticleRequestDto {
    @NotNull
    @Size(max = 60)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private Category category;

    @NotNull
    private State state;

    private Gender gender;

    @NotNull
    private Set<String> images;
}
