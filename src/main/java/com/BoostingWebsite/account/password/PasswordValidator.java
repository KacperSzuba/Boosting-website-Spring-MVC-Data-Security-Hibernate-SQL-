package com.BoostingWebsite.account.password;

public class PasswordValidator {
    public static boolean isPasswordLengthSufficient(String password){
        return password.length() >= 7;
    }

    public static boolean whetherThePasswordsAreTheSame(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }
}
