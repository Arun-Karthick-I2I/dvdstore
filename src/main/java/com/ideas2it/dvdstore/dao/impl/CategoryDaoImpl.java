package com.ideas2it.dvdstore.dao.impl;

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
import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.session.DvdStoreSessionManager;

/**
 * <p>
 * The {@code CategoryDaoImpl} class implements CategoryDao interface. 
 * It provides category related operations that can be performed in a Dvd Store.
 * It has the basic operations such as add, remove and find a category from the
 * Dvd Store. 
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.common.Constants
 * @see    com.ideas2it.dvdstore.connection.DvdDBConnector
 * @see    com.ideas2it.dvdstore.dao.CategoryDao
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Dvd
 * @see    com.ideas2it.dvdstore.model.Category
 * @see    com.ideas2it.dvdstore.session.DvdStoreSessionManager
 */
public class CategoryDaoImpl implements CategoryDao{
    private static SessionFactory sessionFactory = 
        (DvdStoreSessionManager.getInstance()).getSessionFactory();

    /**
     * @{inheritDoc}
     */
    public Boolean addCategory(Category category) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(category);  
            transaction.commit();  

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_ADD).append(Constants.SPACE).
                append(Constants.LABEL_CATEGORY_NAME).
                append(Constants.COLON_SYMBOL).append(category.getName()).
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
    public Boolean removeCategory(Integer categoryId) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        Category category = findCategory(categoryId, Boolean.TRUE);
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Boolean message = (null != category);
            if (message) {
                category.setIsAvailable(Boolean.FALSE);
                session.update(category);
            }
            transaction.commit();  

            return message;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_REMOVE).append(Constants.SPACE).
                append(Constants.LABEL_CATEGORY_ID).
                append(Constants.COLON_SYMBOL).append(categoryId).toString();
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
    public Boolean restoreCategory(Integer categoryId) 
            throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        Category category = findCategory(categoryId, Boolean.FALSE); 
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Boolean message = (null != category);
            if (message) {
                category.setIsAvailable(Boolean.TRUE);
                session.update(category);
            }
            transaction.commit();  

            return message;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_RESTORE).append(Constants.SPACE).
                append(Constants.LABEL_CATEGORY_ID).
                append(Constants.COLON_SYMBOL).append(categoryId).toString();
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
    public Boolean updateCategory(Category category) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(category);  
            transaction.commit();  

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_UPDATE).append(Constants.SPACE).
                append(Constants.LABEL_CATEGORY_ID).
                append(Constants.COLON_SYMBOL).append(category.getId()).
                append(Constants.SPACE).append(Constants.LABEL_CATEGORY_NAME).
                append(Constants.COLON_SYMBOL).append(category.getName()).
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
    public Category findCategory(Integer categoryId, Boolean status)
            throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                criteriaBuilder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = criteriaBuilder.equal(root.get(Constants.LABEL_ID),
                categoryId);
            predicates[1] = criteriaBuilder.equal(root.get(
                Constants.LABEL_ISAVAILABLE), status);
            criteriaQuery.select(root).where(predicates);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH).append(Constants.SPACE).
                append(Constants.LABEL_CATEGORY_ID).
                append(Constants.COLON_SYMBOL).append(categoryId).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public Category findCategoryByName(String categoryName) throws
            DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                criteriaBuilder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_NAME), categoryName));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH).append(Constants.SPACE).
                append(Constants.LABEL_CATEGORY_NAME).
                append(Constants.COLON_SYMBOL).append(categoryName).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public List<Category> getCategories(List<Integer> categoryIds, 
            Boolean status) throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                criteriaBuilder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = root.get(Constants.LABEL_ID).in(categoryIds);
            predicates[1] = criteriaBuilder.equal(root.get(
                Constants.LABEL_ISAVAILABLE), status);
            criteriaQuery.select(root).where(predicates);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_GET_CATEGORIES).
                append(Constants.SPACE).append(Constants.LABEL_CATEGORIES).
                append(Constants.COLON_SYMBOL).append(categoryIds).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public List<Category> getCategories(Boolean status) throws 
            DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Category> criteriaQuery = 
                criteriaBuilder.createQuery(Category.class);
            Root<Category> root = criteriaQuery.from(Category.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal( 
                root.get(Constants.LABEL_ISAVAILABLE), status));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.MESSAGE_EXCEPTION_GET_CATEGORIES, e);
            throw new DvdStoreException(
                Constants.MESSAGE_EXCEPTION_GET_CATEGORIES);
        }
    }
}
