package com.gothings.services.auth;


import com.gothings.data.daos.IUserAccountRepository;
import com.gothings.data.daos.IUserRepository;
import com.gothings.data.daos.IUserRoleRepository;
import com.gothings.data.entities.User;
import com.gothings.data.entities.identity.UserAccount;
import com.gothings.services.exceptions.MessageException;
import com.gothings.services.exceptions.NotFoundException;
import com.gothings.api.dtos.auth.AccessTokenDto;
import com.gothings.api.dtos.auth.LoginRequestDto;
import com.gothings.api.dtos.auth.RegisterRequestDto;
import com.gothings.data.entities.identity.UserRole;
import com.gothings.data.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenService jwtAccessTokenService;
    private final IUserAccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserRoleRepository userRoleRepository;
    private final IUserRepository userRepository;

    public AccessTokenDto authenticate(LoginRequestDto loginRequestDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.username(),
                loginRequestDto.password()
        );
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        String token = this.jwtAccessTokenService.generateToken(authentication);
        return new AccessTokenDto(token);
    }

    public UUID register(RegisterRequestDto registerRequestDto) {
        this.checkIfUsernameExists(registerRequestDto.username());
        saveUserAccount(registerRequestDto);
        return saveUser(registerRequestDto);
    }

    private UUID saveUser(RegisterRequestDto registerRequestDto) {
        return this.userRepository.save(
                User.builder()
                        .names(registerRequestDto.names())
                        .username(registerRequestDto.username())
                        .build()
        ).getId();
    }

    private void saveUserAccount(RegisterRequestDto registerRequestDto) {
        Set<UserRole> roleAuthenticated = Set.of(getRoleAuthenticated());
        this.accountRepository.save(
                UserAccount.builder()
                        .username(registerRequestDto.username())
                        .password(registerRequestDto.password())
                        .password(passwordEncoder.encode(registerRequestDto.password()))
                        .roles(roleAuthenticated)
                        .build()
        );
    }

    private UserRole getRoleAuthenticated() {
        return this.userRoleRepository.findByName(Role.ROLE_AUTHENTICATED)
                .orElseThrow(() -> new NotFoundException(MessageException.ROLE_NOT_FOUND));
    }

    private void checkIfUsernameExists(String username) {
        boolean isUsernameExists = accountRepository.findByUsername(username).isPresent();
        if (isUsernameExists) {
            throw new NotFoundException(MessageException.USERNAME_ALREADY_EXISTS);
        }
    }
}
