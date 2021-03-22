package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseFacade;

class RegisterFacade extends BaseFacade {
    private final AccountCommandHandler accountCommandHandler;

    RegisterFacade(AccountCommandHandler accountCommandHandler) {
        this.accountCommandHandler = accountCommandHandler;
    }

    void createAccount(User user, String confirmPassword) throws DataMismatchException {
        accountCommandHandler.createAccount(user, confirmPassword);
    }

    void enable(Long id){
        accountCommandHandler.enable(id);
    }

    String confirm(Long id, String token){
        return accountCommandHandler.confirm(id, token);
    }
}
