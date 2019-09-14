package pl.javastart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.UserRole;
import pl.javastart.model.entity.enums.RoleName;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChangeAccountStatus {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public void banAccount(Long id){
        userRepository.changeEnabledStatementQuery(id,false);
    }

    public void unBanAccount(Long id){
        userRepository.changeEnabledStatementQuery(id,true);
    }

    public void setRoleAsBooster(Long id, List<UserRole> role){
        userRepository.changeUserRole(id,role);
    }

    public RoleName setd(User user){
       UserRole ur = userRepository.getRoleUser(user.getId());
       return ur.getRoleName();
    }
}