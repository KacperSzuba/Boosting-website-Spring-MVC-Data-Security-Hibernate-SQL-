package com.BoostingWebsite.account.password;

class PasswordValidator {
    static boolean isPasswordLengthSufficient(String password) {
        return password.length() >= 7;
    }

    static boolean whetherThePasswordsAreTheSame(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }
}
