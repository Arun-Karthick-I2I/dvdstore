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
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.session.DvdStoreSessionManager;

/**
 * <p>
 * The {@code CustomerDaoImpl} class implements CustomerDao interface. 
 * It provides the operations that can be performed related to a database. 
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.common.Constants
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.dao.CustomerDao
 * @see    com.ideas2it.dvdstore.model.Customer
 *
 */
public class CustomerDaoImpl implements CustomerDao {
    private static final String QUERY_GET_CUSTOMERS = "FROM Customer";

    private static SessionFactory sessionFactory = 
        (DvdStoreSessionManager.getInstance()).getSessionFactory();

    /**
     * @{inheritDoc}
     */
    public Boolean addCustomer(Customer customer) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(customer);  
            transaction.commit();  

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_REGISTER).append(Constants.SPACE).
                append(Constants.LABEL_MOBILE_NUMBER).
                append(Constants.COLON_SYMBOL).
                append(customer.getMobileNumber()).append(Constants.SPACE).
                append(Constants.LABEL_EMAIL_ID).append(Constants.COLON_SYMBOL).
                append(customer.getEmailId()).toString();
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
    public Customer checkCustomerExistence(Customer customer) 
            throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = 
                criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            Predicate mobilePredicate = criteriaBuilder.equal(
                root.get(Constants.LABEL_MOBILE_NUMBER), 
                    customer.getMobileNumber());
            Predicate emailIdPredicate = criteriaBuilder.equal( 
                root.get(Constants.LABEL_EMAIL_ID), customer.getEmailId());
            criteriaQuery.select(root).where(criteriaBuilder.or(mobilePredicate,
                emailIdPredicate));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_REGISTER).append(Constants.SPACE).
                append(Constants.LABEL_MOBILE_NUMBER).
                append(Constants.COLON_SYMBOL).
                append(customer.getMobileNumber()).append(Constants.SPACE).
                append(Constants.LABEL_EMAIL_ID).append(Constants.COLON_SYMBOL).
                append(customer.getEmailId()).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public Boolean deleteCustomer(Customer customer) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.delete(customer);  
            transaction.commit();  

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_REMOVE).append(Constants.SPACE).
                append(Constants.LABEL_CUSTOMER_ID).
                append(Constants.COLON_SYMBOL).
                append(customer.getId()).toString();
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
    public Customer getCustomer(Integer userId) throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(
                Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_USER), userId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH_CUSTOMER).
                append(Constants.SPACE).append(Constants.LABEL_USER_ID).
                append(Constants.COLON_SYMBOL).
                append(userId).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public Customer searchCustomer(Integer customerId)
            throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(
                Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_ID), customerId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH).append(Constants.SPACE).
                append(Constants.LABEL_CUSTOMER_ID).
                append(Constants.COLON_SYMBOL).
                append(customerId).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    public Boolean updateCustomer(Customer customer) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(customer);  
            transaction.commit();  

            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_UPDATE).append(Constants.SPACE).
                append(Constants.LABEL_CUSTOMER_ID).
                append(Constants.COLON_SYMBOL).append(customer.getId()).
                append(Constants.SPACE).append(Constants.LABEL_MOBILE_NUMBER).
                append(Constants.COLON_SYMBOL).
                append(customer.getMobileNumber()).append(Constants.SPACE).
                append(Constants.LABEL_EMAIL_ID).append(Constants.COLON_SYMBOL).
                append(customer.getEmailId()).toString();
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
    public List<Customer> getCustomersByName(String customerName) 
            throws DvdStoreException {
        List<Customer> customers = new ArrayList<Customer>();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(
                Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(
                root.get(Constants.LABEL_NAME), customerName));
            return session.createQuery(criteriaQuery).list();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_SEARCH).append(Constants.SPACE).
                append(Constants.LABEL_CUSTOMER_NAME).
                append(Constants.COLON_SYMBOL).
                append(customerName).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(exceptionMessage);
        }
    }

    /**
     * @{inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Customer> getCustomers() throws DvdStoreException {
        List<Customer> customers = new ArrayList<Customer>();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(QUERY_GET_CUSTOMERS).list();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.MESSAGE_EXCEPTION_GET_CUSTOMERS, e);
            throw new DvdStoreException(
                Constants.MESSAGE_EXCEPTION_GET_CUSTOMERS);
        }
    }
}
