package com.BoostingWebsite.account.admin;

import org.springframework.stereotype.Service;
import com.BoostingWebsite.account.user.User;
import com.BoostingWebsite.account.roles.UserRole;
import com.BoostingWebsite.account.roles.RoleName;
import com.BoostingWebsite.account.user.UserRepository;
import com.BoostingWebsite.account.roles.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
class AccountStatus {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    AccountStatus(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    void banUser(Long userId) {
        userRepository.changeUserEnabledStatement(userId, false);
    }

    void unBanUser(Long userId) {
        userRepository.changeUserEnabledStatement(userId, true);
    }

    void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    void changeTheRoleName(Long userId, RoleName roleName) {
        UserRole userRole = userRoleRepository.getUserRole(roleName);
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        User user = userRepository.findById(userId).get();
        user.setRoles(roles);
        userRepository.save(user);
    }

    RoleName getCurrentUserRole(User user) {
        UserRole userRole = userRepository.getUserRole(user.getId());
        return userRole.getRoleName();
    }
}