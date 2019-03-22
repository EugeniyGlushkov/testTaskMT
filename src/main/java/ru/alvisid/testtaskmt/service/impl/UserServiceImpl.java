package ru.alvisid.testtaskmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.alvisid.testtaskmt.model.User;
import ru.alvisid.testtaskmt.repository.UserRepository;
import ru.alvisid.testtaskmt.service.UserService;
import ru.alvisid.testtaskmt.util.exceptions.NotFoundException;

import java.util.List;

import static ru.alvisid.testtaskmt.util.ValidationUtil.*;

/**
 * Implementation of the {@code UserService} interface.
 *
 * @author Glushkov Evgen
 * @version 1.0
 * @see UserService
 * @since 2019.03.15
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * The specific repository implementation
     */
    private UserRepository repository;

    /**
     * Constructs new {@code UserServiceImpl} and set a specified user's repository implementation.
     *
     * @param repository the specified user's repository implementation.
     */
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates and saves a given user in the data base.
     *
     * @param user the user to create.
     * @return the created user.
     */
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, User.class.getSimpleName() + " must not be null");

        if (!user.isNew()) {
            throw new IllegalArgumentException(user.getClass().getSimpleName() + " must be new (has id = null)!");
        }

        return repository.save(user);
    }

    /**
     * Updates an existing in the data base user.
     *
     * @param user the user to update.
     * @throws NotFoundException if there aren't updated user in the data base.
     */
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) throws NotFoundException {
        Assert.notNull(user, User.class.getSimpleName() + " must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    /**
     * Deletes the user by specified id.
     *
     * @param id the specified id of a user.
     * @throws NotFoundException if the user with the specified id isn't found.
     */
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    /**
     * Returns the user by the specified id.
     *
     * @param id the specified id of the user to get.
     * @return the user with the specified id.
     * @throws NotFoundException if the user with the specified id isn't found.
     */
    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    /**
     * Returns the list with all users.
     *
     * @return the list with all users.
     */
    @Cacheable("users")
    @Override
    public List <User> getAll() {
        return repository.getAll();
    }

    /**
     * Returns a user by given email.
     *
     * @param email the specified email.
     * @return the user by given email.
     * @throws NotFoundException if the user with the specified email isn't found.
     */
    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }
}
