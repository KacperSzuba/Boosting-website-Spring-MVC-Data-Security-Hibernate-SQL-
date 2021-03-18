package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;

class EmailManagerFacade {
    private final UserBusiness userBusiness;

    EmailManagerFacade(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    void changeEmail(String currentEmail, String email, String confirmEmail) throws DataMismatchException {
        userBusiness.changeEmail(currentEmail, email, confirmEmail);
    }
}
