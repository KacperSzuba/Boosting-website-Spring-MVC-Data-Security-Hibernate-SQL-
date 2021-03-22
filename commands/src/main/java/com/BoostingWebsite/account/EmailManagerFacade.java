package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseFacade;

class EmailManagerFacade extends BaseFacade {
    private final AccountCommandHandler accountCommandHandler;

    EmailManagerFacade(AccountCommandHandler accountCommandHandler) {
        this.accountCommandHandler = accountCommandHandler;
    }

    void changeEmail(String currentEmail, String email, String confirmEmail) throws DataMismatchException {
        accountCommandHandler.changeEmail(currentEmail, email, confirmEmail);
    }
}
