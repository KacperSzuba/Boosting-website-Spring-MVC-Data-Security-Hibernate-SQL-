package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.exception.DataMismatchException;

public class PasswordValidator {
    public static boolean isPasswordLengthSufficient(String password) throws DataMismatchException {
        if(password.length() >= 7 && password.length() <= 20){
            return true;
        }
        throw new DataMismatchException("Password should be 3 to 20 characters long!");
    }

    public static boolean whetherThePasswordsAreTheSame(String p1, String p2) throws DataMismatchException {
        if(p1.equals(p2)){
            return true;
        }

        throw new DataMismatchException("Passwords are different!");
    }
}
