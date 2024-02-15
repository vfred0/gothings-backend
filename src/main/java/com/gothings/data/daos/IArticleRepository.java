package com.gothings.data.daos;


import com.gothings.data.enums.Category;
import com.gothings.data.enums.State;
import com.gothings.data.entities.Article;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IArticleRepository extends IRepository<Article, UUID> {
    @Query("SELECT a FROM Article a WHERE a.title LIKE %?1% AND a.category = ?2 AND a.state = ?3")
    List<Article> findByTitleAndCategoryAndState(String title, Category category, State state);

    @Query("SELECT a FROM Article a WHERE a.title LIKE %?1% AND a.category = ?2 AND a.state = ?3 AND a.user.id = ?4")
    List<Article> findByTitleAndCategoryAndStateAndUserId(String title, Category category, State state, UUID userId);
}