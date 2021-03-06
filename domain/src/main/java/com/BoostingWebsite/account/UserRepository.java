package com.BoostingWebsite.account;

import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface UserRepository {
    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsUserByUsername(String username);

    void changeUserEnabledStatement(@Param("id") Long id, @Param("enabled") boolean enabled);

    void changePassword(@Param("id") Long id, @Param("password") String password);

    void changeEmail(@Param("id") Long id, @Param("email") String email);

    UserRole getUserRole(@Param("id") Long id);

    Optional<User> findById(Long id);

    User save(User user);
}