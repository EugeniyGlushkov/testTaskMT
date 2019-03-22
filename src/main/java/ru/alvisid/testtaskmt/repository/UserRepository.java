package ru.alvisid.testtaskmt.repository;

import ru.alvisid.testtaskmt.model.User;

import java.util.List;

/**
 * The generalized functional for user's repository.
 *
 * @author Glushkov Evgen
 * @version 1.0
 * @since 2019.03.15
 */
public interface UserRepository {
    /**
     * Saves or updates a given user.
     *
     * @param user the user to save or update.
     * @return a saved or update user,
     * null - if there aren't updated user in the data base.
     */
    User save(User user);

    /**
     * Deletes the user by specified id.
     *
     * @param id the specified id of the deleted user.
     * @return {@code true} - the entity is deleted, {@code false} - the entity isn't found.
     */
    boolean delete(int id);

    /**
     * Returns the user with the specified id.
     *
     * @param id the specified id of the user to get.
     * @return the user with the specified id,
     * null - if there aren't the user with the specified id in the DB.
     */
    User get(int id);

    /**
     * Returns the list with all users.
     *
     * @return the list with all users.
     */
    List <User> getAll();

    /**
     * Returns the user by given email.
     *
     * @param email the specified email.
     * @return the user by given email.
     */
    User getByEmail(String email);
}
