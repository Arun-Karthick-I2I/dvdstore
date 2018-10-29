package com.ideas2it.dvdstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.service.OrderService;
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;

/**
 * <p>
 * The {@code OrderController} class represents a Controller that provides 
 * functionality related to the management of orders. It interacts with the 
 * user and satisfies the user needs accordingly.
 * </p>
 *
 */
@Controller
@RequestMapping("order")
public class OrderController {
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }    
    
    /**
     * <p>
     * Fetches the details of the order with the specified order id.
     * </p>
     *
     */
    @GetMapping("search")
    public ModelAndView searchOrder(HttpServletRequest request) {  
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_ORDERS);
        List<Order> orders = new ArrayList<Order>();
        try {
            Order order = orderService.searchOrder(Integer.parseInt(
                request.getParameter(Constants.LABEL_ORDER_ID)));
            if (null != order) {
                orders.add(order);
                modelAndView.addObject(Constants.LABEL_ORDERS, orders);
                modelAndView.addObject(Constants.LABEL_RESET_SEARCH, 
                    Boolean.TRUE);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_SEARCH_ORDER_FAIL);
                orders = orderService.getAllOrders();
                modelAndView.addObject(Constants.LABEL_ORDERS, orders);
            }
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the complete order history of all customers and displays them.
     * </p>
     *
     */
    @GetMapping("display")
    public ModelAndView displayAllOrders() {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_ORDERS);
        try {
            List<Order> orders = orderService.getAllOrders();
            modelAndView.addObject(Constants.LABEL_ORDERS, orders);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
}
