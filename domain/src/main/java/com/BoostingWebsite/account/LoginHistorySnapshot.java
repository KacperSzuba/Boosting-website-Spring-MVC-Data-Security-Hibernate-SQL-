package com.BoostingWebsite.account;

import java.time.LocalDateTime;

class LoginHistorySnapshot {
    private Long id;
    private UserSnapshot user;
    private LocalDateTime date;

    public LoginHistorySnapshot(){}

    LoginHistorySnapshot(final Long id, final UserSnapshot user, final LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.date = date;
    }

    Long getId() {
        return id;
    }

    UserSnapshot getUser() {
        return user;
    }

    LocalDateTime getDate() {
        return date;
    }
}
