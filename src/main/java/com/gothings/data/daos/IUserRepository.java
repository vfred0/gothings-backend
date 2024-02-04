package com.gothings.data.daos;


import com.gothings.data.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends IRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}