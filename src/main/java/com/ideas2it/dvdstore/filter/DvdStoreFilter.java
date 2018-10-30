package com.ideas2it.dvdstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.common.enums.Role.USER_ROLES;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;

/**
 * <p>
 * The {@code DvdStoreFilter} class implements Filter interface. It filters  
 * the incoming requests based on the presence of user ID. It restricts
 * user access based on their role.
 * </p>
 */
public class DvdStoreFilter implements Filter {

    private static final String ADDITIONAL_FILES_PATTERN = 
        ".*(js|css|jpg|png|gif)";    
    private static final String CATEGORY_PATH = "/category/";
    private static final String CUSTOMER_PATH = "/customer/";
    private static final String DISPLAY_ALL_CUSTOMERS = "displayAllCustomers";
    private static final String DVD_PATH = "/dvd/";
    private static final String FORWARD_SLASH_SYMBOL = "/";
    private static final String HOME_PATH = "/user/home";
    private static final String INITIAL_PROJECT_PATH = "/dvdstore";
    private static final String ORDER_PATH = "/order/";
    private static final String PROJECT_PATH = "/dvdstore/";
    private static final String SEARCH_CUSTOMER = "searchCustomer";
    private static final String USER_PATH = "/user/";
    private static final String VIEW_CUSTOMER = "viewCustomer";

    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException {

        Integer userId = null;  
        USER_ROLES role = null;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(Boolean.FALSE);
        String uri = httpRequest.getRequestURI();
 
        httpResponse.setHeader("Cache-Control", "no-cache, no-store");
        httpResponse.setHeader("Pragma", "no-cache");

        // Fetch UserID and User Role if the session is available
        if (null != session) {
            userId = (Integer) session.getAttribute(Constants.LABEL_USER_ID);
            role = (USER_ROLES) session.getAttribute(Constants.LABEL_ROLE);
        }
        
        if (uri.endsWith(Constants.LABEL_LOGIN) || uri.endsWith(PROJECT_PATH)
                || uri.endsWith(INITIAL_PROJECT_PATH)) {
            if (null == userId) {

                // When the page is accessed for the first time
                chain.doFilter(request, response);
            } else {

                /* While the user is logged in already ,he should automatically 
                   be redirected to their home page */
                httpRequest.getRequestDispatcher(HOME_PATH).
                    forward(httpRequest, httpResponse);
            }
        } else if ((uri.contains(USER_PATH) && !(uri.endsWith(HOME_PATH))) || 
                uri.matches(ADDITIONAL_FILES_PATTERN) ||
                    ((null != userId) && (uri.endsWith(HOME_PATH)))) {

            /* Additional files such as javascripts,stylesheets, images are 
               allowed to pass through filter. When user logs out or 
               when he accesses the homepage, the request must be allowed */
            chain.doFilter(request, response);

        } else if ((null != userId) && (USER_ROLES.ADMIN == role)) {

            // Restricting Admin Access to the specified pages
            if (checkAdminAccess(uri)) {
                chain.doFilter(request, response);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher(HOME_PATH).
                    forward(httpRequest, httpResponse);
            }

        } else if ((null != userId) && (USER_ROLES.CUSTOMER == role)) {

            // Restricting Customer Access to the specified pages
            if (uri.contains(CUSTOMER_PATH) && !checkAdminAccess(uri)) {
                chain.doFilter(request, response);
            } else {
                httpRequest.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_UNAUTHORISED_ACCESS);
                httpRequest.getRequestDispatcher(HOME_PATH).
                    forward(httpRequest, httpResponse);
            }

        } else {

            // When session has expired, the user is redirected to login page
            session = httpRequest.getSession(Boolean.TRUE);
            session.setAttribute(Constants.LABEL_MESSAGE,
                Constants.MESSAGE_SESSION_EXPIRED);
            httpResponse.sendRedirect(INITIAL_PROJECT_PATH);
        }
    }

    private Boolean checkAdminAccess(String uri) {
        return (uri.contains(DVD_PATH) || uri.contains(CATEGORY_PATH) ||
            uri.contains(ORDER_PATH) || uri.endsWith(VIEW_CUSTOMER) ||
            uri.endsWith(DISPLAY_ALL_CUSTOMERS) || 
            uri.endsWith(SEARCH_CUSTOMER));
    }

    public void destroy() {
    }

}
