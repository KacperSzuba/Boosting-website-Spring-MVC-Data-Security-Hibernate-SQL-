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
            saveToken(token, user);
        } else {
            updateToken(token, user);
        }
    }

    private void updateToken(String token, User user) {
        tryToUpdateToken(token, user);
    }

    private void tryToUpdateToken(String token, User user) {
        try {
            userTokenRepository.delete(userTokenRepository.findByUser(user).get());
            saveToken(token, user);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void saveToken(String token, User user) {
        tryToSaveToken(token, user);
    }

    private void tryToSaveToken(String token, User user) {
        try {
            UserToken userToken = new UserToken(token, user);
            userTokenRepository.save(userToken);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean checkIfUserHasTokenGenerated(User user) {
        return this.userTokenRepository.findByUser(user).isEmpty();
    }
}