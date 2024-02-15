package com.gothings.api.resources;

import com.gothings.data.enums.Role;
import com.gothings.services.useraccount.IUserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-accounts")
@Slf4j
public class UserAccountResource {

    private final IUserAccountService userAccountService;

    @DeleteMapping("{username}/roles")
    ResponseEntity<HttpHeaders> removeRoles(@PathVariable String username, @RequestBody Role[] roles) {
        userAccountService.removeRolesByUsername(username, roles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{username}/roles")
    ResponseEntity<HttpHeaders> addRoles(@PathVariable String username, @RequestBody Role[] roles) {
        userAccountService.addRolesByUsername(username, roles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<HttpHeaders> removeRoles(@RequestBody Role[] roles) {
        userAccountService.removeRoles(roles);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
