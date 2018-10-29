package com.ideas2it.dvdstore.service;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Order;

/**
 * <p>
 * The {@code OrderService} interface provides the operations that are
 * related to an order. It provides the basic functions such as 
 * adding, removing , searching and displaying orders.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Order
 *
 */
public interface OrderService {

    /**
     * <p>
     * Creates an Order with the specified details. 
     * </p>
     *
     * @param  orders
     *         List of Orders which needs to be placed
     *
     * @return true  If the order has been successfully placed
     *         false If the order is not placed properly
     */
    Boolean addOrders(List<Order> orders) throws DvdStoreException;

    /**
     * <p>
     * Deletes the specified order
     * </p>
     *
     * @param  order
     *         Order which needs to be deleted 
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
     * Provides the list of orders present in the dvd store.
     * </p>
     * 
     * @return orders    
     *         Returns the list of all orders of the DVD Store.
     */
    List<Order> getAllOrders() throws DvdStoreException;
}
