package com.gothings.services.useraccount;

import com.gothings.data.daos.IUserAccountRepository;
import com.gothings.data.daos.IUserRoleRepository;
import com.gothings.data.entities.identity.UserAccount;
import com.gothings.data.entities.identity.UserRole;
import com.gothings.data.enums.Role;
import com.gothings.services.exceptions.MessageException;
import com.gothings.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService implements IUserAccountService {

    private final IUserAccountRepository userAccountRepository;
    private final IUserRoleRepository userRoleRepository;


    private UserAccount getUser(String username) {
        return userAccountRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(MessageException.USER_NOT_FOUND));
    }

    @Override
    public void addRolesByUsername(String username, Role[] roles) {
        UserAccount user = getUser(username);
        for (Role role : roles) {
            UserRole userRole = getUserRole(role);
            user.addRole(userRole);
        }
        userAccountRepository.save(user);
    }

    @Override
    public void removeRoles(Role[] roles) {
        for (Role role : roles) {
            UserRole userRole = getUserRole(role);
            userRoleRepository.delete(userRole);
        }
    }

    @Override
    public void removeRolesByUsername(String username, Role[] roles) {
        UserAccount user = getUser(username);
        user.removeRoles(roles);
        userAccountRepository.save(user);
    }


    private UserRole getUserRole(Role role) {
        return userRoleRepository.findByName(role).orElseThrow(() -> new NotFoundException(MessageException.ROLE_NOT_FOUND));
    }
}
