package service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.alvisid.testtaskmt.model.User;
import ru.alvisid.testtaskmt.repository.util.JpaUtil;
import ru.alvisid.testtaskmt.service.UserService;
import ru.alvisid.testtaskmt.util.exceptions.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static ru.alvisid.testtaskmt.util.ValidationUtil.getRootCause;
import static testdata.UserTestData.*;

/**
 * A user's tests.
 *
 * @author Glushkov Evgen
 * @version 1.0
 * @since 2019.03.16
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ActiveProfiles("postgres")
public class UserServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    static {
        // needed only for java.util.logging (postgres driver)
        SLF4JBridgeHandler.install();
    }

    /**
     * User's service.
     */
    @Autowired
    protected UserService service;

    /**
     * {@code CacheManager} for clearing cache.
     */
    @Autowired
    private CacheManager cacheManager;

    /**
     * Current {@code JpaUtil} object.
     */
    @Autowired
    private JpaUtil jpaUtil;

    /**
     * Executes before every test,
     * clears cache if {@code service} is {@code Cached},
     * clears 2nd level Hibernate cache
     * and activate enum's dictionaries if enums is not activated.
     *
     * @see JpaUtil#clear2ndLevelHibernateCache()
     */
    @Rule
    public ExternalResource beforeRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            jpaUtil.clear2ndLevelHibernateCache();
            cacheManager.getCache("users").clear();
        }
    };

    /**
     * Checks a matching the actual created value from DB to the expected created value from {@code testData}:
     * add a new object to the DB;
     * checks matching the actual list of all objects to the expected list of all objects after creating from {@code testData}.
     */
    @Test
    public void create() {
        User expected = new User(NEW_USER);
        User actual = service.create(expected);

        expected.setId(actual.getId());

        assertMatch(service.getAll(), USER_3, USER_2, USER_4, expected, USER_1);
    }

    /**
     * Checks the {@code IllegalArgumentException} and exception's message
     * when old user (id is not null) create trying.
     */
    @Test
    public void createOldObject() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(User.class.getSimpleName() + " must be new (has id = null)!");
        service.create(USER_1);
    }

    /**
     * Checks the {@code IllegalArgumentException} and exception's message
     * when null object create trying.
     */
    @Test
    public void createNullObject() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(User.class.getSimpleName() + " must not be null");

        service.create(null);
    }

    /**
     * Checks a matching the actual updated value from DB to the expected updated value from {@code testData}:
     * update an exiting object in the DB;
     * checks matching the actual list of all objects to the expected list of all objects from {@code testData}.
     */
    @Test
    public void update() {
        User expected = new User(UPDATED_USER_2);
        service.update(expected);
        User actual = service.get(expected.getId());

        assertMatch(actual, expected);
    }

    /**
     * Checks the {@code NotFoundException} when there are no updated user's id in the DB.
     */
    @Test
    public void updateNotFound() {
        int notFoundId = 10000;
        User updated = new User(UPDATED_USER_2);
        updated.setId(notFoundId);

        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + notFoundId);

        service.update(updated);
    }

    /**
     * Checks the {@code IllegalArgumentException} and exception's message
     * when null object update trying.
     */
    @Test
    public void updateNullObject() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(User.class.getSimpleName() + " must not be null");

        service.update(null);
    }

    /**
     * Checks deletion object from DB by the specified id:
     * deletes object by specified id;
     * checks matching the actual list of all objects to the expected list of all objects after deletion from {@code testData}.
     */
    @Test
    public void delete() {
        int deleteId = USER_4.getId();

        service.delete(deleteId);

        assertMatch(service.getAll(), USER_3, USER_2, USER_1);
    }

    /**
     * Checks the {@code NotFoundException} when there are no deleted object's id in the DB.
     */
    @Test
    public void deleteNotFound() {
        int notFoundId = 10000;

        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + notFoundId);

        service.delete(notFoundId);
    }

    /**
     * Checks matching the actual gotten value from DB to the expected gotten value from {@code testData}.
     */
    @Test
    public void get() {
        User expectedUser = new User(USER_4);
        User actualUser = service.get(expectedUser.getId());

        assertMatch(actualUser, expectedUser);
    }

    /**
     * Checks the {@code NotFoundException} when there are no user's id to getting in the DB.
     */
    @Test
    public void getNotFound() {
        int notFoundId = 10000;

        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + notFoundId);

        service.get(notFoundId);
    }

    /**
     * Checks the actual gotten list of the all users from DB to
     * the expected gotten list of the all users from {@code testData}.
     */
    @Test
    public void getAll() {
        List<User> actualAllUsers = service.getAll();

        assertMatch(actualAllUsers, USER_3, USER_2, USER_4, USER_1);
    }

    /**
     * Checks the matching root exception to expected exception when objects with invalid
     * values is created.
     */
    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new User(null, "newUser@rumbler.ru", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("", "newUser@rumbler.ru", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("     ", "newUser@rumbler.ru", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("Q", "newUser@rumbler.ru", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(
                        "More than 100 symbols" +
                                "More than 100 symbols" +
                                "More than 100 symbols" +
                                "More than 100 symbols" +
                                "More than 100 symbols", "newUser@rumbler.ru", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", null, "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "     ", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "invalidEmail", "newUser",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "newUser@rumbler.ru", null,
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "newUser@rumbler.ru", "",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "newUser@rumbler.ru", "     ",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "newUser@rumbler.ru", "QQ",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "newUser@rumbler.ru",
                        "More than 100 symbols" +
                                "More than 100 symbols" +
                                "More than 100 symbols" +
                                "More than 100 symbols" +
                                "More than 100 symbols",
                        LocalDate.of(1999, 8, 2))),
                ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("NewUser", "newUser@rumbler.ru", "newUser",
                        null)),
                ConstraintViolationException.class);
    }

    /**
     * Checks root exception's class of the thrown exception to the expected exception's class.
     *
     * @param runnable       the runnable object which throws exception.
     * @param exceptionClass the expected exception class.
     * @param <T>            the type of the expected exception.
     */
    public <T extends Throwable> void validateRootCause(Runnable runnable, Class<T> exceptionClass) {
        try {
            runnable.run();
            Assert.fail("Expected: " + exceptionClass.getName());
        } catch (Exception exc) {
            Assert.assertThat(getRootCause(exc), instanceOf(exceptionClass));
        }
    }
}
