package com.ideas2it.dvdstore.exception;

/**
 * <p>
 * The {@code DvdStoreException} is an exception class that provides
 * information regarding errors related to DVD Store.
 * </p>
 *
 */
public class DvdStoreException extends Exception {
    
    public DvdStoreException(String message) {
        super(message);
    }
    public DvdStoreException(String message, Throwable cause) {
        super(message, cause);
    }
    public DvdStoreException(Throwable cause) {
        super(cause);
    }

}
