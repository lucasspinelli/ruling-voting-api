package com.voting.ruling.form;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VoteForm {

    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Associate CPF that will vote")
    private String cpf;

    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Vote value receive : 'yes' 'no' 'n' 'y' 'sim' 'nao' 'não' 's' upper and lower case is received as well ")
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
