package com.ideas2it.dvdstore.model;

import java.sql.Date;

import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * A Order Model class to represent the Customer_Order Entity 
 * It contains order details such as orderId, customerId, dvdId, orderDate
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class Order {
    private Integer id;
    private Integer customerId;
    private Dvd dvd;
    private Address address;
    private Date orderDate;

    public Order() {
    }

    public Order(Integer customerId, Dvd dvd, Address address, Date orderDate) {
        this.customerId = customerId;
        this.dvd = dvd;
        this.address = address;
        this.orderDate = orderDate;
    }

    public Order(Integer id , Integer customerId, Dvd dvd, Address address,
            Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.dvd = dvd;
        this.address = address;
        this.orderDate = orderDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
 
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Dvd getDvd() {
        return dvd;
    }
 
    public void setDvd(Dvd dvd) {
        this.dvd = dvd;
    }

    public Address getAddress() {
        return address;
    }
 
    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /** 
     * <p>
     * Compares whether two orders are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param order
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When the two orders are similar
     *         false  When the orders are not similar
     *
     */
    @Override
    public boolean equals(Object order) {
        if (null == order) {
            return Boolean.FALSE;
        }

        if (!(order instanceof Order)) {
            return Boolean.FALSE;
        }

        if (this == (Order) order) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((Order) order).id));
    }

}
