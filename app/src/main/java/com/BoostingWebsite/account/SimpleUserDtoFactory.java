package com.BoostingWebsite.account;

class SimpleUserDtoFactory {
    User from(final SimpleUserDto source){
        return new User(source.getId(), source.getUsername(), source.getEmail());
    }

    SimpleUserDto to(final User source){
        return new SimpleUserDto(source.getId(), source.getUsername(), source.getEmail());
    }
}
