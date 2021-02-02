package com.BoostingWebsite.account.exception;

public class UserExistException extends RuntimeException {
    public UserExistException(String message){
        super(message);
    }
}
