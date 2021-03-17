package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.UserDto;

public class ApplicationContext {
    private final UserDto user;

    public ApplicationContext(UserDto user) {
        this.user = user;
    }

    public UserDto getUser() {
        return user;
    }
}
