package com.BoostingWebsite.account.token;

import com.BoostingWebsite.account.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTokenRepository extends CrudRepository<UserToken,Long> {

    Optional<UserToken> findByUser(User user);

    UserToken findByToken(String token);
}
