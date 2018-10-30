package com.ideas2it.dvdstore.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.session.DvdStoreSessionManager;

/**
 * <p>
 * The {@code UserDaoImpl} class implements UserDao interface. It provides 
 * the user related operations that can be performed in a Dvd Store. 
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.common.Constants
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.dao.UserDao
 * @see    com.ideas2it.dvdstore.model.User
 *
 */
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory = 
         (DvdStoreSessionManager.getInstance()).getSessionFactory();

    /**
     * @{inheritDoc}
     */
    public Boolean addUser(User user) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_REGISTER).
                append(Constants.SPACE).append(Constants.LABEL_USERNAME).
                append(Constants.COLON_SYMBOL).append(user.getUserName()).
                toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public User getUser(String userName) throws DvdStoreException {
        try (Session session = sessionFactory.openSession();) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(
                User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_USERNAME), userName));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH_CUSTOMER).
                append(Constants.SPACE).append(Constants.LABEL_USERNAME).
                append(Constants.COLON_SYMBOL).append(userName).
                toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }
}
