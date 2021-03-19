package com.BoostingWebsite.account;

import org.springframework.data.repository.CrudRepository;

interface SqlLoginHistoryRepository extends CrudRepository<LoginHistorySnapshot, Long> {
    LoginHistorySnapshot save(LoginHistorySnapshot loginHistory);
}
