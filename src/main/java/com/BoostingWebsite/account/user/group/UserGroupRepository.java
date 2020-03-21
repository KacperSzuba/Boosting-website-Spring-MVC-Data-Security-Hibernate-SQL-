package com.BoostingWebsite.account.user.group;

import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup,Long> {
    UserGroup findByUserGroups(UserGroups userGroups);
}
