package com.BoostingWebsite.account;

import java.util.Date;

class UserTokenSnapshot {
    private Long id;
    private String token;
    private SimpleUserDtoSnapshot user;
    private Date expiryDate;

    protected UserTokenSnapshot(){}

    UserTokenSnapshot(Long id, String token, SimpleUserDtoSnapshot user, Date expiryDate) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    Long getId() {
        return id;
    }

    String getToken() {
        return token;
    }

    SimpleUserDtoSnapshot getUser() {
        return user;
    }

    Date getExpiryDate() {
        return expiryDate;
    }
}
