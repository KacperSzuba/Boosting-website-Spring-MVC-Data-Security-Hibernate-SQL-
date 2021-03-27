package com.BoostingWebsite.account;

import java.util.Optional;

interface UserRepository {
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);

    Optional<User> findByEmail(String email);

    boolean existsUserByUsername(String username);

    Optional<UserRole> getUserRole(Long id);

    Optional<User> findById(Long id);

    User save(User user);
}