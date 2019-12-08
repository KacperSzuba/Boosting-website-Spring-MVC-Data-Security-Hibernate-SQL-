package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class PasswordManager {

    private String message;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActualUser actualUser;

    public void changePassword(HttpServletRequest request, String password, String repeatPassword){
        try {
            tryToChangePassword(actualUser.getActualUser(request).getId(),password,repeatPassword);
        }
        catch (IllegalArgumentException exception){
            setMessage(exception.getMessage());
        }
    }

    private void tryToChangePassword(Long id,String password,String repeatPassword){
        if(isPasswordLengthSufficient(password) && whetherThePasswordsAreTheSame(password,repeatPassword)){
            userRepository.changePasswordQuery(id,passwordEncoder.encode(password));
            setMessage("Your new password is : "+password);
        }
        else {
            throw new IllegalArgumentException("Your password is too short or passwords are different");
        }
    }

    private boolean isPasswordLengthSufficient(String password){
        return password.length() >= 7;
    }

    private boolean whetherThePasswordsAreTheSame(String password,String repeatPassword){
        return password.equals(repeatPassword);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}