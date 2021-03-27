package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.DataMismatchException;
import com.BoostingWebsite.account.exception.UserNotFoundException;
import com.BoostingWebsite.email.EmailBusiness;
import com.BoostingWebsite.utils.BaseBusiness;
import com.BoostingWebsite.utils.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.BoostingWebsite.utils.EmailValidator.whetherEmailIsValid;
import static com.BoostingWebsite.utils.EmailValidator.whetherTheEmailsAreTheSame;
import static com.BoostingWebsite.utils.PasswordValidator.whetherThePasswordsAreTheSame;

class UserBusiness extends BaseBusiness {
    private static final Logger logger  = LoggerFactory.getLogger(UserBusiness.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleBusiness userRoleBusiness;
    private final UserTokenBusiness userTokenBusiness;
    private final SimpleUserDtoFactory simpleUserDtoFactory;
    private final UserQueryRepository userQueryRepository;
    private final LoginHistoryBusiness loginHistoryBusiness;
    private final EmailBusiness emailBusiness;
    private final UserFactory userFactory;

    UserBusiness(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleBusiness userRoleBusiness,
                 UserTokenBusiness userTokenBusiness, SimpleUserDtoFactory simpleUserDtoFactory, UserQueryRepository userQueryRepository,
                 LoginHistoryBusiness loginHistoryBusiness, EmailBusiness emailBusiness, UserFactory userFactory) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleBusiness = userRoleBusiness;
        this.userTokenBusiness = userTokenBusiness;
        this.simpleUserDtoFactory = simpleUserDtoFactory;
        this.userQueryRepository = userQueryRepository;
        this.loginHistoryBusiness = loginHistoryBusiness;
        this.emailBusiness = emailBusiness;
        this.userFactory = userFactory;
    }

    User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    SimpleUserDto findSimpleUserDtoById(Long id){
        return userQueryRepository.getById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    boolean existsUserByEmail(String email){
        return userQueryRepository.existsUserByEmail(email);
    }

    RoleName getRoleName(UserDto userDto){
        User user = findById(userDto.getId());

        return userRepository.getUserRole(user.getSnapshot().getId()).get().getSnapshot().getRoleName();
    }

    User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    void enable(Long id){
        User user = findById(id);
        user.enable();
        userRepository.save(user);
    }

    void createAccount(User user) {
        try {
            /*
            UserRole userRole = userRoleFacade.save(RoleName.ROLE_USER);

            String password = passwordEncoder.encode(user.getPassword());
            userRepository.save(new User(user.getUsername(), password, false, user.getEmail(), List.of(userRole)));

            confirmEmail(applicationSession.getAppUrl(), applicationSession.getToken(), user);
             */
        } catch (Exception ex) {
            logger.error("Error while creating a user", ex);
            user.creationMessage("Invalid registration");
        }
    }

    void confirmEmail(String contextPath, String token, User user) {
        String url = contextPath + "/register/confirm?id=" + user.getSnapshot().getId() + "&token=" + token;
        userTokenBusiness.saveOrUpdateToken(token, simpleUserDtoFactory.to(user));
        emailBusiness.sendEmail(user.getSnapshot().getEmail(), "Confirm email", " \r\n" + url);
    }

    void changeEmail(String currentEmail, String email, String confirmEmail) throws DataMismatchException {
        if (checkIfEmailEnteredMatchesCurrent(currentEmail) && checkIfEmailIsCorrect(email, confirmEmail) && checkIfEmailIsNotAlreadyInDatabase(currentEmail)) {
            User user = userFactory.from(applicationSession.getContext().getUser());
            user.changeEmail(email);
            userRepository.save(user);
        }
    }

    private boolean checkIfEmailIsCorrect(String email, String confirmEmail) throws DataMismatchException {
        if (whetherTheEmailsAreTheSame(email, confirmEmail) && EmailValidator.whetherEmailIsValid(email)) {
            return true;
        }

        throw new DataMismatchException("Your email is wrong or emails are different");
    }

    private boolean checkIfEmailEnteredMatchesCurrent(String currentEmail) throws DataMismatchException {
        if (currentEmail.equals(applicationSession.getContext().getUser().getEmail())) {
            return true;
        }

        throw new DataMismatchException("The email you entered does not match the current one");
    }

    private boolean checkIfEmailIsNotAlreadyInDatabase(String currentEmail) throws DataMismatchException {
        if (existsUserByEmail(currentEmail)) {
            return true;
        }

        throw new DataMismatchException("The email you entered already exists in our database");
    }

    void remindPassword(String email) throws DataMismatchException {
        if (whetherEmailIsValid(email)) {
            User user = findByEmail(email);
            userTokenBusiness.saveOrUpdateToken(applicationSession.getToken(), simpleUserDtoFactory.to(user));
            String token = userTokenBusiness.findByUser(simpleUserDtoFactory.to(user)).getToken();
            emailBusiness.constructResetTokenEmail(applicationSession.getAppUrl(), token, simpleUserDtoFactory.to(user));
        }

        throw new DataMismatchException("This e-mail is invalid!");
    }

    void resetPassword(String newPassword, String confirmNewPassword) throws DataMismatchException {
        User user = userFactory.from(applicationSession.getContext().getUser());

        if(user.canResetPassword(newPassword, confirmNewPassword)){
            user.changePassword(newPassword);
            userRepository.save(user);
        }

        throw new DataMismatchException("An unknown error has occurred!");
    }

    void changePassword(String currentPassword, String newPassword, String confirmNewPassword) throws DataMismatchException {
        User user = userFactory.from(applicationSession.getContext().getUser());

        if(user.canChangePassword(currentPassword, newPassword, confirmNewPassword)){
            user.changePassword(newPassword);
            userRepository.save(user);
        }

        throw new DataMismatchException("An unknown error has occurred!");
    }

    boolean canCreateAccount(User user, String confirmPassword) throws DataMismatchException {
        return checkIfEmailNotExist(user) && checkIfUserNotExists(user) && whetherThePasswordsAreTheSame(user.getSnapshot().getPassword(), confirmPassword);
    }

    private boolean checkIfUserNotExists(User user) throws DataMismatchException {
        boolean isUserExist = userQueryRepository.existsUserByUsername(user.getSnapshot().getUsername());
        if (!isUserExist) {
            return true;
        }

        throw new DataMismatchException("User with this username already exist");
    }

    private boolean checkIfEmailNotExist(User user) throws DataMismatchException {
        boolean isEmailExist = userQueryRepository.existsUserByEmail(user.getSnapshot().getEmail());
        if (!isEmailExist) {
            return true;
        }

        throw new DataMismatchException("User with this email already exist");
    }
}