package com.BoostingWebsite.account.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    UserRepositoryUserDetailsService(UserRepository userRepository, LoginHistoryRepository loginHistoryRepository) {
        this.userRepository = userRepository;
        this.loginHistoryRepository = loginHistoryRepository;
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
            loginHistoryRepository.save(new LoginHistory(LocalDateTime.now(), user));
            return new org.springframework.security.core.userdetails.
                    User(usernameOrEmail, user.getPassword(), user.isEnabled(), true,
                    true, true, authorities);
        } else {
            throw new UsernameNotFoundException("User " + usernameOrEmail + " not found");
        }
    }
}
