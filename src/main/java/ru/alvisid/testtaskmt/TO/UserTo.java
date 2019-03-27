package ru.alvisid.testtaskmt.TO;

import java.time.LocalDate;
import java.util.Objects;

public class UserTo {
    private Integer id;
    private String name;
    private String email;
    private String pas;
    private LocalDate birthDate;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String pas, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pas = pas;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTo userTo = (UserTo) o;
        return Objects.equals(id, userTo.id) &&
                Objects.equals(name, userTo.name) &&
                Objects.equals(email, userTo.email) &&
                Objects.equals(pas, userTo.pas) &&
                Objects.equals(birthDate, userTo.birthDate);
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pas='" + pas + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
