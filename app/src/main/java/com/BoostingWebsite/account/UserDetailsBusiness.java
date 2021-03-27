package com.BoostingWebsite.account;

import com.BoostingWebsite.account.exception.UserNotFoundException;
import com.BoostingWebsite.utils.ApplicationSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

class UserDetailsBusiness implements UserDetailsService {
    private static final Logger logger  = LoggerFactory.getLogger(UserDetailsBusiness.class);

    private final UserRepository userRepository;
    private final LoginHistoryBusiness loginHistoryBusiness;
    private final ApplicationSession session;
    private final UserFactory userFactory;

    UserDetailsBusiness(UserRepository userRepository, LoginHistoryBusiness loginHistoryBusiness, final ApplicationSession session, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.loginHistoryBusiness = loginHistoryBusiness;
        this.session = session;
        this.userFactory = userFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UserNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() ->{
                    logger.warn("Error during user login! Name or Email: " + usernameOrEmail);
                    throw new UserNotFoundException("User " + usernameOrEmail + " not found");
                });

        Collection<GrantedAuthority> authorities = user.getSnapshot().getRoles()
                .stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName().toString()))
                .collect(Collectors.toCollection(ArrayList::new));
        session.setUser(userFactory.toDto(user));
        loginHistoryBusiness.save(user);
        return new org.springframework.security.core.userdetails.
                User(usernameOrEmail, user.getSnapshot().getPassword(), user.getSnapshot().isEnabled(), true,
                true, true, authorities);
    }
}
