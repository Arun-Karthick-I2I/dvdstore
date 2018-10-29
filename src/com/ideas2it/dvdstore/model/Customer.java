package com.ideas2it.dvdstore.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.model.User;

/**
 * <p>
 * A Customer Model class to represent the Customer entity
 * It contains all the details related to a customer such as ID, Name, 
 * Mobile Number, Email Id and Address.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class Customer {

    private Integer id;
    private String name;
    private String mobileNumber;
    private String emailId;
    private User user;
    private List<Address> addresses;
    private List<Order> orders = new ArrayList<Order>();

    public Customer() {
    }

    public Customer(String name, String mobileNumber, String emailId, 
            List<Address> addresses) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.addresses = addresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
  
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /** 
     * <p>
     * Compares whether two customers are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param customer
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar customer is present
     *         false  When customers are not similar
     *
     */
    @Override
    public boolean equals(Object customer) {
        if (null == customer) {
            return Boolean.FALSE;
        }

        if (!(customer instanceof Customer)) {
            return Boolean.FALSE;
        }

        if (this == (Customer) customer) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((Customer) customer).id));
    }
}
