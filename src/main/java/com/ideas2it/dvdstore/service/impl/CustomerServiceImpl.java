package com.ideas2it.dvdstore.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.dao.impl.CustomerDaoImpl;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.OrderService;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.DvdService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;
import com.ideas2it.dvdstore.service.impl.DvdServiceImpl;

/**
 * <p>
 * The {@code CustomerServiceImpl} implements the CustomerService 
 * interface. It provides defenitions for the functions declared in the
 * CustomerService Interface.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.dao.CustomerDao
 * @see    com.ideas2it.dvdstore.dao.impl.CustomerDaoImpl
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Customer
 * @see    com.ideas2it.dvdstore.model.Order
 * @see    com.ideas2it.dvdstore.model.Dvd
 * @see    com.ideas2it.dvdstore.service.CustomerService
 * @see    com.ideas2it.dvdstore.service.DvdService
 * @see    com.ideas2it.dvdstore.service.impl.DvdServiceImpl
 *
 */
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;
    private DvdService dvdService;
    private CategoryService categoryService;
    private OrderService orderService;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setDvdService(DvdService dvdService) {
        this.dvdService = dvdService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @{inheritDoc}
     */
    public Boolean registerCustomer(Customer customer) throws 
            DvdStoreException {
        return customerDao.addCustomer(customer);
    }

    /**
     * @{inheritDoc}
     */
    public Customer checkCustomerExistence(Customer customer) throws 
            DvdStoreException {
        return customerDao.checkCustomerExistence(customer);
    }

    /**
     * @{inheritDoc}
     */
    public Customer searchCustomer(Integer customerId) throws 
            DvdStoreException {
        return customerDao.searchCustomer(customerId);  
    }

    /**
     * @{inheritDoc}
     */
    public Customer searchCustomerByUserId(Integer userId) throws
            DvdStoreException {
        return customerDao.getCustomer(userId);
    }

    /**
     * @{inheritDoc}
     */
    public Boolean deleteCustomer(Customer customer) throws DvdStoreException {    
        return customerDao.deleteCustomer(customer);
    }

    /**
     * @{inheritDoc}
     */
    public Boolean updateCustomer(Customer customer) throws DvdStoreException {    
        return customerDao.updateCustomer(customer);
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> searchDvdByTitle(String title) throws DvdStoreException {
        return dvdService.searchDvdByTitle(title);
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> getDvdsById(List<Integer> dvdIds) throws 
            DvdStoreException {
        return dvdService.getDvdsById(dvdIds, Boolean.TRUE);
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> purchaseDvd(List<Dvd> dvds, Integer customerId, 
            Address address) throws DvdStoreException {
        Order order = new Order();
        List<Order> orders = new ArrayList<Order>();
        List<Dvd> outOfStockDvds = dvdService.updateDvdCopies(dvds);
        if (!outOfStockDvds.isEmpty()) {
            dvds.removeAll(outOfStockDvds);
        }
        for (Dvd dvd: dvds) {
            order = new Order(customerId, dvd, address,
                Date.valueOf(LocalDate.now()));
            orders.add(order);
        }
        orderService.addOrders(orders);
        return outOfStockDvds;
    }
    
    /**
     * @{inheritDoc}
     */
    public Boolean cancelOrder(Order order) throws DvdStoreException {
        return orderService.deleteOrder(order);
    }  

    /**
     * @{inheritDoc}
     */
    public List<Dvd> getAllDvds() throws DvdStoreException {
        return dvdService.fetchDvds(Boolean.TRUE);
    }    

    /**
     * @{inheritDoc}
     */
    public List<Category> getCategories(Boolean status) throws 
            DvdStoreException {
        return categoryService.getCategories(status);
    }

    /**
     * @{inheritDoc}
     */
    public Category searchCategory(Integer categoryId) throws 
            DvdStoreException {
        return categoryService.searchCategory(categoryId, Boolean.TRUE);
    }

    /**
     * @{inheritDoc}
     */
    public List<Customer> getCustomersByName(String customerName) throws
             DvdStoreException {
        return customerDao.getCustomersByName(customerName);
    }    

    /**
     * @{inheritDoc}
     */
    public List<Customer> getAllCustomers() throws DvdStoreException {
        return customerDao.getCustomers();
    }    
}
