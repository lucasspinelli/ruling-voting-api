package com.voting.ruling.model;

import javax.persistence.*;

@Entity
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String cpf;

    @Column
    private String name;

    @Column
    private String lastName;


    public Associate(String cpf, String name, String lastName) {
        this.cpf = cpf;
        this.name = name;
        this.lastName = lastName;
    }

    public Associate() {
    }

    public String getName() {
        return name;
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
