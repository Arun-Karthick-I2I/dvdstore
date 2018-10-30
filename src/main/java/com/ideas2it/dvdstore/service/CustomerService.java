package com.ideas2it.dvdstore.service;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Order;

/**
 * <p>
 * The {@code CustomerService} interface provides the operations
 * that can be done by a customer. It provides the basic functions needed such
 * as register, find, view and update customer details.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Customer
 * @see    com.ideas2it.dvdstore.model.Dvd
 * @see    com.ideas2it.dvdstore.model.Category
 *
 */
public interface CustomerService {

    /**
     * <p>
     * Fetches the details from user and creates an entry or account for the
     * customer if no such account exists
     * </p>
     *
     * @param  customer
     *         Customer Object with details of a new customer for whom an 
     *         entry has to be created
     *
     * @return true  If a new account is created for the customer successfully.
     *         false If there is a problem in creating the account.
     */ 
    Boolean registerCustomer(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Checks for the presence of customers with the given mobile number or 
     * email id
     * <p>
     *
     * @param  customer
     *         Customer with mobile number and email id whose presence needs
     *         to be checked
     *
     * @return Customer  If a customer with either same email or mobile number
     *                   exists
     *         null  If no such customer exists
     */
    Customer checkCustomerExistence(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Searches for the customer with the given mobile number
     * </p>
     * 
     * @param customerId
     *        ID of the customer whose details are being searched for
     *
     * @return customer
     *         Returns the customer with the desired mobile number if it exist
     *         Returns null if no such customer exist.
     */
    Customer searchCustomer(Integer customerId) throws DvdStoreException;

    /**
     * <p>
     * Fetches the details from user and checks for the correctness from the 
     * database and returns message accordingly.
     * </p>
     *
     * @param  userId
     *         User ID of the Customer who has to be logged in.
     *
     * @return customer  If the customer details are correct.
     *         null      If no such customer exists.
     */ 
    Customer searchCustomerByUserId(Integer userId) throws DvdStoreException;

    /**
     * <p>
     * Deletes the corresponding Customer details
     * </p>
     *
     * @param customer
     *        Customer whose details needs to be deleted.
     *
     * @return true  If the customer object is deleted successfully.
     *         false If the customer object is not deleted successfully.
     */
    Boolean deleteCustomer(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Updates the contents of the corresponding Customer.
     * </p>
     *
     * @param customer
     *        Customer whose details needs to be updated.
     *
     * @return true  If the details are updated successfully.
     *         false If the details are not updated successfully.
     */
    Boolean updateCustomer(Customer customer) throws DvdStoreException;

    /**
     * <p>
     * Searches for the list of DVDs with the specific title
     * </p>
     * 
     * @param title
     *        Title whose details are being searched for.
     *
     * @return dvds    
     *         Returns the list of DVDs with the desired criteria if they exist
     *         Returns null if no such DVDs exist.
     */
    List<Dvd> searchDvdByTitle(String title) throws DvdStoreException;

    /**
     * <p>
     * Searches for the DVD with the specified ID
     * </p>
     * 
     * @param dvdIds
     *        ID of the DVDs whose details are being searched for.
     *
     * @return dvds    
     *         Returns the list of dvds with the desired ids if they exist
     *         Returns null if no such DVDs exist.
     */
    List<Dvd> getDvdsById(List<Integer> dvdIds) throws DvdStoreException;

    /**
     * <p>
     * Purchases the specified DVD under the specified customer
     * </p>
     * 
     * @param dvds
     *        List of Dvd which needs to be purchased.
     * @param customerId
     *        ID of the Customer who trys to purchase the DVD.
     * @param address
     *        Address where the order needs to be delivered
     *
     * @return null  If all the dvds were purchased successfully.
     *         dvds  List of dvds whose purchase failed
     */
    List<Dvd> purchaseDvd(List<Dvd> dvds, Integer customerId, Address address) 
        throws DvdStoreException;

    /**
     * <p>
     * Cancels the order for the corresponding customer
     * </p>
     * 
     * @param order
     *        Order which needs to be cancelled
     *
     * @return true  If order cancelled successfully.
     *         false If order couldn't be cancelled
     */
    Boolean cancelOrder(Order order) throws DvdStoreException;

    /**
     * <p>
     * It fetches the list of dvds available in the store.
     * </p>
     *
     * @return dvds
     *         Returns the entire DVD List
     */
    List<Dvd> getAllDvds() throws DvdStoreException;

    /**
     * <p>
     * It fetches the list of dvdCategories available.
     * </p>
     *
     * @param  status
     *         Status of the Categories which have to retrieved
     *
     * @return categories
     *         Returns the list of available categories
     */
    List<Category> getCategories(Boolean status) throws DvdStoreException;

    /**
     * <p>
     * Requests the findCategory function to find category with the specific ID.
     * </p>
     * 
     * @param categoryID
     *        Category ID whose details have to be found.
     *
     * @return Category    
     *         Returns the Category with the desired ID if it exist
     *         Returns null if no such category exist.
     */
    Category searchCategory(Integer categoryId) throws DvdStoreException;

    /**
     * <p>
     * Provides the  list of all customers with the specified name from the
     * DVDStore along with their order details.
     * </p>
     * 
     * @param customerName
     *        name of the customers whose details have to be found.
     *
     * @return customers    
     *         Returns the list of customers with their order details. 
     */
    List<Customer> getCustomersByName(String customerName) throws 
        DvdStoreException;

    /**
     * <p>
     * Provides the total list of all customers of the DVDStore along with 
     * their order details.
     * </p>
     * 
     * @return customers    
     *         Returns the list of customers with their order details. 
     */
    List<Customer> getAllCustomers() throws DvdStoreException;
}
