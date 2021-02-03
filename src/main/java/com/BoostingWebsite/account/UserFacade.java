package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.UserNotFoundException;
import com.BoostingWebsite.auth.UserTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFacade {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleFacade userRoleFacade;
    private final UserTokenRepository userTokenRepository;
    private final UserValidator userValidator;

    UserFacade(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleFacade userRoleFacade, UserTokenRepository userTokenRepository,
               UserValidator userValidator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleFacade = userRoleFacade;
        this.userTokenRepository = userTokenRepository;
        this.userValidator = userValidator;
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }

        return user.get();
    }

    public void changePassword(UserDto userDto, String password){
        User user = findById(userDto.getId());
        userRepository.changePassword(user.getId(), password);
    }

    public void changeEmail(UserDto userDto, String email){
        User user = findById(userDto.getId());

        userRepository.changeEmail(user.getId(), email);
    }

    public boolean existsUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

    public RoleName getRoleName(UserDto userDto){
        User user = findById(userDto.getId());

        return userRepository.getUserRole(user.getId()).getRoleName();
    }

    public User findUserByToken(String token){
        return userTokenRepository.findByToken(token).getUser();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void setEnabled(Long id){
        userRepository.changeUserEnabledStatement(id, true);
    }

    public boolean createAccount(User user, String confirmPassword) {
        try {
            if (userValidator.isAccountCreatedCorrectly(user, confirmPassword)) {
                UserRole userRole = userRoleFacade.save(RoleName.ROLE_USER);

                String password = passwordEncoder.encode(user.getPassword());
                userRepository.save(new User(user.getUsername(), password, false, user.getEmail(), List.of(userRole)));
            }

            return true;
        } catch (Exception exception) {
            user.setCreationErrorMessage("Invalid registration");
            return false;
        }
    }
}
