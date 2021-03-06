package com.BoostingWebsite.account;

import java.util.Optional;

public interface UserQueryRepository {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    SimpleUserDto findByUsername(String username);

    Optional<SimpleUserDto> getById(Long id);
}
