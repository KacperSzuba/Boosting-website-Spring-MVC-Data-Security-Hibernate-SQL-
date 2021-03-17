package com.BoostingWebsite.account;

class UserFactory {
    UserDto toDto(User user){
        UserSnapshot snapshot = user.getSnapshot();
        return UserDto.builder()
                .withId(snapshot.getId())
                .withUsername(snapshot.getUsername())
                .withPassword(snapshot.getPassword())
                .withEnabled(snapshot.isEnabled())
                .withEmail(snapshot.getEmail())
                .withRoles(snapshot.getRoles()).build();
    }

    User from(UserDto userDto){
        return User.restore(new UserSnapshot(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.isEnabled(),
                userDto.getEmail(),
                userDto.getRoles(),
                null)
        );
    }
}
