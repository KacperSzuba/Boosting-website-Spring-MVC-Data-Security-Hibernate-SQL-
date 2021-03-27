package com.BoostingWebsite.account;

class UserTokenFactory {
    UserTokenDto toDto(UserToken userToken){
        UserTokenSnapshot userTokenSnapshot = userToken.getSnapshot();
        return UserTokenDto.builder()
                .withId(userTokenSnapshot.getId())
                .withToken(userTokenSnapshot.getToken())
                .withUser(SimpleUserDto.restore(userTokenSnapshot.getUser()))
                .withExpiryDate(userTokenSnapshot.getExpiryDate())
                .build();
    }

    UserToken from(UserTokenDto dto){
        return UserToken.restore(
                new UserTokenSnapshot(
                        dto.getId(),
                        dto.getToken(),
                        dto.getUser().getSnapshot(),
                        dto.getExpiryDate()
                )
        );
    }
}
