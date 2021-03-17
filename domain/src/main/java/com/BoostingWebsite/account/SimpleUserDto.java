package com.BoostingWebsite.account;


public class SimpleUserDto {
    private Long id;
    private String username;
    private String email;

    private SimpleUserDto(Long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static SimpleUserDto restore(SimpleUserDtoSnapshot snapshot){
        return new SimpleUserDto(snapshot.getId(), snapshot.getUsername(), snapshot.getEmail());
    }

    public SimpleUserDtoSnapshot getSnapshot(){
        return new SimpleUserDtoSnapshot(id, username, email);
    }
}
