package ru.alvisid.testtaskmt.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.testtaskmt.model.User;

import java.util.List;
import java.util.Optional;

/**
 * JpaRepository interface for user.
 *
 * @author Glushkov Evgen.
 * @version 1.0
 * @since 2019.03.15
 */
//@RepositoryRestResource(exported = false)
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository <User, Integer> {
    /**
     * Saves or updates a given user.
     * If there are a given object in the data base then the given object will be update.
     * If there aren't a given object in the data base then a new object
     * with new id (data base set default value) will be saved.
     *
     * @param user a user to save.
     * @return the saved user.
     */
    @Override
    @Transactional
    User save(User user);


    /**
     * Deletes a user by given id.
     *
     * @param user id of the user that must be deleted.
     * @return amount of the deleted entities.
     */
    @Override
    @Transactional
    void delete(User user);

    @Override
    @Transactional
    void deleteById(Integer integer);

    /*@Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);*/

    /**
     * Returns a container with a user by the given id inside.
     *
     * @param integer id of the user to return.
     * @return a container with an user by the given id inside.
     */
    @Override
    Optional <User> findById(Integer integer);


    /**
     * Returns all users sorted with a given sort.
     *
     * @param sort the sort for users list.
     * @return list of all users sorted with a given sort.
     */
    @Override
    List <User> findAll(Sort sort);

    /**
     * Returns an user by the given email.
     *
     * @param email the specified email.
     * @return the user by the given email.
     */
    User getByEmail(String email);
}
