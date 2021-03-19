package com.BoostingWebsite.account;

import org.springframework.stereotype.Repository;

@Repository
class LoginHistoryRepositoryImpl implements LoginHistoryRepository {
    private final SqlLoginHistoryRepository repository;

    LoginHistoryRepositoryImpl(SqlLoginHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoginHistory save(LoginHistory loginHistory) {
        return LoginHistory.restore(repository.save(loginHistory.getSnapshot()));
    }
}
