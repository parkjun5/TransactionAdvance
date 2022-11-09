package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
public class ReadOnly {
    @Id @GeneratedValue
    private Long id;

    private String name;

    protected ReadOnly() {
    }

    public ReadOnly(String name) {
        this.name = name;
    }

    public void setRandomUUID() {
        this.name = UUID.randomUUID().toString();
    }
}
