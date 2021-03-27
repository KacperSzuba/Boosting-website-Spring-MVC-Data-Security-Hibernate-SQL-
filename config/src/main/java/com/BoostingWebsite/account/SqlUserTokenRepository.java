package com.BoostingWebsite.account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface SqlUserTokenRepository extends CrudRepository<UserTokenSnapshot, Long> {
    Optional<UserTokenSnapshot> findByUser_Username(String username);

    Optional<UserTokenSnapshot> findByToken(String token);

    void delete(UserTokenSnapshot userToken);

    UserTokenSnapshot save(UserTokenSnapshot userToken);
}
