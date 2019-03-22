package testdata;


import ru.alvisid.testtaskmt.model.User;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A user's test data.
 *
 * @author Glushkov Evgen
 * @version 1.0
 */
public class UserTestData {
    /**
     * Object which represents existing entity in the data base.
     */
    public static final User
            USER_1 = new User(1, "Paul", "paul@yandex.ru", "paul", LocalDate.of(1995, 10, 22)),
            USER_2 = new User(2, "Jhon", "jhon@mail.ru", "jhon", LocalDate.of(2001, 5, 12)),
            USER_3 = new User(3, "Ann", "ann@list.ru", "ann", LocalDate.of(2003, 3, 1)),
            USER_4 = new User(4, "Mary", "mary@gmail.com", "mary", LocalDate.of(2000, 2, 9));

    /**
     * New {@code User} with id-null.
     */
    public static final User
            NEW_USER = new User("NewUser", "newUser@rumbler.ru", "newUser", LocalDate.of(1999, 8, 2));


    /**
     * USER_2 with an updated data.
     *
     * @see UserTestData#USER_2
     */
    public static final User
            UPDATED_USER_2 = new User(2, "updJhon", "updjhon@mail.ru", "updjhon", LocalDate.of(2010, 3, 19));

    /**
     * Checks matching the specified actual parameter's set for the specified expected parameter's set.
     *
     * @param actual   the specified actual parameter's set.
     * @param expected the specified expected parameter's set.
     */
    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertThat(actual).isEqualTo(List.of(expected));
    }

    /**
     * Checks matching the specified actual parameter for the specified expected parameter.
     *
     * @param actual   the specified actual parameter.
     * @param expected the specified expected parameter.
     */
    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
