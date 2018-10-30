package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.User;

/**
 * <p>
 * The {@code UserService} interface provides the operations
 * that can be done by a user. It provides the basic functions needed such
 * as register, login and update user details.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Customer
 * @see    com.ideas2it.dvdstore.model.User
 *
 */
public interface UserService {

    /**
     * <p>
     * Creates an entry for the user and provides a corresponding message.
     * </p>
     *
     * @param user
     *        User details which needs to be added to the Database
     *
     * @return message
     *         Returns true if User details is successfully added  
     *         to the database else Returns false. 
     */    
    Boolean register(User user) throws DvdStoreException;

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
    Customer searchCustomer(Integer userId) throws DvdStoreException;

    /**
     * <p>
     * Searches for the presence of a particular userName and returns 
     * message accordingly.
     * </p>
     * 
     * @param customer
     *        Customer who presence has to be searched.
     *
     * @return true    If userName is available
     *         false   If userName is already taken by another user.
     */
    Boolean checkUserNameAvailability(String userName) throws DvdStoreException;

    /**
     * <p>
     * Fetches the user details and validates it with the one present in the 
     * database.
     * </p>
     * 
     * @param user
     *        User object which needs to be validated.
     *
     * @return true    If username and password matches.
     *         false   If password doesn't match
     *         null    If no such user exists for that role.
     */
    Boolean validateUser(User user) throws DvdStoreException;
}
