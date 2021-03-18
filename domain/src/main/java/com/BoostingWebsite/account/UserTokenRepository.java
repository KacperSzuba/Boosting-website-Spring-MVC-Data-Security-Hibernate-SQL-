package com.BoostingWebsite.account;

import java.util.Optional;

interface UserTokenRepository {

    Optional<UserToken> findByUser_Username(String username);

    UserToken findByToken(String token);

    void delete(UserToken userToken);

    UserToken save(UserToken userToken);
}