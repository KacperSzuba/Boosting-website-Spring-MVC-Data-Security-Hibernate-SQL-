package com.BoostingWebsite.account;

public class SimpleUserDtoSnapshot {
    private Long id;
    private String username;
    private String email;

    protected SimpleUserDtoSnapshot(){}

    public SimpleUserDtoSnapshot(final Long id, final String username, final String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
