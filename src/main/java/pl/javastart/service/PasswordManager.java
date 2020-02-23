package pl.javastart.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.manage.ActualUser;
import pl.javastart.repository.user.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class PasswordManager {

    private String message;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ActualUser actualUser;
    private final HttpServletRequest request;
    public PasswordManager(PasswordEncoder passwordEncoder, UserRepository userRepository, ActualUser actualUser,HttpServletRequest request) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.actualUser = actualUser;
        this.request = request;
    }

    public void changePassword(String password, String repeatPassword){
        try {
            tryToChangePassword(actualUser.getActualUser(this.request).getId(),password,repeatPassword);
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