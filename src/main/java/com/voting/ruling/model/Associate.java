package com.voting.ruling.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="associate")
public class Associate {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String cpf;

    @Column
    private String name;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "associate")
    private List<Vote> votes;

    public String getName() {
        return name;
    }

    public Associate(String cpf, String name, String lastName) {
        this.cpf = cpf;
        this.name = name;
        this.lastName = lastName;
    }

    public Associate() {
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
