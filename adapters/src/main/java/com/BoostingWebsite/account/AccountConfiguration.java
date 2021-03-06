package com.BoostingWebsite.account;

import com.BoostingWebsite.email.EmailService;
import com.BoostingWebsite.utils.ApplicationSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class AccountConfiguration {

    @Bean
    EmailManager emailManager(
            final ApplicationSession applicationSession,
            final EmailService emailService,
            final UserTokenFacade userTokenFacade,
            final UserFacade userFacade){
        return new EmailManager(applicationSession, emailService, userTokenFacade, userFacade, new SimpleUserDtoFactory());
    }

    @Bean
    LoginHistoryFacade loginHistoryFacade(final LoginHistoryRepository loginHistoryRepository){
        return new LoginHistoryFacade(loginHistoryRepository);
    }

    @Bean
    PasswordManager passwordManager(
            final PasswordEncoder passwordEncoder,
            final UserFacade userFacade,
            final ApplicationSession applicationSession,
            final UserTokenFacade tokenRecorder,
            final EmailService emailService
    ){
        return new PasswordManager(passwordEncoder, userFacade, applicationSession, tokenRecorder, emailService, new SimpleUserDtoFactory());
    }

    @Bean
    UserFacade userFacade(
            final PasswordEncoder passwordEncoder,
            final UserRepository userRepository,
            final UserRoleFacade userRoleFacade,
            final UserTokenFacade userTokenFacade,
            final UserQueryRepository userQueryRepository
    ){
        return new UserFacade(passwordEncoder, userRepository, userRoleFacade, userTokenFacade, new SimpleUserDtoFactory(), userQueryRepository);
    }

    @Bean
    UserRoleFacade userRoleFacade(final UserRoleRepository userRoleRepository){
        return new UserRoleFacade(userRoleRepository);
    }

    @Bean
    UserTokenFacade userTokenFacade(final UserTokenRepository userTokenRepository){
        return new UserTokenFacade(userTokenRepository);
    }

    @Bean UserValidator userValidator(final UserQueryRepository userQueryRepository){
        return new UserValidator(userQueryRepository);
    }

    @Bean
    UserRepositoryUserDetailsService userRepositoryUserDetailsService(final UserRepository userRepository, final LoginHistoryFacade loginHistoryFacade){
        return new UserRepositoryUserDetailsService(userRepository, loginHistoryFacade);
    }
}
