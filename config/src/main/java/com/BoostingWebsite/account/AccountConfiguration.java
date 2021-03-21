package com.BoostingWebsite.account;

import com.BoostingWebsite.email.EmailBusiness;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class AccountConfiguration {
    @Bean
    UserDetailsBusiness userDetailsBusiness(
            final UserRepository userRepository,
            final LoginHistoryBusiness loginHistoryBusiness,
            final ApplicationSession session){
        return new UserDetailsBusiness(userRepository, loginHistoryBusiness, session, new UserFactory());
    }

    @Bean
    LoginHistoryBusiness loginHistoryBusiness(final LoginHistoryRepository loginHistoryRepository){
        return new LoginHistoryBusiness(loginHistoryRepository);
    }

    @Bean
    UserBusiness userBusiness(
            final PasswordEncoder passwordEncoder,
            final UserRepository userRepository,
            final UserRoleBusiness userRoleBusiness,
            final UserTokenBusiness userTokenBusiness,
            final UserQueryRepository userQueryRepository,
            final LoginHistoryBusiness loginHistoryBusiness,
            final EmailBusiness emailBusiness
    ){
        return new UserBusiness(
                passwordEncoder,
                userRepository,
                userRoleBusiness,
                userTokenBusiness,
                new SimpleUserDtoFactory(),
                userQueryRepository,
                loginHistoryBusiness,
                emailBusiness,
                new UserFactory());
    }

    @Bean
    UserRoleBusiness userRoleBusiness(final UserRoleRepository userRoleRepository){
        return new UserRoleBusiness(userRoleRepository);
    }

    @Bean
    UserTokenBusiness userTokenBusiness(final UserTokenRepository userTokenRepository){
        return new UserTokenBusiness(userTokenRepository, new SimpleUserDtoFactory());
    }

    @Bean
    EmailManagerFacade emailManagerFacade(final UserBusiness userBusiness){
        return new EmailManagerFacade(userBusiness);
    }

    @Bean
    PasswordManagerFacade passwordManagerFacade(final UserBusiness userBusiness){
        return new PasswordManagerFacade(userBusiness);
    }

    @Bean
    PasswordReminderFacade passwordReminderFacade(final UserBusiness userBusiness, final UserTokenBusiness userTokenBusiness){
        return new PasswordReminderFacade(userBusiness, userTokenBusiness);
    }

    @Bean
    PasswordResetFacade passwordResetFacade(final UserBusiness userBusiness){
        return new PasswordResetFacade(userBusiness);
    }

    @Bean
    RegisterFacade registerFacade(final UserBusiness userBusiness, final UserTokenBusiness userTokenBusiness){
        return new RegisterFacade(userBusiness, userTokenBusiness);
    }
}
