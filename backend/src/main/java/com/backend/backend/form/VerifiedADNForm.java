package com.backend.backend.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

public class VerifiedADNForm {
    @NotNull
	@Size(min=6, max=6)
	private List<String> id;

	@NotNull
	private Boolean correct;

    public List<String> getId() {
        return this.id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getIdAsString() {
        return this.id.toString();
    }

    public String toString() {
        return "VerifiedADN{" +
                "sequence=" + this.id +
                ", correct='" + this.correct +
                '}';
    }
}