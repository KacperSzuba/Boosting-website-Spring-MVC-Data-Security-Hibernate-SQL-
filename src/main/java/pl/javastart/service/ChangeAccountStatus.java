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

    public void banUser(Long userId){
        userRepository.changeUserEnabledStatement(userId,false);
    }

    public void unBanUser(Long userId){
        userRepository.changeUserEnabledStatement(userId,true);
    }

    public void deleteUser(Long userId){
        userRepository.deleteUser(userId);
    }

    public void changeTheRoleName(Long userId, RoleName roleName){
        UserRole userRole = userRoleRepository.getUserRole(roleName);
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        User user = userRepository.findById(userId).get();
        user.setRoles(roles);
        userRepository.save(user);
    }

    public RoleName getCurrentUserRole(User user){
       UserRole userRole = userRepository.getUserRole(user.getId());
       return userRole.getRoleName();
    }
}