package com.voting.ruling.form;

import com.voting.ruling.model.Ruling;

import static java.util.Objects.isNull;

public class RulingForm {

    private String description;

    public RulingForm() {
    }

    public RulingForm(String description) {

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ruling toRuling() {
        return isNull(description) ? new Ruling() : new Ruling(description);
    }
}
