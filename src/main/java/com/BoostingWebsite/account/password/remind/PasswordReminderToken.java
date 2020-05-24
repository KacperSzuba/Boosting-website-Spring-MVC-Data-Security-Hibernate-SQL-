package com.BoostingWebsite.account.password.remind;

import com.BoostingWebsite.account.token.TokenValidator;
import com.BoostingWebsite.account.token.UserTokenRepository;
import com.BoostingWebsite.account.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
class PasswordReminderToken {
    private final TokenValidator tokenValidator;
    private final UserTokenRepository userTokenRepository;

    public PasswordReminderToken(TokenValidator tokenValidator, UserTokenRepository userTokenRepository) {
        this.tokenValidator = tokenValidator;
        this.userTokenRepository = userTokenRepository;
    }

    String validateResetPasswordToken(Long id, String token){
        String tempToken = tokenValidator.validateToken(id, token);
        if (tempToken != null){
            return tempToken;
        }
        else {
            User user = userTokenRepository.findByToken(token).getUser();
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(
                    new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return null;
        }
    }

}
