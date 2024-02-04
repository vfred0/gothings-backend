package com.gothings.services.user;


import com.gothings.data.daos.IUserRepository;
import com.gothings.services.crud.CrudService;
import com.gothings.services.exceptions.MessageException;
import com.gothings.services.exceptions.NotFoundException;
import com.gothings.api.dtos.UserDto;
import com.gothings.api.dtos.article.ArticleResponseDto;
import com.gothings.api.dtos.auth.RegisterRequestDto;
import com.gothings.data.entities.User;
import com.gothings.data.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService extends CrudService<User, RegisterRequestDto, UserDto, UUID> implements IUserService {

    private final IUserRepository userRepository;
    private final Mapper mapper;

    public UserService(IUserRepository userRepository, Mapper mapper) {
        super(mapper, userRepository, User.class, UserDto.class);
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ArticleResponseDto> getArticlesByUserId(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(this::getMessageException);
        return this.mapper.toDtos(Arrays.asList(user.getArticles().toArray()), ArticleResponseDto.class);
    }

    @Override
    protected UUID getId(User model) {
        return model.getId();
    }

    @Override
    protected RuntimeException getMessageException() {
        return new NotFoundException(MessageException.USER_NOT_FOUND);
    }
}