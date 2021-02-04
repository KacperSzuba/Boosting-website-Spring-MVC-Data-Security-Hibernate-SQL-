package com.BoostingWebsite.auth;

import java.util.Optional;

interface UserTokenRepository {

    Optional<UserToken> findByUser_Username(String username);

    UserToken findByToken(String token);
}
