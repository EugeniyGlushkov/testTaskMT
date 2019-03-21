package ru.alvisid.testtaskmt.repository.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Miscellaneous utility test methods.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 * @since 2019.03.15
 */
public class JpaUtil {
    @PersistenceContext
    EntityManager em;

    /**
     * Clears 2nd level Hibernate cache.
     */
    public void clear2ndLevelHibernateCache() {
        Session session = (Session) em.getDelegate();
        SessionFactory sessionFactory = session.getSessionFactory();
        sessionFactory.getCache().evictAllRegions();
    }
}
