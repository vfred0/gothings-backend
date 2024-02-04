package com.gothings.data.daos;


import com.gothings.data.entities.identity.UserAccount;

import java.util.Optional;
import java.util.UUID;

public interface IUserAccountRepository extends IRepository<UserAccount, UUID> {
    Optional<UserAccount> findByUsername(String username);
}
