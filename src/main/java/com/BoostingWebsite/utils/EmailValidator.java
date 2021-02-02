package com.BoostingWebsite.utils;

public class EmailValidator {
    public static boolean whetherEmailIsValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
