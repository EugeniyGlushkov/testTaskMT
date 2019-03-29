package ru.alvisid.testtaskmt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A user entity.
 *
 * @author Glushkov Evgen
 * @version 1.0
 * @since 2019.15.03
 */

@Entity
@Table(name = "users", uniqueConstraints =
@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")
)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Access(AccessType.FIELD)
public class User {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    public static final String ROLE = "ROLE_USER";

    /**
     * The specific identifier for each entity in a database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the user.
     * Min value is 2 characters, max value is 100 characters.
     * Must be non null and has least one non space symbol.
     */
    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;


    /**
     * The user's email.
     */
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    private String email;

    /**
     * The user's password.
     */
    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    /**
     * The user's day of birth.
     */
    @Column(name = "birth_date", nullable = false)
    @NotNull
    private LocalDate birthDate;

    /**
     * Returns the specific id.
     *
     * @return the specific id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the user's name.
     *
     * @return the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the user's email.
     *
     * @return the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the user's password.
     *
     * @return the user's password.
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user's day of birth.
     *
     * @return the user's day of birth.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the specified id.
     *
     * @param id the specified id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Sets the specified user's name.
     *
     * @param name the specified user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the specified user's email.
     *
     * @param email the specified user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the specified user's password.
     *
     * @param password the specified user's password.
     */
    public void setPassword(String password) {
        //https://www.baeldung.com/spring-security-5-default-password-encoder
        this.password = PASSWORD_ENCODER.encode(password);
    }

    /**
     * Sets the specified user's day of birth.
     *
     * @param birthDate the specified user's day of birth.
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Returns {@code true} if id is null.
     *
     * @return {@code true} if id is null.
     */
    @JsonIgnore
    public boolean isNew() {
        return Objects.isNull(this.id);
    }

    /**
     * Initializes a newly created object with null fields.
     *
     * @see User#User(String, String, String, LocalDate)
     * @see User#User(Integer, String, String, String, LocalDate)
     * @see User#User(User)
     */
    public User() {
    }

    /**
     * Constructs a <b>User</b> object and
     * sets name, email, password and birthDate fields.
     *
     * @param name      the specified user's name.
     * @param email     the specified user's email.
     * @param password  the specified user's password.
     * @param birthDate the specified user's date of birth.
     * @see User#User()
     * @see User#User(Integer, String, String, String, LocalDate)
     * @see User#User(User)
     */
    public User(String name, String email, String password, LocalDate birthDate) {
        this(null, name, email, password, birthDate);
    }

    /**
     * Constructs a <b>User</b> object and
     * sets id, name, email, password and birthDate fields.
     *
     * @param id        the specified id.
     * @param name      the specified user's name.
     * @param email     the specified user's email.
     * @param password  the specified user's password.
     * @param birthDate the specified user's date of birth.
     * @see User#User()
     * @see User#User(String, String, String, LocalDate)
     * @see User#User(User)
     */
    public User(Integer id, String name, String email, String password, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    /**
     * Constructs new object which is copy of the specified object.
     * new object is equals to specified object.
     *
     * @param user the specified object to copying.
     * @see User#User()
     * @see User#User(String, String, String, LocalDate)
     * @see User#User(Integer, String, String, String, LocalDate)
     */
    public User(User user) {
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthDate());
    }

    /**
     * Compares this object to the specified object.
     * The result is {@code true} if and only if the argument is not null
     * and is the same class as this object,
     * and that contains the same id value as this object.
     *
     * @param o the specified object.
     * @return {@code true} if the objects are the same; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }

        User that = (User) o;

        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code for this object.
     *
     * @return the hash code for this object.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns a String object representing this <b>User</b> object.
     *
     * @return the String object representing this <b>User</b> object.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
