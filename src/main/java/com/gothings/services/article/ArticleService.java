package com.gothings.services.article;


import com.gothings.api.dtos.article.ArticleRequestDto;
import com.gothings.api.dtos.article.ArticleResponseDto;
import com.gothings.data.daos.IArticleRepository;
import com.gothings.data.daos.IUserRepository;
import com.gothings.data.entities.Article;
import com.gothings.data.entities.User;
import com.gothings.data.enums.Category;
import com.gothings.data.enums.State;
import com.gothings.data.utils.Mapper;
import com.gothings.services.crud.CrudService;
import com.gothings.services.exceptions.MessageException;
import com.gothings.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService extends CrudService<Article, ArticleRequestDto, ArticleResponseDto, UUID> implements IArticleService {

    private final IArticleRepository articleRepository;
    private final IUserRepository userRepository;
    private final Mapper mapper;

    public ArticleService(IArticleRepository articleRepository, IUserRepository userRepository, Mapper mapper) {
        super(mapper, articleRepository, Article.class, ArticleResponseDto.class);
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ArticleResponseDto> search(String title, Category category, State state) {
        return this.mapper.toDtos(this.articleRepository.findByTitleAndCategoryAndState(title, category, state), ArticleResponseDto.class);
    }

    @Override
    public UUID update(UUID articleId, ArticleRequestDto articleRequestDto) {
        Article article = this.articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException(MessageException.ARTICLE_NOT_FOUND));
        article.setTitle(articleRequestDto.getTitle());
        article.setDescription(articleRequestDto.getDescription());
        article.setCategory(articleRequestDto.getCategory());
        article.setState(articleRequestDto.getState());
        article.setGender(articleRequestDto.getGender());
        article.setImages(articleRequestDto.getImages());
        Article articleUpdated = this.articleRepository.save(article);
        return articleUpdated.getId();
    }

    @Override
    public UUID save(UUID userId, ArticleRequestDto articleRequestDto) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(MessageException.USER_NOT_FOUND));
        Article article = this.mapper.toEntity(articleRequestDto, Article.class);
        article.setUser(user);
        return this.articleRepository.save(article).getId();
    }

    @Override
    public List<ArticleResponseDto> findAll() {
        return this.mapper.toDtos(this.articleRepository.findAll(), ArticleResponseDto.class);
    }

    @Override
    public List<ArticleResponseDto> search(String title, Category category, State state, UUID userId) {
        return this.mapper.toDtos(this.articleRepository.findByTitleAndCategoryAndStateAndUserId(title, category, state, userId), ArticleResponseDto.class);
    }

    @Override
    protected UUID getId(Article model) {
        return model.getId();
    }

    @Override
    protected RuntimeException getMessageException() {
        return new NotFoundException(MessageException.ARTICLE_NOT_FOUND);
    }

}