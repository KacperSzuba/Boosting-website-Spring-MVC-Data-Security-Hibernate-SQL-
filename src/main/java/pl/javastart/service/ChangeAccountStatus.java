package pl.javastart.service;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javastart.model.entity.User;
import pl.javastart.model.entity.UserRole;
import pl.javastart.model.entity.enums.RoleName;
import pl.javastart.repository.UserRepository;
import pl.javastart.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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