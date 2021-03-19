package com.BoostingWebsite.account;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class UserQueryRepositoryImpl implements UserQueryRepository {
    private final SqlUserQueryRepository repository;

    UserQueryRepositoryImpl(SqlUserQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return repository.existsUserByEmail(email);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return repository.existsUserByUsername(username);
    }

    @Override
    public SimpleUserDto findByUsername(String username) {
        return null;
    }

    @Override
    public Optional<SimpleUserDto> getById(Long id) {
        return Optional.empty();
    }
}
