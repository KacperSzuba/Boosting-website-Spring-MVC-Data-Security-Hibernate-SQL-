package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFacade {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleFacade userRoleFacade;
    private final UserTokenFacade userTokenFacade;
    private final SimpleUserDtoFactory simpleUserDtoFactory;
    private final UserQueryRepository userQueryRepository;

    UserFacade(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleFacade userRoleFacade,
               UserTokenFacade userTokenFacade, SimpleUserDtoFactory simpleUserDtoFactory, UserQueryRepository userQueryRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleFacade = userRoleFacade;
        this.userTokenFacade = userTokenFacade;
        this.simpleUserDtoFactory = simpleUserDtoFactory;
        this.userQueryRepository = userQueryRepository;
    }

    User findById(Long id){
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
        return userQueryRepository.existsUserByEmail(email);
    }

    public RoleName getRoleName(UserDto userDto){
        User user = findById(userDto.getId());

        return userRepository.getUserRole(user.getId()).getRoleName();
    }

    public User findUserByToken(String token){
        return simpleUserDtoFactory.from(userTokenFacade.findByToken(token).getUser());
    }

    User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public UserDto findUserDtoByUsername(String username){
        return toDto(userRepository.findByUsername(username));
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void setEnabled(Long id){
        userRepository.changeUserEnabledStatement(id, true);
    }

    public void createAccount(User user) {
        try {
            UserRole userRole = userRoleFacade.save(RoleName.ROLE_USER);

            String password = passwordEncoder.encode(user.getPassword());
            userRepository.save(new User(user.getUsername(), password, false, user.getEmail(), List.of(userRole)));
        } catch (Exception exception) {
            user.setCreationErrorMessage("Invalid registration");
        }
    }


    private UserDto toDto(User user){
        return UserDto.builder()
                .withId(user.getId())
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withEnabled(user.isEnabled())
                .withEmail(user.getEmail())
                .withRoles(user.getRoles()).build();
    }
}
