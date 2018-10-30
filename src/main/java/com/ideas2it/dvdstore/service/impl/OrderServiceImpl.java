package com.ideas2it.dvdstore.service.impl;

import java.util.List;

import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.dao.impl.OrderDaoImpl;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.service.OrderService;

/**
 * <p>
 * The {@code OrderServiceImpl} implements the OrderService interface. 
 * It provides defenitions for the functions declared in the OrderService
 * Interface.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;   

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * @{inheritDoc}
     */
    public Boolean addOrders(List<Order> orders) throws DvdStoreException {
        return orderDao.addOrders(orders);
    }

    /**
     * @{inheritDoc}
     */
    public Boolean deleteOrder(Order order) throws DvdStoreException {
        return orderDao.deleteOrder(order);

    }

    /**
     * @{inheritDoc}
     */
    public Order searchOrder(Integer orderId) throws DvdStoreException {
        return orderDao.searchOrder(orderId);

    }

    /**
     * @{inheritDoc}
     */
    public List<Order> getAllOrders() throws DvdStoreException {
        return orderDao.getAllOrders();
    }
}
