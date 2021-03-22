package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.utils.BaseFacade;

class PasswordResetFacade extends BaseFacade {
    private final AccountCommandHandler accountCommandHandler;

    PasswordResetFacade(AccountCommandHandler accountCommandHandler) {
        this.accountCommandHandler = accountCommandHandler;
    }
    void resetPassword(String newPassword, String confirmNewPassword) throws DataMismatchException {
        accountCommandHandler.resetPassword(newPassword, confirmNewPassword);
    }
}
