package com.BoostingWebsite.account.exception;

public class UserTokenNotFound extends RuntimeException {
    public UserTokenNotFound(String message){
        super(message);
    }
}
