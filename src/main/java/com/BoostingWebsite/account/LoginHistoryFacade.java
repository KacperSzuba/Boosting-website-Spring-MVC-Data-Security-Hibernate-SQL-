package com.BoostingWebsite.account;

import org.springframework.stereotype.Service;

@Service
public class LoginHistoryFacade {

    private final LoginHistoryRepository loginHistoryRepository;

    public LoginHistoryFacade(LoginHistoryRepository loginHistoryRepository) {
        this.loginHistoryRepository = loginHistoryRepository;
    }

    public void save(User user){
        loginHistoryRepository.save(new LoginHistory(user));
    }

}
