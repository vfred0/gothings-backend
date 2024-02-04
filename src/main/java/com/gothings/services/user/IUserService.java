package com.gothings.services.user;


import com.gothings.services.crud.ICrudService;
import com.gothings.api.dtos.UserDto;
import com.gothings.api.dtos.article.ArticleResponseDto;
import com.gothings.api.dtos.auth.RegisterRequestDto;

import java.util.List;
import java.util.UUID;

public interface IUserService extends ICrudService<RegisterRequestDto, UserDto, UUID> {

    List<ArticleResponseDto> getArticlesByUserId(UUID id);
}