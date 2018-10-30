package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Order;

/**
 * <p>
 * The {@code OrderDao} interface provides order related operations that can
 * be performed to a Dvd Store. It provides the basic operations such as add, 
 * remove and find orders from the dvdstore.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Order
 *
 */
public interface OrderDao {
    /**
     * <p>
     * Adds Order details of a Customer into the Database
     * </p>
     *
     * @param orders
     *        List of customer orders which needs to be added to the Database
     *
     * @return message
     *         Returns true if order details is successfully created  
     *         and added to the database else Returns false. 
     */    
    Boolean addOrders(List<Order> orders) throws DvdStoreException;

    /**
     * <p>
     * Deletes an order from the database.
     * </p>
     *
     * @param  order
     *         Order which needs to be removed 
     *
     * @return true    If the order has been successfully deleted
     *         false   If order is not deleted 
     */
    Boolean deleteOrder(Order order) throws DvdStoreException;

    /**
     * <p>
     * Searched for the Order with the specified ID. 
     * </p>
     *
     * @param  orderId
     *         ID of the order to be searched for 
     *
     * @return order  If the order has been successfully found
     *         null   If no such order is found
     */
    Order searchOrder(Integer orderId) throws DvdStoreException;

    /**
     * <p>
     * Provides the list of orders present in the database.
     * </p>
     * 
     * @return orders    
     *         Returns the list of all orders of the DVD Store.
     */
    List<Order> getAllOrders() throws DvdStoreException;
}
