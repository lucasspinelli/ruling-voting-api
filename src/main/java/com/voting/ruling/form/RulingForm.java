package com.voting.ruling.form;

import com.voting.ruling.model.Ruling;
import io.swagger.annotations.ApiModelProperty;

import static java.util.Objects.isNull;

public class RulingForm {

    @ApiModelProperty(value = "Ruling description")
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
