package com.BoostingWebsite.auth;

import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class TokenValidator {

    private final UserTokenRepository userTokenRepository;

    public TokenValidator(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public String validateToken(long id, String token) {
        UserToken passToken = userTokenRepository.findByToken(token);
        if ((passToken == null) || (passToken.getUser().getId() != id)) {
            return "invalid";
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }
        return null;
    }
}
