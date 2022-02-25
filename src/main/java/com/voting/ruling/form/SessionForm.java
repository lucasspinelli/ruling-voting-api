package com.voting.ruling.form;

public class SessionForm {

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
