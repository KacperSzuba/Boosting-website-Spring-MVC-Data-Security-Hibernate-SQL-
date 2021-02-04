package com.BoostingWebsite.account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserQueryRepository extends CrudRepository<User, Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    SimpleUserDto findByUsername(String username);

    Optional<SimpleUserDto> getById(Long id);
}
