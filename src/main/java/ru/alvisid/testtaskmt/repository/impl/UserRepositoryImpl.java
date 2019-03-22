package ru.alvisid.testtaskmt.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.testtaskmt.model.User;
import ru.alvisid.testtaskmt.repository.UserRepository;
import ru.alvisid.testtaskmt.repository.datajpa.CrudUserRepository;

import java.util.List;
import java.util.Objects;

/**
 * DataJpa implementation of the UserRepository.
 *
 * @author Glushkov Evgen
 * @version 1.0
 * @since 2019.03.15
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    /**
     * Sort by name.
     */
    private static final Sort SORT_NAME =
            new Sort(Sort.Direction.ASC, "name");

    /**
     * An interface for user repository which extends JpaRepository.
     */
    private final CrudUserRepository crudRepository;

    /**
     * Constructs a new UserRepositoryImpl with the specified CrudUserRepository.
     *
     * @param crudRepository the specified <em>CrudUserRepository</em>.
     */
    @Autowired
    public UserRepositoryImpl(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Saves or updates a given user.
     * Returns null if there aren't updating value in the data base.
     *
     * @param user an user to save.
     * @return the saved or updated user,
     * null - if there aren't updating value in the data base.
     */
    @Override
    public User save(User user) {
        if (user.isNew() || !Objects.isNull(get(user.getId()))) {
            return crudRepository.save(user);
        }

        return null;
    }

    /**
     * Deletes an user by given id.
     *
     * @param id id of the user that must be deleted.
     * @return true - the entity is deleted, false - the entity isn't found.
     */
    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    /**
     * Returns a user by given id.
     *
     * @param id id of the user to return.
     * @return the user by given id.
     */
    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    /**
     * Returns all users sorted with specific sort.
     *
     * @return list of all users.
     * @see UserRepositoryImpl#SORT_NAME
     */
    @Override
    public List <User> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    /**
     * Returns a user by given email.
     *
     * @param email the specified email.
     * @return the user by given email.
     */
    @Override
    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }
}
