package com.BoostingWebsite.account;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface SqlUserRepository extends CrudRepository<UserSnapshot, Long> {
    @Query(value = "SELECT user FROM UserSnapshot user WHERE user.username = :usernameOrEmail or user.email = :usernameOrEmail")
    Optional<UserSnapshot> findByUsernameOrEmail(String usernameOrEmail);

    Optional<UserSnapshot> findByEmail(String email);

    boolean existsUserByUsername(String username);

    @Query(value = "SELECT u.roles FROM UserSnapshot u WHERE u.id = :id")
    UserRole getUserRole(@Param("id") Long id);

    Optional<UserSnapshot> findById(Long id);

    UserSnapshot save(UserSnapshot user);
}
