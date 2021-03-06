package com.BoostingWebsite.account;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

class UserRepositoryUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final LoginHistoryFacade loginHistoryFacade;

    UserRepositoryUserDetailsService(final UserRepository userRepository, final LoginHistoryFacade loginHistoryFacade) {
        this.userRepository = userRepository;
        this.loginHistoryFacade = loginHistoryFacade;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(usernameOrEmail);

        if (user == null && userRepository.findByEmail(usernameOrEmail).isPresent()) {
            user = userRepository.findByEmail(usernameOrEmail).get();
        }

        if (user != null) {
            Collection<GrantedAuthority> authorities = user.getRoles()
                    .stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName().toString()))
                    .collect(Collectors.toCollection(ArrayList::new));
            loginHistoryFacade.save(user);
            return new org.springframework.security.core.userdetails.
                    User(usernameOrEmail, user.getPassword(), user.isEnabled(), true,
                    true, true, authorities);
        } else {
            throw new UsernameNotFoundException("User " + usernameOrEmail + " not found");
        }
    }
}
