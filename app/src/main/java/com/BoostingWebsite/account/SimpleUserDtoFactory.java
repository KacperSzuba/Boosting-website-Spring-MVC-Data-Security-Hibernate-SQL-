package com.BoostingWebsite.account;

import java.util.ArrayList;

class SimpleUserDtoFactory {
    User from(final SimpleUserDto source){
        SimpleUserDtoSnapshot snapshot = source.getSnapshot();
        return User.restore(
                new UserSnapshot(
                        snapshot.getId(),
                        snapshot.getUsername(),
                        null,
                        false,
                        snapshot.getEmail(),
                        new ArrayList<>(),
                        null
                )
        );
    }

    SimpleUserDto to(final User source){
        UserSnapshot snapshot = source.getSnapshot();
        return SimpleUserDto.restore(
                new SimpleUserDtoSnapshot(
                        snapshot.getId(),
                        snapshot.getUsername(),
                        snapshot.getEmail()
                )
        );
    }
}
