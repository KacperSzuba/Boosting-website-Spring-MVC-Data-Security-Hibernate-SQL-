package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;

class PasswordResetFacade {
    private final UserBusiness userBusiness;

    PasswordResetFacade(final UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    void resetPassword(String newPassword, String confirmNewPassword) throws DataMismatchException {
        userBusiness.resetPassword(newPassword, confirmNewPassword);
    }
}
