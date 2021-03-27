package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.UserNotFoundException;
import com.BoostingWebsite.utils.BaseBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

class UserTokenBusiness extends BaseBusiness {
    private static final Logger logger  = LoggerFactory.getLogger(UserTokenBusiness.class);

    private final UserTokenRepository userTokenRepository;
    private final SimpleUserDtoFactory simpleUserDtoFactory;
    private final UserTokenFactory userTokenFactory;

    UserTokenBusiness(final UserTokenRepository userTokenRepository, final SimpleUserDtoFactory simpleUserDtoFactory, UserTokenFactory userTokenFactory) {
        this.userTokenRepository = userTokenRepository;
        this.simpleUserDtoFactory = simpleUserDtoFactory;
        this.userTokenFactory = userTokenFactory;
    }

    void saveOrUpdateToken(String token, SimpleUserDto user) {
        if (checkIfUserHasTokenGenerated(user)) {
            saveToken(token, user);
        } else {
            updateToken(token, user);
        }
    }

    private void updateToken(String token, SimpleUserDto user) {
        try {
            UserToken userToken = userTokenFactory.from(findByUser(user));
            userTokenRepository.delete(userToken);
            saveToken(token, user);
        } catch (Exception exception) {
            logger.error("Error during updating token! Id: " );
        }
    }

    private void saveToken(String token, SimpleUserDto user) {
        try {
            UserTokenSnapshot snapshot = new UserTokenSnapshot(0L, token, user.getSnapshot(), new Date());
            userTokenRepository.save(UserToken.restore(snapshot));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean checkIfUserHasTokenGenerated(SimpleUserDto user) {
        return this.userTokenRepository.findByUser_Username(user.getSnapshot().getUsername()).isEmpty();
    }

     UserTokenDto findByUser(SimpleUserDto user){
        return userTokenFactory.toDto(
                userTokenRepository.findByUser_Username(user.getSnapshot().getUsername())
                        .orElseThrow(() -> new UserNotFoundException("Token not found!")));
    }

    UserTokenDto findByToken(String token){
        return userTokenFactory.toDto(
                userTokenRepository.findByToken(token)
                        .orElseThrow(() -> new UserNotFoundException("Token not found!")));
    }

    Optional<String> validateToken(long id, String token) {
        UserTokenDto passToken = findByToken(token);
        if ((passToken == null) || (passToken.getUser().getSnapshot().getId() != id)) {
            return Optional.of("invalid");
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return Optional.of("expired");
        }
        return Optional.empty();
    }

    String confirm(Long id, String token) {
        String tempToken = validateToken(id, token).get();
        if (tempToken != null) {
            return tempToken;
        } else {
            User user = simpleUserDtoFactory.from(findByToken(token).getUser());
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(new SimpleGrantedAuthority("CONFIRMATION_EMAIL_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return null;
        }
    }
}