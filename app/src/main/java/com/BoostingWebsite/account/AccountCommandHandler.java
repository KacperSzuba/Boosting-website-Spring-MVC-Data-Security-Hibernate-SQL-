package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;

public class AccountCommandHandler {
    private final UserBusiness userBusiness;
    private final UserTokenBusiness userTokenBusiness;

    public AccountCommandHandler(UserBusiness userBusiness, UserTokenBusiness userTokenBusiness) {
        this.userBusiness = userBusiness;
        this.userTokenBusiness = userTokenBusiness;
    }

    public void changeEmail(String currentEmail, String email, String confirmEmail) throws DataMismatchException {
        userBusiness.changeEmail(currentEmail, email, confirmEmail);
    }

    public void changePassword(String currentPassword, String password, String repeatPassword) throws DataMismatchException {
        userBusiness.changePassword(currentPassword, password, repeatPassword);
    }

    public void remindPassword(String email) throws DataMismatchException {
        userBusiness.remindPassword(email);
    }

    public String confirm(Long id, String token) {
        return userTokenBusiness.confirm(id, token);
    }

    public void resetPassword(String password, String repeatPassword) throws DataMismatchException {
        userBusiness.resetPassword(password, repeatPassword);
    }

    public void createAccount(User user, String confirmPassword) throws DataMismatchException {
        if(userBusiness.canCreateAccount(user, confirmPassword)){
            userBusiness.createAccount(user);
        }
    }

    public void enable(Long id) {
        userBusiness.enable(id);
    }

    public SimpleUserDto findSimpleUserDtoById(Long id){
        return userBusiness.findSimpleUserDtoById(id);
    }
}
