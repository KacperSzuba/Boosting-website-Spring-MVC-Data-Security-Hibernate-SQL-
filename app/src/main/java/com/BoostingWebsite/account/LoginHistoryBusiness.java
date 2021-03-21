package com.BoostingWebsite.account;

import com.BoostingWebsite.utils.BaseBusiness;

public class LoginHistoryBusiness extends BaseBusiness {

    private final LoginHistoryRepository loginHistoryRepository;

    public LoginHistoryBusiness(final LoginHistoryRepository loginHistoryRepository) {
        this.loginHistoryRepository = loginHistoryRepository;
    }

    public void save(User user){
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.create(user);
        loginHistoryRepository.save(loginHistory);
    }
}
