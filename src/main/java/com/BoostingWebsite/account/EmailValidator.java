package com.BoostingWebsite.account;

class EmailValidator {
    static boolean whetherTheEmailsAreTheSame(String email, String confirmEmail) {
        return email.equals(confirmEmail);
    }
}
