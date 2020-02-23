package pl.javastart.service;

import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.repository.user.UserRepository;

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

    public void changeEmail(String email, String repeatEmail){
        try{
            checkIfEmailIsCorrect(actualUser.getActualUser(this.request).getId(),email,repeatEmail);
        }
        catch (IllegalArgumentException exception){
            setMessage(exception.getMessage());
        }
    }

    private void checkIfEmailIsCorrect(Long id,String email,String repeatEmail){
        if (whetherTheEmailsAreTheSame(email, repeatEmail) && isValid(email)) {
            tryToChangeEmail(id,email);
        }
        else {
            throw new IllegalArgumentException("Your email is wrong or emails are different");
        }
    }


    private void tryToChangeEmail(Long id,String email){
        userRepository.changeEmail(id, email);
        setMessage("Your new email is : " + email);
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
