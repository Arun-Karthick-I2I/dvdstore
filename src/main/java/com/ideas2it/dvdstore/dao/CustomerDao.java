package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Customer;

/**
 * <p>
 * The {@code CustomerDao} interface provides the operations that can
 * be performed by the customers to store into or retrieve from a database.
 * It provides the basic operations such as register, view, find and update 
 * customers of a DVD Store.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Customer
 *
 */
public interface CustomerDao {

    /**
     * <p>
     * Adds a Customer into the Database
     * </p>
     *
     * @param customer
     *        Customer who has to be added to the Database
     *
     * @return message
     *         Returns true if Customer details is successfully created  
     *         and added to the database else Returns false. 
     */    
    Boolean addCustomer(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Deletes a Customer from the Database
     * </p>
     *
     * @param customer
     *        Customer who has to be deleted from the Database
     *
     * @return message
     *         Returns true if Customer details is successfully created  
     *         and added to the database else Returns false. 
     */    
    Boolean deleteCustomer(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Updates the Customer details in the Database
     * </p>
     *
     * @param customer
     *        Customer which has to be updated in the Database
     *
     * @return message
     *         Returns true if Customer is successfully updated  
     *         in the database else Returns false. 
     */
    Boolean updateCustomer(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Searches through the database based on mobile number and email id
     * </p>
     * 
     * @param customer
     *        Customer who presence has to be searched.
     *
     * @return customer    
     *         Returns the customer with the either the same mobile number or
     *         email id exists
     *         Returns null if no such customer exist.
     */
    Customer checkCustomerExistence(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Searches through the database based on user ID.
     * </p>
     * 
     * @param userId
     *        User ID of the customer whose details have to be found.
     *
     * @return customer    
     *         Returns the customer with the desired user id if it exist
     *         Returns null if no such customer exist.
     */
    Customer getCustomer(Integer userId) throws DvdStoreException;

    /**
     * <p>
     * Searches through the database based on Customer ID.
     * </p>
     * 
     * @param customerId
     *        ID of the customer whose details have to be found.
     *
     * @return customer    
     *         Returns the customer with the desired id if it exist
     *         Returns null if no such customer exist.
     */
    Customer searchCustomer(Integer customerId) throws DvdStoreException;

    /**
     * <p>
     * Provides the list of customers present in the database.
     * </p>
     *
     * @param customerName
     *        name of customers whose details have to be found.
     *
     * @return customers    
     *         Returns the list of all customers of the DVD Store.
     */
    List<Customer> getCustomersByName(String customerName) throws 
        DvdStoreException;

    /**
     * <p>
     * Provides the list of customers present in the database.
     * </p>
     * 
     * @return customers    
     *         Returns the list of all customers of the DVD Store.
     */
    List<Customer> getCustomers() throws DvdStoreException;
}
