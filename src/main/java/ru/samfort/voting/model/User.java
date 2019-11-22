package ru.samfort.voting.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Roles> roles;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    private String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 50)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled;

    public User() {
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getRegistered(), user.getEmail(), user.getPassword(), user.isEnabled(), user.getRoles());
    }

    public User(Integer id, String name, String email, String password, Roles role, Roles... roles) {
        this(id, name, new Date(), email, password, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, Date registered, String email, String password, boolean enabled, Collection<Roles> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        setRoles(roles);
    }

    public void setRoles(Collection<Roles> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Roles.class) : EnumSet.copyOf(roles);
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
