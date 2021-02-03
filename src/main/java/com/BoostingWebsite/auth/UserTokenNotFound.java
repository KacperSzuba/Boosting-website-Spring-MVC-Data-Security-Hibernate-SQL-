package com.BoostingWebsite.auth;

public class UserTokenNotFound extends RuntimeException {
    public UserTokenNotFound(String message){
        super(message);
    }
}
