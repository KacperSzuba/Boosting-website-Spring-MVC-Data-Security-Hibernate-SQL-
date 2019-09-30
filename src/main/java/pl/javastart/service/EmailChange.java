package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class EmailChange {

    private String message;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActualUser actualUser;

    public void changeEmail(HttpServletRequest request, String email, String repeatEmail){
        try{
            tryToChangeEmail(actualUser.getActualUser(request).getId(),email,repeatEmail);
        }
        catch (IllegalArgumentException exception){
            setMessage(exception.getMessage());
        }
    }

    private void tryToChangeEmail(Long id,String email,String repeatEmail){
        if (whetherTheEmailsAreTheSame(email, repeatEmail) && isValid(email)) {
            userRepository.changeEmailQuery(id, email);
            setMessage("Your new email is : " + email);
        } else {
            throw new IllegalArgumentException("Your email is wrong or emails are different");
        }
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
