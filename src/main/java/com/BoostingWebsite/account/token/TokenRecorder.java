package com.BoostingWebsite.account.token;

import com.BoostingWebsite.account.user.User;
import org.springframework.stereotype.Component;

@Component
public class TokenRecorder {

    private final UserTokenRepository userTokenRepository;

    public TokenRecorder(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public void saveOrUpdateToken(String token, User user) {
        if (checkIfUserHasTokenGenerated(user)) {
            saveToken(token,user);
        }
        else {
            updateToken(token,user);
        }
    }

    private void updateToken(String token,User user) {
        userTokenRepository.delete(userTokenRepository.findByUser(user).get());
        saveToken(token, user);
    }

    private void saveToken(String token,User user) {
        this.userTokenRepository.save(new UserToken(token, user));
    }

    private boolean checkIfUserHasTokenGenerated(User user) {
        return this.userTokenRepository.findByUser(user).isEmpty();
    }
}