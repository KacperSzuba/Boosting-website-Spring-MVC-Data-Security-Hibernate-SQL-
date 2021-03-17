package com.BoostingWebsite.account;

import java.util.List;

class UserSnapshot {
    private Long id;
   // @Size(min = 7, max = 20, message = "Username length should be between 7 and 20 letters")
    private String username;
 //   @Size(min = 7, message = "Password length should be between 7 and 20 letters")
    private String password;
 //   @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;
  //  @NotEmpty(message = "E-mail cannot be empty")
 //   @Email(message = "Invalid email")
    private String email;
    private List<UserRoleSnapshot> roles;
    private String creationErrorMessage;

    public UserSnapshot(){}

    UserSnapshot(Long id, String username, String password, boolean enabled, String email, List<UserRoleSnapshot> roles, String creationErrorMessage) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.roles = roles;
        this.creationErrorMessage = creationErrorMessage;
    }

    Long getId() {
        return id;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    boolean isEnabled() {
        return enabled;
    }

    String getEmail() {
        return email;
    }

    List<UserRoleSnapshot> getRoles() {
        return roles;
    }

    String getCreationErrorMessage() {
        return creationErrorMessage;
    }
}
