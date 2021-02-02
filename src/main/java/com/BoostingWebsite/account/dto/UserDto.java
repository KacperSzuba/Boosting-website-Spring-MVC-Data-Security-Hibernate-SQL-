package com.BoostingWebsite.account.dto;

import com.BoostingWebsite.account.userRole.UserRole;

import java.util.List;

public class UserDto {
    public static Builder builder(){
        return new Builder();
    }

    private final Long id;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final String email;
    private final List<UserRole> roles;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    private UserDto(final Builder builder) {
        id = builder.id;
        username = builder.username;
        password = builder.password;
        enabled = builder.enabled;
        email = builder.email;
        roles = builder.roles;
    }

    public static class Builder{
        private Long id;
        private String username;
        private String password;
        private boolean enabled;
        private String email;
        private List<UserRole> roles;

        private Builder(){}

        public UserDto build(){
            return new UserDto(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withRoles(List<UserRole> roles) {
            this.roles = roles;
            return this;
        }
    }
}
