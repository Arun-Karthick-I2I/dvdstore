package com.ideas2it.dvdstore.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.common.enums.Role.USER_ROLES;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.service.UserService;
import com.ideas2it.dvdstore.service.impl.UserServiceImpl;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.User;

/**
 * <p>
 * The {@code DvdController} class represents a Controller that provides 
 * functionality related to the management of users. It interacts with the 
 * user and satisfies the user needs accordingly.
 * </p>
 *
 */
@Controller
@RequestMapping("user")
public class UserController { 
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }    

    /**
     * <p>
     * Creates an entry for the user if no such user exists else returns a 
     * message accordingly.
     * </p>
     *
     */
    @PostMapping("register")
    public String register(HttpServletRequest request, ModelMap model) {
        User user = new User();
        user.setUserName(request.getParameter(Constants.LABEL_USERNAME));
        user.setPassword(request.getParameter(Constants.LABEL_PASSWORD));
        user.setRole(USER_ROLES.CUSTOMER);
        try {  
            if (userService.register(user)) {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_REGISTER_SUCCESS);
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_REGISTER_FAIL);
            }        
        } catch(DvdStoreException e) {
            model.addAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return Constants.INDEX_PAGE;
    }

    /**
     * <p>
     * Requests the view handler to show the registration form page.
     * </p>
     * 
     */
    @GetMapping("registrationForm")
    public String registrationForm(ModelMap model) {
        model.addAttribute(Constants.LABEL_REGISTER, Boolean.TRUE);
        return Constants.INDEX_PAGE;
    }

    /**
     * <p>
     * Checks the username, displays the rest of the form if the username is 
     * available else displays an alert and asks for another username.
     * </p>
     *
     */
    @PostMapping("checkUserNameAvailability")
    public String checkUserNameAvailability(HttpServletRequest request,
            ModelMap model) {
        String userName = request.getParameter(Constants.LABEL_USERNAME);
        try {
            if (userService.checkUserNameAvailability(userName)) {
                model.addAttribute(Constants.LABEL_USERNAME, 
                    userName);
                model.addAttribute(Constants.LABEL_COMPLETE_REGISTRATION,
                    Boolean.TRUE);
            } else {
                model.addAttribute(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_USERNAME_ALREADY_TAKEN);
                request.setAttribute(Constants.LABEL_REGISTER, Boolean.TRUE);
            }
        } catch (DvdStoreException e) {
            model.addAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return Constants.INDEX_PAGE;
    }

    /**
     * <p>
     * Checks the username and password and logins the user if the credentials
     * are correct.
     * </p>
     *
     */
    @PostMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.TRUE);
        session.setMaxInactiveInterval(Constants.SESSION_MAX_INACTIVE_INTERVAL);
        User user = new User();
        user.setUserName(request.getParameter(Constants.LABEL_USERNAME));
        user.setPassword(request.getParameter(Constants.LABEL_PASSWORD));
        user.setRole(USER_ROLES.valueOf(request.getParameter(
            Constants.LABEL_ROLE)));
        ModelAndView modelAndView = new ModelAndView(Constants.INDEX_PAGE);
        try {
            Boolean isAuthenticated = userService.validateUser(user);
            if (null == isAuthenticated) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_CUSTOMER_NOT_FOUND_REGISTER);
            } else if (isAuthenticated) {
                session.setAttribute(Constants.LABEL_USER_ID, user.getId());
                session.setAttribute(Constants.LABEL_ROLE, user.getRole());
                if (USER_ROLES.CUSTOMER == user.getRole()) {
                    loginCustomer(request, user.getId(), modelAndView);
                } else {
                    modelAndView.setViewName(Constants.ADMIN_HOME);
                }
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_INVALID_CREDENTIALS);
            }
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Logs in the customer and goes to Customer HomePage if the customer
     * has previously completed the registration else asks them to complete the
     * form.
     * </p>
     *
     */
    private void loginCustomer(HttpServletRequest request, Integer userId, 
            ModelAndView modelAndView) throws DvdStoreException { 
        HttpSession session = request.getSession(Boolean.FALSE);
        Customer customer = userService.searchCustomer(userId);
        if (null == customer) {
            modelAndView.setViewName(Constants.CUSTOMER_FORM);
            modelAndView.addObject(Constants.LABEL_CUSTOMER, new Customer());
        } else {
            session.setAttribute(Constants.LABEL_TODAY, 
                Date.valueOf(LocalDate.now()));
            modelAndView.setViewName(Constants.CUSTOMER_HOME);
            session.setAttribute(Constants.LABEL_CUSTOMER, customer);
        }
    }

    /**
     * <p>
     * Invalidates the session and logs the customer out of the page. 
     * </p>
     *
     */
    @PostMapping("logout")
    public String logout(HttpSession session, ModelMap model) { 
        if (null != session) {
            session.invalidate();
        }
        model.addAttribute(Constants.LABEL_MESSAGE, 
            Constants.MESSAGE_LOGGED_OUT);
        return Constants.INDEX_PAGE;
    }

    /**
     * <p>
     * Redirects to the correct home page based on the user role.
     * </p>
     *
     */
    @GetMapping("home")
    public String goHome(HttpServletRequest request) { 
        HttpSession session = request.getSession(Boolean.FALSE);
        USER_ROLES role = (USER_ROLES) session.getAttribute(
            Constants.LABEL_ROLE);
        if (USER_ROLES.ADMIN == role) {
            return Constants.ADMIN_HOME;
        } else {
            return Constants.CUSTOMER_HOME;
        }
    }
}
