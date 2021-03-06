package com.BoostingWebsite.account;

import org.springframework.data.repository.CrudRepository;

interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {
}
