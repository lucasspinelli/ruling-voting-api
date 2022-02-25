package com.voting.ruling.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VoteForm {

    @NotNull @NotBlank
    private String cpf;

    @NotNull @NotBlank
    private String vote;

    public VoteForm() {
    }

    public VoteForm(String cpf, String vote) {
        this.cpf = cpf;
        this.vote = vote;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
