package com.BoostingWebsite.account;

import com.BoostingWebsite.utils.BaseBusiness;

class LoginHistoryBusiness extends BaseBusiness {

    private final LoginHistoryRepository loginHistoryRepository;

    LoginHistoryBusiness(final LoginHistoryRepository loginHistoryRepository) {
        this.loginHistoryRepository = loginHistoryRepository;
    }

    void save(User user){
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.create(user);
        loginHistoryRepository.save(loginHistory);
    }
}
