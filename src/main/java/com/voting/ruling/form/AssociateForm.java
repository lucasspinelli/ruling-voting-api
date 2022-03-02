package com.voting.ruling.form;

import com.voting.ruling.model.Associate;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociateForm {
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Associate Name")
    private String name;
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "Associate Last Name")
    private String lastName;
    @ApiModelProperty(value = "Associate CPF")
    private String cpf;

    public AssociateForm(String name, String lastName, String cpf) {

        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public AssociateForm() {

    }

    public Associate toAssociate() {
        return new Associate(this.cpf, this.name, this.lastName);
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
