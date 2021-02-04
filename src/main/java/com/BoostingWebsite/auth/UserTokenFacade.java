package com.BoostingWebsite.auth;

import com.BoostingWebsite.account.SimpleUserDto;
import com.BoostingWebsite.account.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Optional;

@Component
public class UserTokenFacade {

    private final UserTokenRepository userTokenRepository;

    public UserTokenFacade(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
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
            userTokenRepository.delete(userTokenRepository.findByUser_Username(user.getUsername()).get());
            saveToken(token, user);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void saveToken(String token, SimpleUserDto user) {
        try {
            UserToken userToken = new UserToken(token, user);
            userTokenRepository.save(userToken);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean checkIfUserHasTokenGenerated(SimpleUserDto user) {
        return this.userTokenRepository.findByUser_Username(user.getUsername()).isEmpty();
    }

    public UserTokenDto findByUser(SimpleUserDto user){
        Optional<UserToken> userToken = userTokenRepository.findByUser_Username(user.getUsername());

        if(userToken.isEmpty()){
            throw new UserNotFoundException("Token not found!");
        }

        return userToken.get().toDto();
    }

    public UserTokenDto findByToken(String token){
        return userTokenRepository.findByToken(token).toDto();
    }

    public String validateToken(long id, String token) {
        UserTokenDto passToken = findByToken(token);
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