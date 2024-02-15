package com.gothings.data.daos;


import com.gothings.data.entities.identity.UserAccount;
import com.gothings.data.entities.identity.UserRole;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IUserAccountRepository extends IRepository<UserAccount, UUID> {
    Optional<UserAccount> findByUsername(String username);

    @Query("SELECT ua.roles FROM UserAccount ua, UserRole ur WHERE ua.username = :username")
    Set<UserRole> findRolesByUsername(String username);
}
