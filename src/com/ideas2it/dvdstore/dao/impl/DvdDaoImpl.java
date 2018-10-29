package com.ideas2it.dvdstore.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;    
import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.DvdDao;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.session.DvdStoreSessionManager;

/**
 * <p>
 * The {@code DvdDaoImpl} class implements the DvdDao
 * interface. It provides defenitions for the functions declared in
 * the interface. This class is responsible for performing manipulations
 * in the Dvd Store.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.common.Constants
 * @see    com.ideas2it.dvdstore.dao.DvdDao
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Dvd
 *
 */
public class DvdDaoImpl implements DvdDao {
    private static SessionFactory sessionFactory = 
        (DvdStoreSessionManager.getInstance()).getSessionFactory();

    /**
     * @{inheritDoc}
     */
    public Boolean addDvd(Dvd dvd) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(dvd);  
            transaction.commit();  

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_ADD).append(Constants.SPACE).
                append(Constants.LABEL_TITLE).
                append(Constants.COLON_SYMBOL).append(dvd.getTitle()).
                append(Constants.SPACE).append(Constants.LABEL_TYPE).
                append(Constants.COLON_SYMBOL).append(dvd.getType()).
                append(Constants.SPACE).append(Constants.LABEL_LANGUAGE).
                append(Constants.COLON_SYMBOL).append(dvd.getLanguage()).
                toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }
        
    /**
     * @{inheritDoc}
     */
    public Boolean removeDvd(Integer dvdId) throws DvdStoreException { 
        Session session = null;
        Transaction transaction = null;
        Dvd dvd = searchDvdById(dvdId, Boolean.TRUE);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Boolean message = (null != dvd);
            if (message) {
                dvd.setIsAvailable(Boolean.FALSE);
                session.update(dvd);
            }
            transaction.commit();  

            return message;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_REMOVE).append(Constants.SPACE).
                append(Constants.LABEL_DVD_ID).
                append(Constants.COLON_SYMBOL).append(dvdId).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }

    /**
     * @{inheritDoc}
     */
    public Boolean restoreDvd(Integer dvdId) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        Dvd dvd = searchDvdById(dvdId, Boolean.FALSE);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Boolean message = (null != dvd);
            if (message) {
                dvd.setIsAvailable(Boolean.TRUE);
                session.update(dvd);
            }
            transaction.commit();  

            return message;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_RESTORE).append(Constants.SPACE).
                append(Constants.LABEL_DVD_ID).
                append(Constants.COLON_SYMBOL).append(dvdId).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }

    /**
     * @{inheritDoc}
     */
    public Boolean updateDvd(Dvd dvd) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(dvd);  
            transaction.commit();  

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_UPDATE).append(Constants.SPACE).
                append(Constants.LABEL_DVD_ID).
                append(Constants.COLON_SYMBOL).append(dvd.getId()).
                append(Constants.SPACE).append(Constants.LABEL_TITLE).
                append(Constants.COLON_SYMBOL).append(dvd.getTitle()).
                append(Constants.SPACE).append(Constants.LABEL_TYPE).
                append(Constants.COLON_SYMBOL).append(dvd.getType()).
                append(Constants.SPACE).append(Constants.LABEL_LANGUAGE).
                append(Constants.COLON_SYMBOL).append(dvd.getLanguage()).
                toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }
   
    /**
     * @{inheritDoc}
     */
    public Dvd searchDvd(Dvd dvd) throws DvdStoreException {        
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = criteriaBuilder.createQuery(
                Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            Predicate[] predicates = new Predicate[3];
            predicates[0] = criteriaBuilder.equal(
                root.get(Constants.LABEL_TITLE), dvd.getTitle());
            predicates[1] = criteriaBuilder.equal(
                root.get(Constants.LABEL_LANGUAGE), dvd.getLanguage());
            predicates[2] = criteriaBuilder.equal(
                root.get(Constants.LABEL_TYPE), dvd.getType());

            criteriaQuery.select(root).where(predicates);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH).append(Constants.SPACE).
                append(Constants.LABEL_TITLE).
                append(Constants.COLON_SYMBOL).append(dvd.getTitle()).
                append(Constants.SPACE).append(Constants.LABEL_TYPE).
                append(Constants.COLON_SYMBOL).append(dvd.getType()).
                append(Constants.SPACE).append(Constants.LABEL_LANGUAGE).
                append(Constants.COLON_SYMBOL).append(dvd.getLanguage()).
                toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } 
    }

    /**
     * @{inheritDoc}
     */
    public Dvd searchDvdById(Integer dvdId, Boolean status) throws 
            DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = criteriaBuilder.createQuery(
                Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = criteriaBuilder.equal(root.get(Constants.LABEL_ID),
                dvdId);
            predicates[1] = criteriaBuilder.equal(
                root.get(Constants.LABEL_ISAVAILABLE), status);
            criteriaQuery.select(root).where(predicates);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH).append(Constants.SPACE).
                append(Constants.LABEL_DVD_ID).
                append(Constants.COLON_SYMBOL).append(dvdId).
                toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } 
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> searchDvdByTitle(String title) throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = criteriaBuilder.createQuery(
                Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = criteriaBuilder.like(
                root.get(Constants.LABEL_TITLE), title);
            predicates[1] = criteriaBuilder.equal(
                root.get(Constants.LABEL_ISAVAILABLE), Boolean.TRUE);
            criteriaQuery.select(root).where(predicates);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH).append(Constants.SPACE).
                append(Constants.LABEL_TITLE).
                append(Constants.COLON_SYMBOL).append(title).
                toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } 
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> getDvdsById(List<Integer> dvdIds, Boolean status) throws 
            DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = criteriaBuilder.createQuery(
                Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = root.get(Constants.LABEL_ID).in(dvdIds);
            predicates[1] = criteriaBuilder.equal(root.get(
                Constants.LABEL_ISAVAILABLE), status);
            criteriaQuery.select(root).where(predicates);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_GET_DVDS).append(Constants.SPACE).
                append(Constants.LABEL_DVDS).
                append(Constants.COLON_SYMBOL).append(dvdIds).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        } 
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> getDvds(Boolean status) throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Dvd> criteriaQuery = criteriaBuilder.createQuery(
                Dvd.class);
            Root<Dvd> root = criteriaQuery.from(Dvd.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal( 
                root.get(Constants.LABEL_ISAVAILABLE), status));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.MESSAGE_EXCEPTION_GET_DVDS, e);
            throw new DvdStoreException(Constants.MESSAGE_EXCEPTION_GET_DVDS);
        } 
    }
}
