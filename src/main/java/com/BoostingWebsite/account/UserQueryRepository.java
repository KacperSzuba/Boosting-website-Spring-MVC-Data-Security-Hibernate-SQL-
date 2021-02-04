package com.BoostingWebsite.account;

import org.springframework.data.repository.CrudRepository;

public interface UserQueryRepository extends CrudRepository<User, Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);
}
