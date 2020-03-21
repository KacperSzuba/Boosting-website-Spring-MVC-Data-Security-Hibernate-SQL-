package com.BoostingWebsite.account.user;

import com.BoostingWebsite.account.login.LoginHistory;
import com.BoostingWebsite.account.roles.UserRole;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 7,max = 20,message = "Username length should between 7 and 20 letters")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 7,message = "Password length should be more than 7 letters")
    private String password;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @OneToMany
    private List<LoginHistory> loginHistory;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;

    public User(){}

    public User(String username, String password, boolean enabled,String email ,List<UserRole> roles) {
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
}


