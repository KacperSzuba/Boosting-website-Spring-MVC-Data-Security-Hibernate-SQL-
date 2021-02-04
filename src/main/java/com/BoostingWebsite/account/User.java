package com.BoostingWebsite.account;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 7, max = 20, message = "Username length should be between 7 and 20 letters")
    private String username;

    @Size(min = 7, message = "Password length should be between 7 and 20 letters")
    private String password;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    @NotEmpty(message = "E-mail cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserRole> roles;

    @Transient
    private String creationErrorMessage;

    @PersistenceConstructor
    public User() {
    }

    User(Long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }

    User(String username, String password, boolean enabled, String email, List<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.roles = roles;
    }

    Long getId() {
        return id;
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    boolean isEnabled() {
        return enabled;
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    List<UserRole> getRoles() {
        return roles;
    }

    void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    String getCreationErrorMessage() {
        return creationErrorMessage;
    }

    void setCreationErrorMessage(String creationErrorMessage) {
        this.creationErrorMessage = creationErrorMessage;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    UserDto toDto(){
        return UserDto.builder()
                .withId(id)
                .withUsername(username)
                .withPassword(password)
                .withEnabled(enabled)
                .withEmail(email)
                .withRoles(roles).build();
    }
}


