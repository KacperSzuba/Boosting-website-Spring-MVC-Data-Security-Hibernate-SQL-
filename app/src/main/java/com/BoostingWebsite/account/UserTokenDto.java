package com.BoostingWebsite.account;

import java.util.Date;

public class UserTokenDto {
    public static Builder builder(){
        return new Builder();
    }

    private final Long id;
    private final String token;
    private final SimpleUserDto user;
    private final Date expiryDate;

    private UserTokenDto(Builder builder){
        id = builder.id;
        token = builder.token;
        user = builder.user;
        expiryDate = builder.expiryDate;
    }

    public static class Builder{
        private Long id;
        private String token;
        private SimpleUserDto user;
        private Date expiryDate;

        private Builder(){}

        public UserTokenDto build(){
            return new UserTokenDto(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        public Builder withUser(SimpleUserDto user) {
            this.user = user;
            return this;
        }

        public Builder withExpiryDate(Date expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public SimpleUserDto getUser() {
        return user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}
