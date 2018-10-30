package com.ideas2it.dvdstore.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;

/**
 * <p>
 * The {@code DvdStoreSessionManager} Class is used to a create a singleton 
 * object that creates a session factory and provides access to it.
 * </p>
 *
 */
public class DvdStoreSessionManager {
    private static DvdStoreSessionManager dvdStoreSessionManager;
    private static SessionFactory sessionFactory;

    private DvdStoreSessionManager() {
        try {
            sessionFactory = (new Configuration()).configure("hibernate.cfg.xml").
                buildSessionFactory();
        } catch (Throwable cause) {
            DvdStoreLogger.error(Constants.MESSAGE_SESSION_FACTORY_FAIL, cause);
        }
    }

    /**
     * <p>
     * It returns the DvdStoreSessionManager Object to the invoking function. 
     * It is the only access point to get the session factory initialised.
     * </p>
     *
     * @return dvdStoreSessionManager
     *         Returns the sessionManager object that can access the intialised
     *         session factory. 
     */
    public static DvdStoreSessionManager getInstance() {
        if (null == dvdStoreSessionManager) {
            dvdStoreSessionManager = new DvdStoreSessionManager();
        }
        return dvdStoreSessionManager;
    }

    /**
     * <p>
     * It returns the session factory object intialised with the specified 
     * hibernate configuration.
     * </p>
     *
     * @return sessionFactory
     *         Returns the session factory. 
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
