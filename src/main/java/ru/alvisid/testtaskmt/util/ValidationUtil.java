package ru.alvisid.testtaskmt.util;


import ru.alvisid.testtaskmt.util.exceptions.NotFoundException;

import java.util.Objects;

/**
 * This class consists exclusively of static methods wich helps
 * to check the passed values.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @since 2019.03.15
 */
public class ValidationUtil {

    /**
     * Throws the NotFoundException if a found is false.
     *
     * @param found   shows availability the entity in the DB.
     * @param message message for thrown NotFoundException.
     * @throws NotFoundException the exception is thrown when entity is not found.
     * @see ValidationUtil#checkNotFound(Object, String)
     */
    public static void checkNotFound(boolean found, String message) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + message);
        }
    }

    /**
     * Returns a checked object, throws the NotFoundException if the checked object is null.
     *
     * @param object  the checked object.
     * @param message message for thrown NotFoundException.
     * @param <T>     the type of the checked object.
     * @return the checked object.
     * @throws NotFoundException if the checked object is null.
     * @see ValidationUtil#checkNotFound(boolean, String)
     */
    public static <T> T checkNotFound(T object, String message) {
        checkNotFound(!Objects.isNull(object), message);
        return object;
    }

    /**
     * Throws the NotFoundException if a found is false.
     *
     * @param found shows availability the entity in the DB.
     * @param id    specific id of the entity.
     * @throws NotFoundException the exception is thrown when entity is not found.
     * @see ValidationUtil#checkNotFound(Object, String)
     */
    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    /**
     * Returns a checked object, throws the NotFoundException if the checked object is null.
     *
     * @param object the checked object.
     * @param id     specific id of the entity.
     * @param <T>    the type of the checked object.
     * @return the checked object.
     * @throws NotFoundException if the checked object is null.
     * @see ValidationUtil#checkNotFoundWithId(boolean, int)
     */
    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    /**
     * Returns a root of the specified {@code Throwable} object.
     *
     * @param throwable the specified {@code Throwable} object.
     * @return the root of the specified {@code Throwable} object.
     */
    public static Throwable getRootCause(Throwable throwable) {
        Throwable result = throwable;
        Throwable cause;

        while (null != (cause = result.getCause()) && cause != result) {
            result = cause;
        }

        return result;
    }

    // never instantiated
    private ValidationUtil() {
    }
}
