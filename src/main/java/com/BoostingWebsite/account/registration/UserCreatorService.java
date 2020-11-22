package com.BoostingWebsite.account.registration;

import com.BoostingWebsite.account.validator.CreationAccountValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.roles.RoleName;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.account.roles.UserRoleRepository;
import com.BoostingWebsite.account.roles.UserRole;

import java.util.ArrayList;
import java.util.List;

@Service
class UserCreatorService {
    private User user;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CreationAccountValidator creationAccountValidator;

    UserCreatorService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, CreationAccountValidator creationAccountValidator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.creationAccountValidator = creationAccountValidator;
    }

    boolean createAccount(User user, String confirmPassword) {
        this.user = user;
        try {
            tryToCreateAccount(confirmPassword);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            user.setCreationErrorMessage("Invalid registration");
            return false;
        }
    }

    private void tryToCreateAccount(String confirmPassword) {
        if (creationAccountValidator.isAccountCreatedCorrectly(this.user, confirmPassword)) {
            UserRole userRole = userRoleRepository.getUserRole(RoleName.ROLE_USER);
            userRoleRepository.save(userRole);
            List<UserRole> roles = new ArrayList<>();
            roles.add(userRole);
            String password = passwordEncoder.encode(this.user.getPassword());
            userRepository.save(new User(this.user.getUsername(), password, false, this.user.getEmail(), roles));
        }
    }
}
