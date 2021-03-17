package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;

import java.util.List;
import java.util.stream.Collectors;

import static com.BoostingWebsite.utils.PasswordValidator.isPasswordLengthSufficient;
import static com.BoostingWebsite.utils.PasswordValidator.whetherThePasswordsAreTheSame;


class User {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String email;
    private List<UserRole> roles;
    private String creationErrorMessage;

    User(){}

    private User(Long id, String username, String password, boolean enabled, String email, List<UserRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.roles = roles;
    }

    void creationMessage(String creationErrorMessage){
        this.creationErrorMessage = creationErrorMessage;
    }

    void enable(){
        enabled = true;
    }

    void changeEmail(String email){
        this.email = email;
    }

    boolean canResetPassword(String newPassword, String confirmNewPassword) throws DataMismatchException {
        return isPasswordLengthSufficient(newPassword) && whetherThePasswordsAreTheSame(newPassword, confirmNewPassword);
    }

    boolean canChangePassword(String currentPassword, String newPassword, String confirmNewPassword) throws DataMismatchException {
        return isPasswordLengthSufficient(newPassword) &&
                whetherThePasswordsAreTheSame(newPassword, confirmNewPassword) &&
                whetherThePasswordsAreTheSame(password, currentPassword);
    }

    void changePassword(String password){
        this.password = password;
    }

    static User restore(UserSnapshot snapshot){
        return new User(snapshot.getId(), snapshot.getUsername(), snapshot.getPassword(), snapshot.isEnabled(), snapshot.getEmail(), snapshot.getRoles().stream().map(UserRole::restore).collect(Collectors.toList()));
    }

    UserSnapshot getSnapshot(){
        return new UserSnapshot(id, username, password, enabled, email, roles.stream().map(UserRole::getSnapshot).collect(Collectors.toList()), creationErrorMessage);
    }
}


