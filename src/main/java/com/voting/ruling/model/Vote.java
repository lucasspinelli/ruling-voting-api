package com.voting.ruling.model;


import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String vote;

    @ManyToOne
    @JoinColumn(name = "id_associate")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;

    public Vote() {
    }

    public Vote(String vote, Associate associate, Session session) {
        this.vote = vote;
        this.associate = associate;
        this.session = session;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

}
