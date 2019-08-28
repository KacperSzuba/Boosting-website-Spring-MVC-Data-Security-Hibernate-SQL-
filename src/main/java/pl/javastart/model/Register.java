


package pl.javastart.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.User;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;

@Service
public class Register {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private void tryToCreateAccount(User user){
        boolean isUserExist = userRepository.existsUserByUsername(user.getUsername());

        if(isUserExist){

        }
    }
}
