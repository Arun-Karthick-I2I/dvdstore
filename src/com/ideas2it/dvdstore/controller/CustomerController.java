package com.ideas2it.dvdstore.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.model.User;

/**
 * <p>
 * The {@code CustomerController} class represents a Controller that provides 
 * functionality related to the management of customers. It interacts with the
 * user and satisfies the user needs accordingly.
 * </p>
 *
 */
@Controller
@RequestMapping("customer")
public class CustomerController {  
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * <p>
     * Fetches the necessary details from the customer and creates an entry 
     * for that customer in the database if no such customer exists 
     * and then provides a message else returns only a message.
     * </p>
     *
     */
    @PostMapping("register")
    public ModelAndView register(@ModelAttribute(Constants.LABEL_CUSTOMER) 
            Customer customer, HttpSession session) { 
        ModelAndView modelAndView = new ModelAndView(Constants.CUSTOMER_HOME);
        List<Address> addresses = new ArrayList<Address>();
        User user = new User();
        user.setId((Integer) session.getAttribute(Constants.LABEL_USER_ID));
        customer.setUser(user);
        try {
            if (null != customerService.checkCustomerExistence(customer)) {
                modelAndView = new ModelAndView(Constants.CUSTOMER_FORM, 
                    Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CUSTOMER_ALREADY_EXIST);
            } else if (customerService.registerCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_REGISTER_SUCCESS);
            }
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.CUSTOMER_FORM, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Deletes the details of the particular customer
     * </p>
     *
     */
    @PostMapping("deleteCustomer")
    public ModelAndView deleteCustomer(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.VIEW_CUSTOMER);
        try {
            Customer customer = customerService.searchCustomer(Integer.parseInt(
                request.getParameter(Constants.LABEL_CUSTOMER_ID)));
            if (null != customer) {
                if (customerService.deleteCustomer(customer)) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MESSAGE_CUSTOMER_REMOVE_SUCCESS);
                }
            }
            List<Customer> customers = customerService.getAllCustomers();
            modelAndView.addObject(Constants.LABEL_VIEW_ALL, Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
            
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }    
 
    @GetMapping("edit")
    public ModelAndView editCustomer(HttpSession session) {
        Customer customer = (Customer)session.getAttribute(
            Constants.LABEL_CUSTOMER);
        return new ModelAndView(Constants.CUSTOMER_FORM, 
            Constants.LABEL_CUSTOMER, customer);
    }

    /**
     * <p>
     * Fetches the necessary details from the customer that needs to be updated,
     * updates it and then provides a message else returns only a message.
     * </p>
     *
     */
    @PostMapping("update")
    public ModelAndView updateCustomer(@ModelAttribute("customer") 
            Customer customer, HttpSession session) { 
        ModelAndView modelAndView = new ModelAndView(Constants.CUSTOMER_HOME);
        try {
            Customer existingCustomer = (Customer) 
                session.getAttribute(Constants.LABEL_CUSTOMER);
            customer.setUser(existingCustomer.getUser());
            customer.setAddresses(existingCustomer.getAddresses());
            customer.setOrders(existingCustomer.getOrders());
            existingCustomer = 
                customerService.checkCustomerExistence(customer);
            if (null != existingCustomer) {
                if ((customer.equals(existingCustomer)) &&
                        (customerService.updateCustomer(customer))) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MESSAGE_UPDATE_SUCCESS);
                } else if (!(customer.equals(existingCustomer))) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE,
                        Constants.MESSAGE_CUSTOMER_ALREADY_EXIST);
                }
            } else if (customerService.updateCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_UPDATE_SUCCESS);
            }
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the details of the customer who has logged in and shows his 
     * details such as orders, addresses and provides options to edit details 
     * and to purchase dvds.
     * </p>
     *
     */
    @GetMapping("display")
    public ModelAndView displayCustomer(HttpSession session) { 
        ModelAndView modelAndView = new ModelAndView(Constants.CUSTOMER_HOME);
        try {
            Customer customer = (Customer) session.getAttribute(
                Constants.LABEL_CUSTOMER);
            customer = customerService.searchCustomer(customer.getId());
            session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.INDEX_PAGE, 
                Constants.LABEL_MESSAGE, e.getMessage());
            session.invalidate();
        }
        return modelAndView;
    }
 
    @GetMapping("newAddressForm")
    public ModelAndView showAddressForm() {
        return new ModelAndView(Constants.ADDRESS_FORM, Constants.LABEL_ADDRESS,
            new Address());
    }

    /**
     * <p>
     * Fetches the necessary details for an address and adds it to the 
     * customer.
     * </p>
     *
     */
    @PostMapping("addAddress")
    public ModelAndView addAddress(@ModelAttribute(Constants.LABEL_ADDRESS) 
            Address address, HttpSession session) { 
        ModelAndView modelAndView = new ModelAndView(Constants.REDIRECT + 
            Constants.LABEL_DISPLAY);
        Customer customer = (Customer) 
            session.getAttribute(Constants.LABEL_CUSTOMER);
        address.setCustomerId(customer.getId());
        List<Address> addresses = customer.getAddresses();
        addresses.add(address);
        customer.setAddresses(addresses);
        try {    
            if (customerService.updateCustomer(customer)) {
                session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_ADDRESS_ADD_SUCCESS);
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
            } else {
                session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_ADDRESS_ADD_FAIL);
            }
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Deletes the particular address details from the customer.
     * </p>
     *
     */
    @PostMapping("deleteAddress")
    public ModelAndView deleteAddress(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.CUSTOMER_HOME);
        HttpSession session = request.getSession(Boolean.FALSE);
        Address address = new Address();
        List<Address> addresses = new ArrayList<Address>();
        Customer customer = (Customer) 
            session.getAttribute(Constants.LABEL_CUSTOMER);
        address.setId(Integer.parseInt(request.getParameter(
            Constants.LABEL_ADDRESS_ID)));
        addresses = customer.getAddresses();
        addresses.remove(address);
        customer.setAddresses(addresses);
        try {
            if (customerService.updateCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_ADDRESS_REMOVE_SUCCESS);
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
            } 
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }    

    /**
     * <p>
     * Forwards the Address details from display page to Update DVD Page.
     * </p>
     *
     */
    @PostMapping("editAddress")
    public ModelAndView editAddress(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.ADDRESS_FORM);
        HttpSession session = request.getSession(Boolean.FALSE);
        Integer addressId = Integer.parseInt(request.getParameter(
            Constants.LABEL_ADDRESS_ID));
        Customer customer = 
                (Customer) session.getAttribute(Constants.LABEL_CUSTOMER);
        for(Address address : customer.getAddresses()) {
            if (addressId == address.getId()) {    
                modelAndView.addObject(Constants.LABEL_ADDRESS, address);
                break;
            }
        }
        return modelAndView;
    } 

    /**
     * <p>
     * Fetches the new address details from the customer and updates it in the 
     * database.
     * </p>
     *
     */
    @PostMapping("updateAddress")
    public ModelAndView updateAddress(@ModelAttribute(Constants.LABEL_ADDRESS) 
            Address address, HttpSession session) { 
        ModelAndView modelAndView = new ModelAndView(Constants.CUSTOMER_HOME);
        Customer customer = (Customer)
            session.getAttribute(Constants.LABEL_CUSTOMER);
        List<Address> addresses = customer.getAddresses();
        addresses.remove(address);
        addresses.add(address);
        customer.setAddresses(addresses);
        try {
            if (customerService.updateCustomer(customer)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_UPDATE_SUCCESS);
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_UPDATE_FAIL);
            }
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the list of Dvd Available in that title for purchase and 
     * displays it to the customer.
     * </p>
     *
     */
    @GetMapping("displayDvdByTitle")
    public ModelAndView searchDvdByTitle(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.PURCHASE_DVD);
        try {
            String titlePattern = new StringBuilder(Constants.PERCENT_SYMBOL).
                append(request.getParameter(Constants.LABEL_TITLE)).
                    append(Constants.PERCENT_SYMBOL).toString();
            List<Dvd> dvds = customerService.searchDvdByTitle(titlePattern);
            if (dvds.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_SEARCH_FAIL);
                dvds = customerService.getAllDvds();
            } else {
                modelAndView.addObject(Constants.LABEL_RESET_SEARCH, 
                    Boolean.TRUE);
            }
            List<Category> categories = 
                customerService.getCategories(Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
            modelAndView.addObject(Constants.LABEL_DVDS, dvds);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.CUSTOMER_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
 
    /**
     * <p>
     * Fetches the list of Dvd Available in that category for purchase and 
     * displays it to the customer.
     * </p>
     *
     */
    @GetMapping("displayDvdsByCategory")
    public ModelAndView searchDvdByCategory(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.PURCHASE_DVD);
        try {
            Integer categoryId = Integer.parseInt(request.getParameter(
                Constants.LABEL_CATEGORY_ID));
            Category category = customerService.searchCategory(categoryId);
            List<Dvd> dvds = category.getDvds();
            List<Category> categories = 
                customerService.getCategories(Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
            modelAndView.addObject(Constants.LABEL_CATEGORY_ID, categoryId);
            modelAndView.addObject(Constants.LABEL_DVDS, dvds);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.CUSTOMER_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the list of Dvd Available for purchase and displays it to the
     * customer.
     * </p>
     *
     */
    @GetMapping("displayDvds")
    public ModelAndView displayDvds() { 
        ModelAndView modelAndView = new ModelAndView(Constants.PURCHASE_DVD);
        try {
            List<Dvd> dvds = customerService.getAllDvds();
            List<Category> categories = 
                customerService.getCategories(Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
            modelAndView.addObject(Constants.LABEL_DVDS, dvds);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.CUSTOMER_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the list of Dvds that needs to be purchased along with the
     * delivery address. Provides the dvds along with customer id and address
     * for further processing. It returns the message whether the purchase was 
     * successful or not or the particular dvds that couldn't be ordered from
     * the list of dvds provided by the customer.
     * </p>
     *
     */
    @PostMapping("purchaseDvd")
    public ModelAndView purchaseDvd(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.REDIRECT + 
            "displayDvds");
        HttpSession session = request.getSession(Boolean.FALSE);
        Customer customer = 
                (Customer) session.getAttribute(Constants.LABEL_CUSTOMER);
        Address address = new Address();
        address.setId(Integer.parseInt(request.getParameter(
            Constants.LABEL_ADDRESS_ID)));
        List<Integer> dvdIds = new ArrayList<Integer>();
        for (String dvdId : request.getParameterValues(Constants.LABEL_DVD_ID)){
            dvdIds.add(Integer.parseInt(dvdId));
        }
        try {
            List<Dvd> dvds = customerService.getDvdsById(dvdIds);
            if (null != dvds) {
                dvds = customerService.purchaseDvd(dvds, customer.getId(), 
                    address);
                if (dvds.isEmpty()) {
                    session.setAttribute(Constants.LABEL_MESSAGE,
                        Constants.MESSAGE_ORDER_SUCCESS);
                } else {
                    StringBuilder message = 
                        new StringBuilder(Constants.MESSAGE_ORDER_SUCCESS).
                            append(Constants.MESSAGE_OUT_OF_STOCK);
                    for (Dvd dvd : dvds) {
                        message.append(dvd.getTitle()).
                          append(Constants.SPACE);
                    }
                    session.setAttribute(Constants.LABEL_MESSAGE,
                        message.toString());
                }
            } else {
                session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_ORDER_FAIL);
            }
            customer = customerService.searchCustomer(customer.getId());
            session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.CUSTOMER_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
   
    /**
     * <p>
     * Fetches the id of the order the customer wishes to cancel and cancels 
     * that order for the customer.
     * </p>
     *
     *
     */
    @PostMapping("cancelOrder")
    public ModelAndView cancelOrder(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.CUSTOMER_HOME);
        HttpSession session = request.getSession(Boolean.FALSE);
        Customer customer = 
                (Customer) session.getAttribute(Constants.LABEL_CUSTOMER);
        Integer orderId = Integer.parseInt(
            request.getParameter(Constants.LABEL_ORDER_ID));
        Order order = new Order();
        order.setId(orderId);
        try {
            if (customerService.cancelOrder(order)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_ORDER_CANCEL_SUCESS);
                customer = customerService.searchCustomer(customer.getId());
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_ORDER_CANCEL_FAIL);
            }
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the details of the customer from the database and then displays 
     * it to the user from Admin Perspective.
     * </p>
     *
     */
    @GetMapping("viewCustomer")
    public ModelAndView viewCustomer(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.VIEW_CUSTOMER);
        try {
            Customer customer = customerService.searchCustomer(Integer.parseInt(
                request.getParameter(Constants.LABEL_CUSTOMER_ID)));
            if (null != customer) {
                modelAndView.addObject(Constants.LABEL_VIEW_ALL, Boolean.FALSE);
                modelAndView.addObject(Constants.LABEL_CUSTOMER, customer);
            } 
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the details of all customers with the given name and then 
     * displays it.
     * </p>
     *
     */
    @GetMapping("searchCustomer")
    public ModelAndView displayCustomersByName(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.VIEW_CUSTOMER);
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = customerService.getCustomersByName(request.getParameter(
                Constants.LABEL_CUSTOMER_NAME));
            modelAndView.addObject(Constants.LABEL_VIEW_ALL, Boolean.TRUE);
            if (customers.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_SEARCH_CUSTOMER_FAIL);
                customers = customerService.getAllCustomers();
            } else {
                modelAndView.addObject(Constants.LABEL_RESET_SEARCH, 
                    Boolean.TRUE);
            }
            modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the details of all customers and then displays it.
     * </p>
     *
     */
    @GetMapping("displayAllCustomers")
    public ModelAndView displayAllCustomers() { 
        ModelAndView modelAndView = new ModelAndView(Constants.VIEW_CUSTOMER);
        List<Customer> customers = new ArrayList<Customer>();
        try {
            customers = customerService.getAllCustomers();
            modelAndView.addObject(Constants.LABEL_VIEW_ALL, Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_CUSTOMERS, customers);
            
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
}
