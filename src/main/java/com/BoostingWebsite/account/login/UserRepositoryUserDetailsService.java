package com.BoostingWebsite.account.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.user.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user!=null){
                Collection<GrantedAuthority> authorities = user.getRoles()
                        .stream()
                        .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName().toString()))
                        .collect(Collectors.toCollection(ArrayList::new));
            return new org.springframework.security.core.userdetails.
                    User(username,user.getPassword(),user.isEnabled(),true,
                    true,true,authorities);
        }
        else {
            throw new UsernameNotFoundException("User "+username+" not found");
        }
    }
}
