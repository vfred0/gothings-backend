package com.gothings.services.useraccount;

import com.gothings.data.enums.Role;

public interface IUserAccountService {

    void addRolesByUsername(String username, Role[] roles);

    void removeRoles(Role[] roles);

    void removeRolesByUsername(String username, Role[] roles);
}
