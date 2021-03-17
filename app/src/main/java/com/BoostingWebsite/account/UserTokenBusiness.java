package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.UserNotFoundException;
import com.BoostingWebsite.utils.BaseFacade;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

public class UserTokenBusiness extends BaseFacade {

    private final UserTokenRepository userTokenRepository;
    private final SimpleUserDtoFactory simpleUserDtoFactory;

    public UserTokenBusiness(final UserTokenRepository userTokenRepository, final SimpleUserDtoFactory simpleUserDtoFactory) {
        this.userTokenRepository = userTokenRepository;
        this.simpleUserDtoFactory = simpleUserDtoFactory;
    }

    public void saveOrUpdateToken(String token, SimpleUserDto user) {
        if (checkIfUserHasTokenGenerated(user)) {
            saveToken(token, user);
        } else {
            updateToken(token, user);
        }
    }

    private void updateToken(String token, SimpleUserDto user) {
        try {
            userTokenRepository.delete(userTokenRepository.findByUser_Username(user.getSnapshot().getUsername()).get());
            saveToken(token, user);
        } catch (Exception exception) {
            exception.printStackTrace();
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

    public UserTokenDto findByUser(SimpleUserDto user){
        Optional<UserToken> userToken = userTokenRepository.findByUser_Username(user.getSnapshot().getUsername());

        if(userToken.isEmpty()){
            throw new UserNotFoundException("Token not found!");
        }

        return toDto(userToken.get());
    }

    public UserTokenDto findByToken(String token){
        return toDto(userTokenRepository.findByToken(token));
    }

    public String validateToken(long id, String token) {
        UserTokenDto passToken = findByToken(token);
        if ((passToken == null) || (passToken.getUser().getSnapshot().getId() != id)) {
            return "invalid";
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }
        return null;
    }

    String confirm(Long id, String token) {
        String tempToken = validateToken(id, token);
        if (tempToken != null) {
            return tempToken;
        } else {
            User user = simpleUserDtoFactory.from(findByToken(token).getUser());
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(new SimpleGrantedAuthority("CONFIRMATION_EMAIL_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return null;
        }
    }

    private UserTokenDto toDto(UserToken userToken){
        UserTokenSnapshot userTokenSnapshot = userToken.getSnapshot();
        return UserTokenDto.builder()
                .withId(userTokenSnapshot.getId())
                .withToken(userTokenSnapshot.getToken())
                .withUser(SimpleUserDto.restore(userTokenSnapshot.getUser()))
                .withExpiryDate(userTokenSnapshot.getExpiryDate())
                .build();
    }
}