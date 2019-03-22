package ru.alvisid.testtaskmt.service;

import ru.alvisid.testtaskmt.model.User;
import ru.alvisid.testtaskmt.util.exceptions.NotFoundException;

import java.util.List;

/**
 * The specific functional for the user's service.
 *
 * @author Glushkov Evgen
 * @version 1.0
 * @since 2019.03.15
 */
public interface UserService {
    /**
     * Creates and saves a given user in the data base.
     *
     * @param user the user to create.
     * @return the created user.
     */
    User create(User user);

    /**
     * Updates an existing in the data base user.
     *
     * @param user the user to update.
     * @throws NotFoundException if there aren't updated user in the data base.
     */
    void update(User user) throws NotFoundException;

    /**
     * Deletes the user by specified id.
     *
     * @param id the specified id of a user.
     * @throws NotFoundException if the user with the specified id isn't found.
     */
    void delete(int id) throws NotFoundException;

    /**
     * Returns the user by the specified id.
     *
     * @param id the specified id of the user to get.
     * @return the user with the specified id.
     * @throws NotFoundException if the user with the specified id isn't found.
     */
    User get(int id) throws NotFoundException;

    /**
     * Returns the list with all users.
     *
     * @return the list with all users.
     */
    List <User> getAll();

    /**
     * Returns a user by given email.
     *
     * @param email the specified email.
     * @return the user by given email.
     * @throws NotFoundException if the user with the specified email isn't found.
     */
    User getByEmail(String email) throws NotFoundException;
}
