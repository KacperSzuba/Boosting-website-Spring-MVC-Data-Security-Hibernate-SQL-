package com.BoostingWebsite.auth;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    Optional<UserToken> findByUser_Username(String username);

    UserToken findByToken(String token);
}
