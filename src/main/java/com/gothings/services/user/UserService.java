package com.gothings.services.user;


import com.gothings.data.daos.IUserAccountRepository;
import com.gothings.data.daos.IUserRepository;
import com.gothings.data.entities.identity.UserRole;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService extends CrudService<User, RegisterRequestDto, UserDto, UUID> implements IUserService {

    private final IUserRepository userRepository;
    private final IUserAccountRepository userAccountRepository;
    private final Mapper mapper;

    public UserService(IUserRepository userRepository, Mapper mapper, IUserAccountRepository userAccountRepository) {
        super(mapper, userRepository, User.class, UserDto.class);
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<ArticleResponseDto> getArticlesByUserId(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(this::getMessageException);
        return this.mapper.toDtos(Arrays.asList(user.getArticles().toArray()), ArticleResponseDto.class);
    }

    @Override
    public UUID update(UUID userId, UserDto userDto) {
        User user = this.userRepository.findById(userId).orElseThrow(this::getMessageException);
        user.setNames(userDto.getNames());
        user.setPhoto(userDto.getPhoto());
        user.setNumberWhatsapp(userDto.getNumberWhatsapp());
        user.setAbout(userDto.getAbout());
        User userUpdated = this.userRepository.save(user);
        return userUpdated.getId();
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = this.mapper.toDtos(users, UserDto.class);
        for (User user : users) {
            Set<UserRole> roles = this.userAccountRepository.findRolesByUsername(user.getUsername());
            userDtos.stream()
                    .filter(userDto -> userDto.getId().equals(user.getId()))
                    .forEach(userDto ->

                            {
                                userDto.setRoles(roles
                                        .stream()
                                        .map(UserRole::getName)
                                        .collect(Collectors.toSet()));
                                userDto.setCreatedAt(user.getCreatedAt());
                            }
                    );
        }

        return userDtos;
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