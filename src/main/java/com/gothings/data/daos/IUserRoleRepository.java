package com.gothings.data.daos;


import com.gothings.data.entities.identity.UserRole;
import com.gothings.data.enums.Role;

import java.util.Optional;
import java.util.UUID;

public interface IUserRoleRepository extends IRepository<UserRole, UUID> {

    Optional<UserRole> findByName(Role name);

}
