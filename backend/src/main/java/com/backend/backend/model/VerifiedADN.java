package com.backend.backend.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class VerifiedADN implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private String id;
    @Column(nullable = false, updatable = false)
    private Boolean correct;

    public VerifiedADN() {}

    public VerifiedADN(String id, Boolean correct) {
        this.id = id;
        this.correct = correct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "VerifiedADN{" +
                "sequence=" + id +
                ", correct='" + correct +
                '}';
    }
}