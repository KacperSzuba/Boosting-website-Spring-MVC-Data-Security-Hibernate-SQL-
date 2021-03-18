package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;

class PasswordReminderFacade {
    private final UserBusiness userBusiness;
    private final UserTokenBusiness userTokenBusiness;

    PasswordReminderFacade(UserBusiness userBusiness, UserTokenBusiness userTokenBusiness) {
        this.userBusiness = userBusiness;
        this.userTokenBusiness = userTokenBusiness;
    }

    void remindPassword(String email) throws DataMismatchException {
        userBusiness.remindPassword(email);
    }

    String confirm(Long id, String token){
        return userTokenBusiness.confirm(id, token);
    }
}
