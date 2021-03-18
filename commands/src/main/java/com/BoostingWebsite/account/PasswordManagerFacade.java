package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;

class PasswordManagerFacade {
    private final UserBusiness userBusiness;

    PasswordManagerFacade(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    void changePassword(String currentPassword, String newPassword, String confirmNewPassword) throws DataMismatchException {
        userBusiness.changePassword(currentPassword, newPassword, confirmNewPassword);
    }
}
