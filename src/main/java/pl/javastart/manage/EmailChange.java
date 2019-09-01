package pl.javastart.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.repository.UserRepository;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Email;

@Service
public class EmailChange {

    private String message;

    @Autowired
    private UserRepository userRepository;

    public void changeEmail(Long id,String email,String repeatEmail){
        tryToChangeEmail(id,email,repeatEmail);
    }

    private void tryToChangeEmail(Long id,String email,String repeatEmail){
        try {
            if (whetherTheEmailsAreTheSame(email, repeatEmail) && isValid(email)) {
                userRepository.changeEmailQuery(id, email);
                setMessage("Your new email is : " + email);
            } else {
                throw new IllegalArgumentException("Your email is wrong or emails are different");
            }
        }
        catch (IllegalArgumentException exception){
            setMessage(exception.getMessage());
        }
    }

    private boolean whetherTheEmailsAreTheSame(String email,String repeatEmail){
        return email.equals(repeatEmail);
    }

    static boolean isValid(String email) {
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
