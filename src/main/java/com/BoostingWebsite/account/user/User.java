package com.BoostingWebsite.account.user;

import com.BoostingWebsite.account.login.LoginHistory;
import com.BoostingWebsite.account.roles.UserRole;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
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

    @OneToMany(fetch = FetchType.EAGER)
    private List<LoginHistory> loginHistory;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserRole> roles;

    @Transient
    private String creationErrorMessage;

    @PersistenceConstructor
    public User() {
    }

    public User(String username, String password, boolean enabled, String email, List<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<LoginHistory> getLoginHistory() {
        return loginHistory;
    }

    public String getCreationErrorMessage() {
        return creationErrorMessage;
    }

    public void setCreationErrorMessage(String creationErrorMessage) {
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
                ", loginHistory=" + loginHistory +
                ", roles=" + roles +
                '}';
    }
}


