package com.BoostingWebsite.utils;

public class PasswordValidator {
    public static boolean isPasswordLengthSufficient(String password) {
        return password.length() >= 7 && password.length() <= 20;
    }

    public static boolean whetherThePasswordsAreTheSame(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }
}
