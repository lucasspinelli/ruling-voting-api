package com.voting.ruling.form;

import com.voting.ruling.model.Associate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociateForm {
    @NotNull @NotBlank
    private String name;
    @NotNull @NotBlank
    private String lastName;
    private String cpf;

    public AssociateForm(String name, String lastName, String cpf) {

        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public Associate toAssociate() {
        return new Associate(this.cpf, this.name, this.lastName);
    }

    public AssociateForm() {

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
}
