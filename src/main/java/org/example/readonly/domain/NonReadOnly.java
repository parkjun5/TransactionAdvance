package org.example.readonly.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
public class NonReadOnly {

    @Id @GeneratedValue
    private Long id;

    private String name;

    protected NonReadOnly() {
    }

    public NonReadOnly(String name) {
        this.name = name;
    }

    public void setRandomUUID() {
        this.name = UUID.randomUUID().toString();
    }
}
