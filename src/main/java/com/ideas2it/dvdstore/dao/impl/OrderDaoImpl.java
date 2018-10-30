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
import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.session.DvdStoreSessionManager;

/**
 * <p>
 * The {@code OrderDaoImpl} class implements OrderDao interface. It provides 
 * order related operations that can be performed in a Dvd Store. 
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.common.Constants
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.dao.OrderDao
 * @see    com.ideas2it.dvdstore.logger.DvdStoreLogger
 * @see    com.ideas2it.dvdstore.model.Order
 * @see    com.ideas2it.dvdstore.session.DvdStoreSessionManager
 *
 */
public class OrderDaoImpl implements OrderDao {
    private static final String QUERY_GET_ORDERS = "FROM Order";

    private static SessionFactory sessionFactory = 
        (DvdStoreSessionManager.getInstance()).getSessionFactory();


    /**
     * @{inheritDoc}
     */
    public Boolean addOrders(List<Order> orders) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            for (Order order : orders) {
                session.save(order);  
            }
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            StringBuilder exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_ADD).append(Constants.SPACE).
                append(Constants.LABEL_CUSTOMER_ID).
                append(Constants.COLON_SYMBOL).
                append(orders.get(0).getCustomerId()).append(Constants.SPACE);
                for (Order order : orders) {
                    exceptionMessage.append(Constants.LABEL_DVD_ID).
                    append(Constants.COLON_SYMBOL).
                    append(order.getDvd().getId());
                }
            DvdStoreLogger.error(exceptionMessage.toString(), e);
            throw new DvdStoreException(exceptionMessage.toString());
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }

    /**
     * @{inheritDoc}
     */
    public Boolean deleteOrder(Order order) throws DvdStoreException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(order);  
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_REMOVE).append(Constants.SPACE).
                append(Constants.LABEL_CUSTOMER_ID).
                append(Constants.COLON_SYMBOL).append(order.getCustomerId()).
                append(Constants.SPACE).append(Constants.LABEL_ORDER_ID).
                append(Constants.COLON_SYMBOL).append(order.getId()).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(Constants.MESSAGE_EXCEPTION_REMOVE);
        } finally {
            if (null != session) {
                session.close();
            }
        }
    }

    /**
     * @{inheritDoc}
     */
    public Order searchOrder(Integer orderId) throws DvdStoreException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(
                Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal
                (root.get(Constants.LABEL_ID), orderId));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            String exceptionMessage = new StringBuilder(
                Constants.MESSAGE_EXCEPTION_GET_ORDERS).
                append(Constants.SPACE).append(Constants.LABEL_ORDER_ID).
                append(Constants.COLON_SYMBOL).append(orderId).toString();
            DvdStoreLogger.error(exceptionMessage, e);
            throw new DvdStoreException(Constants.MESSAGE_EXCEPTION_GET_ORDERS);
        } 
    }    

    /**
     * @{inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Order> getAllOrders() throws DvdStoreException {
        List<Order> orders = new ArrayList<Order>();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(QUERY_GET_ORDERS).list();
        } catch (HibernateException e) {
            DvdStoreLogger.error(Constants.MESSAGE_EXCEPTION_GET_ORDERS, e);
            throw new DvdStoreException(
                Constants.MESSAGE_EXCEPTION_GET_ORDERS);
        }
    }
}
