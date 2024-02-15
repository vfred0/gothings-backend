package com.gothings.services.article;

import com.gothings.api.dtos.article.ArticleRequestDto;
import com.gothings.api.dtos.article.ArticleResponseDto;
import com.gothings.data.enums.Category;
import com.gothings.data.enums.State;
import com.gothings.services.crud.ICrudService;

import java.util.List;
import java.util.UUID;

public interface IArticleService extends ICrudService<ArticleRequestDto, ArticleResponseDto, UUID> {

    List<ArticleResponseDto> search(String title, Category category, State state);

    UUID save(UUID userId, ArticleRequestDto articleRequestDto);

    List<ArticleResponseDto> findAll();

    List<ArticleResponseDto> search(String title, Category category, State state, UUID userId);
}
