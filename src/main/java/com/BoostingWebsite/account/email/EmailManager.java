package com.BoostingWebsite.account.email;

import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.ActualUser;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.validator.EmailValidator;
import com.BoostingWebsite.exceptions.DataMismatchException;

@Service
class EmailManager {
    private String message;

    private final UserRepository userRepository;
    private final ActualUser actualUser;

    public EmailManager(UserRepository userRepository, ActualUser actualUser) {
        this.userRepository = userRepository;
        this.actualUser = actualUser;
    }

    void changeEmail(String currentEmail, String email, String repeatEmail){
        try{
            whetherEmailCanBeChanged(currentEmail,email,repeatEmail);
        }
        catch (DataMismatchException exception){
            setMessage(exception.getMessage());
        }
    }

    private void whetherEmailCanBeChanged(String currentEmail,String email, String repeatEmail) throws DataMismatchException {
        if(checkIfEmailEnteredMatchesCurrent(currentEmail) && checkIfEmailIsCorrect(email,repeatEmail)){
            tryToChangeEmail(email);
        }
    }

    private boolean checkIfEmailIsCorrect(String email,String repeatEmail) throws DataMismatchException {
        if (whetherTheEmailsAreTheSame(email, repeatEmail) && EmailValidator.validateEmail(email)) {
            return true;
        }
        else {
            throw new DataMismatchException("Your email is wrong or emails are different");
        }
    }

    private boolean checkIfEmailEnteredMatchesCurrent(String currentEmail) throws DataMismatchException {
        if(currentEmail.equals(loggedInUser().getEmail())){
            return true;
        }
        else {
            throw new DataMismatchException("The email you entered does not match the current one");
        }
    }

    private void tryToChangeEmail(String email){
        userRepository.changeEmail(loggedInUser().getId(), email);
        setMessage("Your new email is : " + email);
    }

    private User loggedInUser(){
        return actualUser.getActualUser();
    }

    private boolean whetherTheEmailsAreTheSame(String email,String repeatEmail){
        return email.equals(repeatEmail);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
