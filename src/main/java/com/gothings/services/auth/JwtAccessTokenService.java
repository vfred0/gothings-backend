package com.gothings.services.auth;

import com.gothings.services.exceptions.MessageException;
import com.gothings.services.exceptions.NotFoundException;
import com.gothings.data.daos.IUserRepository;
import com.gothings.data.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtAccessTokenService {

    private final JwtEncoder jwtEncoder;
    private final IUserRepository userRepository;

    private UserDetails getUserDetails(Authentication authentication) {
        return Optional
                .of(authentication.getPrincipal())
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .orElseThrow(() -> new NotFoundException(MessageException.USER_NOT_FOUND));
    }

    private JwtClaimsSet getClaims(UserDetails userDetails, User user) {
        int AMOUNT_TO_ADD = 1;
        return JwtClaimsSet
                .builder()
                .subject(userDetails.getUsername())
                .claim("id", user.getId())
                .claim("names", user.getNames())
                .claim("scope", getRoles(userDetails))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(AMOUNT_TO_ADD, ChronoUnit.DAYS))
                .build();
    }

    private List<String> getRoles(UserDetails userDetails) {
        return userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = getUserDetails(authentication);
        User user = this.userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException(MessageException.USER_NOT_FOUND));
        JwtClaimsSet claimsSet = getClaims(userDetails, user);
        return this.jwtEncoder
                .encode(JwtEncoderParameters.from(claimsSet))
                .getTokenValue();
    }
}
