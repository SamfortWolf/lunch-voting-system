package ru.samfort.voting.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"},  name = "votes_idx")})
public class Vote extends AbstractBaseEntity {

    @ManyToOne
    private User user;

    @Column(name = "date", nullable = false)
    private Date date;
}
