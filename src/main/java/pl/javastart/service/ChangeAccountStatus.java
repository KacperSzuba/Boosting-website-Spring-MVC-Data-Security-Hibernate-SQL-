package pl.javastart.service;

import org.springframework.stereotype.Service;
import pl.javastart.model.entity.user.User;
import pl.javastart.model.entity.user.UserRole;
import pl.javastart.model.enums.RoleName;
import pl.javastart.repository.user.UserRepository;
import pl.javastart.repository.user.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChangeAccountStatus {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public ChangeAccountStatus(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public void banUser(Long id){
        userRepository.changeEnabledStatementQuery(id,false);
    }

    public void unBanUser(Long id){
        userRepository.changeEnabledStatementQuery(id,true);
    }

    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }

    public void changeTheRoleName(Long id, RoleName roleName){
        UserRole userRole = userRoleRepository.getUserRole(roleName);
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        User user = userRepository.findById(id).get();
        user.setRoles(roles);
        userRepository.save(user);
    }

    public RoleName getCurrentUserRole(User user){
       UserRole ur = userRepository.getUserRole(user.getId());
       return ur.getRoleName();
    }
}