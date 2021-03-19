package com.BoostingWebsite.account;

import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
class UserTokenRepositoryImpl implements UserTokenRepository {
    private final SqlUserTokenRepository repository;

    UserTokenRepositoryImpl(SqlUserTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserToken> findByUser_Username(String username) {
        return repository.findByUser_Username(username).map(UserToken::restore);
    }

    @Override
    public UserToken findByToken(String token) {
        return UserToken.restore(repository.findByToken(token));
    }

    @Override
    public void delete(UserToken userToken) {
        repository.delete(userToken.getSnapshot());
    }

    @Override
    public UserToken save(UserToken userToken) {
        return UserToken.restore(userToken.getSnapshot());
    }
}


