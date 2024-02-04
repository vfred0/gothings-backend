package com.gothings.api.resources;

import com.gothings.api.dtos.auth.AccessTokenDto;
import com.gothings.api.dtos.auth.LoginRequestDto;
import com.gothings.api.dtos.auth.RegisterRequestDto;
import com.gothings.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AccessTokenDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authService.authenticate(loginRequestDto), HttpStatus.OK);
    }


    @PostMapping("register")
    public ResponseEntity<HttpHeader> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return new ResponseEntity<>(HttpHeader.getHttpHeaders(authService.register(registerRequestDto)), HttpStatus.CREATED);
    }
}