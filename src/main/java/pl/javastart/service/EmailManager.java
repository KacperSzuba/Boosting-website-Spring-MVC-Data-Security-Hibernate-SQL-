package pl.javastart.service;

import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.model.entity.user.User;
import pl.javastart.repository.user.UserRepository;
import pl.javastart.service.exception.DataMismatchException;

import javax.servlet.http.HttpServletRequest;

@Service
public class EmailManager {
    private String message;

    private final UserRepository userRepository;
    private final ActualUser actualUser;
    private final HttpServletRequest request;

    public EmailManager(UserRepository userRepository, ActualUser actualUser,HttpServletRequest request) {
        this.userRepository = userRepository;
        this.actualUser = actualUser;
        this.request = request;
    }

    public void changeEmail(String currentEmail,String email, String repeatEmail){
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
        if (whetherTheEmailsAreTheSame(email, repeatEmail) && isValid(email)) {
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
        return actualUser.getActualUser(this.request);
    }

    private boolean whetherTheEmailsAreTheSame(String email,String repeatEmail){
        return email.equals(repeatEmail);
    }

    private static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
