package com.voting.ruling.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Ruling {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private Date creationDate = new Date();

    @Column
    private double expiration = 1;

    @Column
    private boolean active;

    @OneToMany(mappedBy = "ruling", cascade = CascadeType.ALL)
    private List<Vote> vote;

    public List<Vote> getVote() {
        return vote;
    }

    public void setVote(List<Vote> vote) {
        this.vote = vote;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getExpiration() {
        return expiration;
    }

    public void setExpiration(double expiration) {
        this.expiration = expiration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
