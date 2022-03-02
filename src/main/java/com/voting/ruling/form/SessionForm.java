package com.voting.ruling.form;

import io.swagger.annotations.ApiModelProperty;

public class SessionForm {

    @ApiModelProperty(value = "Time for this session end in milliseconds")
    private Double expiration;

    public SessionForm(Double expiration) {
        this.expiration = expiration;
    }

    public Double getExpiration() {
        return expiration;
    }

    public void setExpiration(Double expiration) {
        this.expiration = expiration;
    }

    public SessionForm() {

    }
}
