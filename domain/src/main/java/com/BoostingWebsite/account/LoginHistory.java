package com.BoostingWebsite.account;

import java.time.LocalDateTime;


class LoginHistory {
    private Long id;
    private User user;
    private LocalDateTime date;

    LoginHistory(){}

    private LoginHistory(Long id, User user, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.date = date;
    }

    void create(User user){
        this.user = user;
        date = LocalDateTime.now();
    }

    static LoginHistory restore(LoginHistorySnapshot snapshot){
        return new LoginHistory(snapshot.getId(), User.restore(snapshot.getUser()), snapshot.getDate());
    }

    LoginHistorySnapshot getSnapshot(){
        return new LoginHistorySnapshot(id, user.getSnapshot(), date);
    }
}
