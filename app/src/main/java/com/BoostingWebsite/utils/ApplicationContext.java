package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.UserDto;
import com.BoostingWebsite.account.exception.UserExistException;

public final class ApplicationContext {
    private static final ApplicationContext INSTANCE = new ApplicationContext();
    private UserDto user;

    private ApplicationContext(){}

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user){
        if(this.user == null){
            this.user = user;
        }

        throw new UserExistException("User is already set");
    }

    public ApplicationContext getInstance(){
        return INSTANCE;
    }
}
