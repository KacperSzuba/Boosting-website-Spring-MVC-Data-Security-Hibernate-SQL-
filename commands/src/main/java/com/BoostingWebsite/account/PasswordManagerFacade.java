package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseFacade;

class PasswordManagerFacade extends BaseFacade {
    private final AccountCommandHandler accountCommandHandler;

    PasswordManagerFacade(AccountCommandHandler accountCommandHandler) {
        this.accountCommandHandler = accountCommandHandler;
    }
    void changePassword(String currentPassword, String newPassword, String confirmNewPassword) throws DataMismatchException {
        accountCommandHandler.changePassword(currentPassword, newPassword, confirmNewPassword);
    }
}
