package ru.alvisid.testtaskmt;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.GregorianCalendar;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private GregorianCalendar birthDate;

    private User() {
    }

    public User(String name, String email, GregorianCalendar birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
}
