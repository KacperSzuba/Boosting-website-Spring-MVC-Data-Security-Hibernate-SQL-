package com.BoostingWebsite.account.mail;

class EmailValidator {
    static boolean whetherTheEmailsAreTheSame(String email, String confirmEmail) {
        return email.equals(confirmEmail);
    }
}
