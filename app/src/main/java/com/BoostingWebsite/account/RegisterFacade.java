package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;

class RegisterFacade {
    private final UserBusiness userBusiness;
    private final UserTokenBusiness userTokenBusiness;

    RegisterFacade(UserBusiness userBusiness, UserTokenBusiness userTokenBusiness) {
        this.userBusiness = userBusiness;
        this.userTokenBusiness = userTokenBusiness;
    }

    void createAccount(User user, String confirmPassword) throws DataMismatchException {
        if(userBusiness.canCreateAccount(user, confirmPassword)){
            userBusiness.createAccount(user);
        }
    }

    void enable(Long id){
        userBusiness.enable(id);
    }

    String confirm(Long id, String token){
        return userTokenBusiness.confirm(id, token);
    }
}
