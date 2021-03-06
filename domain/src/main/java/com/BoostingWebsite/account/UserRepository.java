package com.BoostingWebsite.account;

import java.util.Optional;

interface UserRepository {
    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsUserByUsername(String username);

    void changeUserEnabledStatement(Long id, boolean enabled);

    void changePassword(Long id, String password);

    void changeEmail(Long id, String email);

    UserRole getUserRole(Long id);

    Optional<User> findById(Long id);

    User save(User user);
}