package com.voting.ruling.Model;


import javax.persistence.*;

@Entity
public class Vote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private boolean voteValue;

    @ManyToOne
    @JoinColumn(name = "id_associate")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "id_ruling")
    private Ruling ruling;

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Ruling getRuling() {
        return ruling;
    }

    public void setRuling(Ruling ruling) {
        this.ruling = ruling;
    }

    public boolean isVoteValue() {
        return voteValue;
    }

    public void setVoteValue(boolean voteValue) {
        this.voteValue = voteValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
