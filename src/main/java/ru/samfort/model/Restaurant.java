package ru.samfort.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "address"}, name = "restaurants_idx")})
public class Restaurant extends AbstractNamedEntity {

    @NotBlank
    @Column(name = "address", nullable = false)
    @Size(min = 5, max = 250)
    private String address;

    public Restaurant() {

    }

    public Restaurant(String address, String name) {
        this(null, address, name);
    }

    public Restaurant(Integer id, String address, String name) {
        super(id, name);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
