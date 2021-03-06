package com.BoostingWebsite.account;

import org.springframework.data.annotation.Immutable;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Immutable
public class SimpleUserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 7, max = 20, message = "Username length should be between 7 and 20 letters")
    private String username;

    @NotEmpty(message = "E-mail cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @PersistenceConstructor
    public SimpleUserDto(){
    }

    public SimpleUserDto(Long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
