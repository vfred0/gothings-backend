package com.gothings.api.resources;


import com.gothings.api.dtos.article.ArticleRequestDto;
import com.gothings.api.dtos.article.ArticleResponseDto;
import com.gothings.data.enums.Category;
import com.gothings.data.enums.State;
import com.gothings.services.article.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleResource {

    private final IArticleService articleService;

    @GetMapping("{articleId}")
    ResponseEntity<ArticleResponseDto> findById(@PathVariable UUID articleId) {
        return articleService.findById(articleId).map(articleDto -> new ResponseEntity<>(articleDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    ResponseEntity<List<ArticleResponseDto>> findAll() {
        return ResponseEntity.ok(articleService.findAll());
    }


    @PostMapping("{userId}")
    ResponseEntity<HttpHeaders> save(@PathVariable UUID userId, @RequestBody ArticleRequestDto articleRequestDto) {
        UUID articleId = articleService.save(userId, articleRequestDto);
        return new ResponseEntity<>(
                HttpHeader.getHttpHeaders(articleId),
                HttpStatus.CREATED
        );
    }

    @PutMapping("{articleId}")
    ResponseEntity<HttpHeaders> update(@PathVariable UUID articleId, @RequestBody ArticleRequestDto articleDto) {
        UUID id = articleService.update(articleId, articleDto);
        return new ResponseEntity<>(HttpHeader.getHttpHeaders(id), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpHeaders> deleteById(@PathVariable UUID id) {
        articleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("search")
    ResponseEntity<List<ArticleResponseDto>> search(@RequestParam(required = false) String title, @RequestParam(required = false) Category category, @RequestParam(required = false) State state) {
        return ResponseEntity.ok(articleService.search(title, category, state));
    }


    @GetMapping("{userId}/search")
    ResponseEntity<List<ArticleResponseDto>> search(@RequestParam(required = false) String title, @RequestParam(required = false) Category category, @RequestParam(required = false) State state, @PathVariable UUID userId) {
        return ResponseEntity.ok(articleService.search(title, category, state, userId));
    }
}
