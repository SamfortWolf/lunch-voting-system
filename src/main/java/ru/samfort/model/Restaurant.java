package ru.samfort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurants_idx")})
public class Restaurant extends AbstractNamedEntity {

    @NotBlank
    @Column(name = "address", nullable = false)
    @Size(min = 5, max = 250)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull
    private User user;

    public Restaurant() {

    }

    public Restaurant(String name, String address, User user) {
        this(null, name, address, user);
    }

    public Restaurant(Integer id, String name, String address, User user) {
        super(id, name);
        this.address = address;
        this.user=user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
