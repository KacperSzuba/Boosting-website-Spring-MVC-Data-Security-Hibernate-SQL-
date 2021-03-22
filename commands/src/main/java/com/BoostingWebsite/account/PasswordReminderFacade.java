package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseFacade;

class PasswordReminderFacade extends BaseFacade {
    private final AccountCommandHandler accountCommandHandler;

    PasswordReminderFacade(AccountCommandHandler accountCommandHandler) {
        this.accountCommandHandler = accountCommandHandler;
    }

    void remindPassword(String email) throws DataMismatchException {
        accountCommandHandler.remindPassword(email);
    }

    String confirm(Long id, String token){
        return accountCommandHandler.confirm(id, token);
    }
}
