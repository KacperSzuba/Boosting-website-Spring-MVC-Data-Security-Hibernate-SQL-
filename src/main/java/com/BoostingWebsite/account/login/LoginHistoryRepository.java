package com.BoostingWebsite.account.login;

import org.springframework.data.repository.CrudRepository;

public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {
}
