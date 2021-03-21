package com.BoostingWebsite.account;

import com.BoostingWebsite.utils.ApplicationSession;
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
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail);

        if (user != null && user.isPresent()) {
            Collection<GrantedAuthority> authorities = user.get().getSnapshot().getRoles()
                    .stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName().toString()))
                    .collect(Collectors.toCollection(ArrayList::new));
            session.setUser(userFactory.toDto(user.get()));
            loginHistoryBusiness.save(user.get());
            return new org.springframework.security.core.userdetails.
                    User(usernameOrEmail, user.get().getSnapshot().getPassword(), user.get().getSnapshot().isEnabled(), true,
                    true, true, authorities);
        } else {
            throw new UsernameNotFoundException("User " + usernameOrEmail + " not found");
        }
    }
}
