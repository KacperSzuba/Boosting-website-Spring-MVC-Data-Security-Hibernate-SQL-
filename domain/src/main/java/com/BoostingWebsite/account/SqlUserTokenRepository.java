package com.BoostingWebsite.account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface SqlUserTokenRepository extends UserTokenRepository, CrudRepository<UserToken, Long> {
    Optional<UserToken> findByUser_Username(String username);

    UserToken findByToken(String token);

    void delete(UserToken userToken);

    UserToken save(UserToken userToken);
}
