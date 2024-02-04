package com.gothings.api.resources;

import com.gothings.api.dtos.UserDto;
import com.gothings.api.dtos.article.ArticleResponseDto;
import com.gothings.api.dtos.auth.RegisterRequestDto;
import com.gothings.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class UserResource {

    private final IUserService userService;

    @PostMapping
    ResponseEntity<HttpHeaders> save(@RequestBody RegisterRequestDto registerRequestDto) {
        UUID userId = userService.save(registerRequestDto);
        return new ResponseEntity<>(HttpHeader.getHttpHeaders(userId), HttpStatus.CREATED);
    }

    @GetMapping("{id}/articles")
    ResponseEntity<List<ArticleResponseDto>> getArticlesByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getArticlesByUserId(id));
    }

    @PutMapping("{userId}")
    ResponseEntity<HttpHeaders> update(@PathVariable UUID userId, @RequestBody RegisterRequestDto registerRequestDto) {
        UUID articleId = userService.update(userId, registerRequestDto);
        return new ResponseEntity<>(HttpHeader.getHttpHeaders(articleId), HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    ResponseEntity<Void> delete(@PathVariable UUID userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    ResponseEntity<UserDto> findById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
