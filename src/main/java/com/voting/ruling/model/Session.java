package com.voting.ruling.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Entity
public class Session {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ruling ruling;

    @Column
    private Date creationDate = new Date();

    @Column
    private Double expiration;

    @Column
    private boolean active = true;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> vote;

    public Session(Double expiration) {
        if (isNull(expiration))
            expiration = 60000.0;
        this.expiration = expiration;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ruling getRuling() {
        return ruling;
    }

    public void setRuling(Ruling ruling) {
        this.ruling = ruling;
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

    public List<Vote> getVote() {
        return vote;
    }

    public void setVote(List<Vote> vote) {
        this.vote = vote;
    }

    public String getResult() {
        if (isNull(this.vote) || vote.isEmpty()) return "There is no vote for this session";
        Integer yes = this.vote.stream().filter(voteYes -> voteYes.getVote().equals("SIM")).collect(Collectors.toList()).size();
        Integer no = this.vote.stream().filter(voteNo -> voteNo.getVote().equals("NÃO")).collect(Collectors.toList()).size();
        return String.format("Sim : %d , Não : %d", yes, no);
    }
}
