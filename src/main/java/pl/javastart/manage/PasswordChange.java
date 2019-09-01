package pl.javastart.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javastart.repository.UserRepository;

@Service
public class PasswordChange {

    private String message;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void changePassword(Long id,String password){
        tryToChangePassword(id,password);
    }

    private void tryToChangePassword(Long id,String password){
        try {
            if(isPasswordLengthSufficient(password)){
                userRepository.changePasswordQuery(id,passwordEncoder.encode(password));
                setMessage("Your new password is : "+password);
            }
            else {
                throw new IllegalArgumentException("Your password is too short");
            }
        }
        catch (IllegalArgumentException exception){
            setMessage(exception.getMessage());
        }
    }

    private boolean isPasswordLengthSufficient(String password){
        return password.length() >= 7;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}
