package com.BoostingWebsite.account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface SqlUserQueryRepository extends CrudRepository<UserSnapshot, Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    Optional<UserSnapshot> findByUsername(String username);

    Optional<UserSnapshot> getById(Long id);
}
