package com.ideas2it.dvdstore.common;

/**
 * <p> 
 * Constants class is used to store Constant values, labels and messages
 * related to the DVDStore.
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public final class Constants {

    public static final float MINIMUM_RATING = 1;

    public static final float MAXIMUM_RATING = 10;

    public static final float MINIMUM_PRICE = 10;

    public static final float MAXIMUM_PRICE = 750;

    public static final Integer MINIMUM_LENGTH = 1;

    public static final Integer MAXIMUM_LENGTH = 30;

    public static final Integer SESSION_MAX_INACTIVE_INTERVAL = 100;

    public static final String PERCENT_SYMBOL = "%";

    public static final String COLON_SYMBOL = ":";

    public static final String SPACE = " ";

    public static final String NEWLINE = "\n";

    /**
     * <p>
     * All the Label Fields available are listed below
     * </p>
     */
    public static final String LABEL_ADDRESS = "address";

    public static final String LABEL_ADMIN = "admin";

    public static final String LABEL_ADDRESS_ID = "addressId";

    public static final String LABEL_ADDRESS_LINE = "addressLine";

    public static final String LABEL_CATEGORIES = "categories";

    public static final String LABEL_CATEGORY = "category";

    public static final String LABEL_CATEGORY_ID = "categoryId";

    public static final String LABEL_CATEGORY_NAME = "categoryName";
 
    public static final String LABEL_CHOICE = "choice";

    public static final String LABEL_CITY = "city";

    public static final String LABEL_COMMAND = "command";

    public static final String LABEL_COMPLETE_REGISTRATION = 
        "completeRegistration";

    public static final String LABEL_COPIES = "copies";

    public static final String LABEL_COUNTRY = "country";

    public static final String LABEL_CREATE = "create";

    public static final String LABEL_CUSTOMER = "customer";

    public static final String LABEL_CUSTOMERS = "customers";

    public static final String LABEL_CUSTOMER_ID = "customerId";

    public static final String LABEL_CUSTOMER_NAME = "customerName";

    public static final String LABEL_DAYS = " Days ago ";

    public static final String LABEL_DELETE = "delete";

    public static final String LABEL_DISPLAY = "display";

    public static final String LABEL_DVD = "dvd";

    public static final String LABEL_DVDS = "dvds";

    public static final String LABEL_DVD_ID = "dvdId";

    public static final String LABEL_EMAIL_ID = "emailId";

    public static final String LABEL_EXIT = "exit";

    public static final String LABEL_FORWARD = "forward";

    public static final String LABEL_ID = "id";

    public static final String LABEL_ISAVAILABLE = "isAvailable";

    public static final String LABEL_LANGUAGE = "language";

    public static final String LABEL_LOGIN = "login";
 
    public static final String LABEL_MESSAGE = "message";

    public static final String LABEL_MOBILE_NUMBER = "mobileNumber";

    public static final String LABEL_MONTHS = " Months ";

    public static final String LABEL_MOVIE_HD = "720P HD READY";

    public static final String LABEL_MOVIE_FHD = "1080P FULL HD";
 
    public static final String LABEL_MOVIE_UHD = "4K ULTRA HD";

    public static final String LABEL_NAME = "name";

    public static final String LABEL_OPERATION = "operation";

    public static final String LABEL_ORDER = "order";

    public static final String LABEL_ORDERS = "orders";

    public static final String LABEL_ORDER_ID = "orderId";

    public static final String LABEL_PASSWORD = "password";

    public static final String LABEL_PRICE = "price";

    public static final String LABEL_PINCODE = "pinCode";

    public static final String LABEL_PURCHASE = "purchase";

    public static final String LABEL_RATING = "rating";

    public static final String LABEL_REGISTER = "register";

    public static final String LABEL_RELEASE_DATE = "releaseDate";

    public static final String LABEL_RESTORE = "restore";

    public static final String LABEL_RESET_SEARCH = "resetSearch";

    public static final String LABEL_ROLE = "role";

    public static final String LABEL_SEARCH = "search";
 
    public static final String LABEL_STATE = "state";

    public static final String LABEL_STATUS = "status";

    public static final String LABEL_TITLE = "title";
   
    public static final String LABEL_TODAY = "today";

    public static final String LABEL_TYPE = "type";

    public static final String LABEL_UPDATE = "update";

    public static final String LABEL_USER = "user";

    public static final String LABEL_USERNAME = "userName";

    public static final String LABEL_USER_ID = "userId";

    public static final String LABEL_VIEW_ALL = "viewAll";

    public static final String LABEL_YEARS = " Years ";

    /**
     * <p>
     * All the Messages available are listed below
     * </p>
     */
    public static final String MESSAGE_ADD_MORE_CATEGORY = 
        "Do you want to add more Categories ? (Press y for Yes else Press n)";

    public static final String MESSAGE_ADDRESS_ADD_FAIL = 
        "Failed to add Address";
 
    public static final String MESSAGE_ADDRESS_ADD_SUCCESS = 
        "Address Added Successfully";
 
    public static final String MESSAGE_ADDRESS_REMOVE_SUCCESS = 
        "Address Removed Successfully";
 
    public static final String MESSAGE_CATEGORY_ADD_FAIL = 
        "Failed to add Category";

    public static final String MESSAGE_CATEGORY_ADD_SUCCESS = 
        "Category Added Successfully";
 
    public static final String MESSAGE_CUSTOMER_REMOVE_SUCCESS = 
        "Customer Removed Successfully";
 
    public static final String MESSAGE_CUSTOMER_REMOVE_FAIL = 
        "Failed to remove Customer";
 
    public static final String MESSAGE_LOGGED_OUT = 
        "You have logged out of the store successfully!!";

    public static final String MESSAGE_CATEGORY_ALREADY_PRESENT = 
        "A Category with similar name is already present with ID:";
 
    public static final String MESSAGE_CATEGORY_ALREADY_PRESENT_DELETED = 
        "This Category was previously present but was deleted with ID :";

    public static final String MESSAGE_CATEGORY_DISPLAY =
        "List of Categories Available:";

    public static final String MESSAGE_CATEGORY_DELETE_FAIL = 
        "Failed to delete Category";

    public static final String MESSAGE_CATEGORY_DELETE_SUCCESS = 
        "Category Deleted Successfully";

    public static final String MESSAGE_CATEGORY_RESTORE_FAIL = 
        "Failed to restore Category";

    public static final String MESSAGE_CATEGORY_RESTORE_SUCCESS = 
        "Category Restored Successfully";

    public static final String MESSAGE_CHECK_RELEASE_DATE = 
        "Release Date can't be in Future";

    public static final String MESSAGE_CHOICE = "Enter your Choice :";

    public static final String MESSAGE_COPIES = "Enter Copies Available :";

    public static final String MESSAGE_CREATE_SUCCESS = 
        "DVD Created and Added to Store Successfully";

    public static final String MESSAGE_CREATE_FAIL =
        "DVD not added to the store";

    public static final String MESSAGE_CUSTOMER_ALREADY_EXIST =
        "A customer with either the same mobile number or email id exists";

    public static final String MESSAGE_CUSTOMER_NOT_FOUND_REGISTER =
        "No Customer exists with this Username!! Please Register";

    public static final String MESSAGE_DELETE_SUCCESS = 
        "DVD Deleted Successfully";

    public static final String MESSAGE_DELETE_FAIL = "Unable to Delete DVD";

    public static final String MESSAGE_DVD_ALREADY_PRESENT = 
        "A similar DVD already present in the Store with ID:";

    public static final String MESSAGE_DVD_ALREADY_PRESENT_DELETED = 
        "A similar DVD was previously deleted from the Store with ID:";

    public static final String MESSAGE_DVD_LIST = "List of DVDs :";

    public static final String MESSAGE_DVD_REMOVED_FROM_CATEGORY_SUCCESS = 
        "Dvd removed from the Category successfully";

    public static final String MESSAGE_DVD_REMOVED_FROM_CATEGORY_FAIL = 
        "Unable to remove Dvd from the Category";

    public static final String MESSAGE_HIBERNATE_EXCEPTION = 
        "Hibernate Exception";

    public static final String MESSAGE_INVALID_CREDENTIALS = 
        "Invalid Credentials";

    public static final String MESSAGE_LOG_DISPLAY_CATEGORY = 
        "Category Details displayed Successfully :";

    public static final String MESSAGE_LOG_DISPLAY_CUSTOMER = 
        "Customer Details displayed Successfully ";

    public static final String MESSAGE_LOG_DISPLAY_ORDER = 
        "Order Details displayed Successfully ";

    public static final String MESSAGE_LOG_DISPLAY_DVD = 
         "DVD Displayed Successfully :";

    public static final String MESSAGE_LOG_CUSTOMER_LOGIN = 
        "Customer Logged in Successfully : ";

    public static final String MESSAGE_LOG_CUSTOMER_LOGOUT = 
        "Customer Logged out Successfully : ";

    public static final String MESSAGE_LOG_NO_SUCH_ALGORITHM = 
        "No Such Algorithm found";

    public static final String MESSAGE_ORDER_SUCCESS = 
        "DVD Purchased Successfully ";

    public static final String MESSAGE_ORDER_CANCEL_SUCESS = 
        "Order Cancelled Successfully";

    public static final String MESSAGE_ORDER_CANCEL_FAIL = 
        "Failed to Cancel Order";

    public static final String MESSAGE_ORDER_FAIL = 
        "Unable to purchase DVDs";

    public static final String MESSAGE_OUT_OF_STOCK = 
        "The following Dvds went out of Stock:";

    public static final String MESSAGE_PRICE = "Enter Price between 10 - 750";

    public static final String MESSAGE_PURCHASE =
        "Do you want to purchase ?  (Press y for Yes else Press n) ";

    public static final String MESSAGE_RATING = "Enter Rating between 1 - 10";

    public static final String MESSAGE_REGISTER = 
        "Do you want to register ?  (Press y for Yes else Press n)  ";

    public static final String MESSAGE_REGISTER_SUCCESS = 
        "Registered Successfully";

    public static final String MESSAGE_REGISTER_FAIL = 
        "Registration Failed ";

    public static final String MESSAGE_RESTORE = 
        "Do you want to Restore Anything ? (Press y for Yes else Press n)";

    public static final String MESSAGE_RESTORE_SUCCESS = 
        "DVD Restored Successfully";

    public static final String MESSAGE_RESTORE_FAIL = 
        "Failed to restore DVD";

    public static final String MESSAGE_RESTORED_DVD =
        "We have restored a DVD with similar details";

    public static final String MESSAGE_SEARCH = "Enter DVD Details :";

    public static final String MESSAGE_SEARCH_FAIL = 
        "The Given DVD doesnot exist ";

    public static final String MESSAGE_SEARCH_CATEGORY_FAIL = 
        "The Given Category doesn't exist ";

    public static final String MESSAGE_SEARCH_CUSTOMER_FAIL =
        "The Given Customer doesn't exist ";

    public static final String MESSAGE_SEARCH_ORDER_FAIL =
        "The Given Order doesn't exist ";

    public static final String MESSAGE_SEARCH_DVD_TITLE_FAIL =
        "No DVD available in that Title";

    public static final String MESSAGE_SESSION_EXPIRED = 
        "Your Session has expired! Please Login again";

    public static final String MESSAGE_SESSION_FACTORY_FAIL = 
        "Failed to Create Session Factory";

    public static final String MESSAGE_TRY_AGAIN_LATER = 
        "Problem with the System : Please Try Again Later ";     
 
    public static final String MESSAGE_EXCEPTION_ADD = 
        "Problem with Database while adding : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_PURCHASE = 
        "Problem with Database while purchasing : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_REGISTER = 
        "Problem with Database while registering Customer : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_REMOVE = 
        "Problem with Database while removing : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_RESTORE = 
        "Problem with Database while restoring : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_UPDATE = 
        "Problem with Database while updating : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_SEARCH = 
        "Problem with Database while searching : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_SEARCH_CUSTOMER = 
        "Problem with Database while searching Customer : Try Again Later ";

    public static final String MESSAGE_EXCEPTION_GET_CATEGORIES = 
        "Problem in fetching Categories from the Database: Try Again Later";

    public static final String MESSAGE_EXCEPTION_GET_CUSTOMERS = 
        "Problem in fetching Customers from the Database: Try Again Later ";

    public static final String MESSAGE_EXCEPTION_GET_DVDS = 
        "Problem in fetching DVDS from the Database: Try Again Later ";

    public static final String MESSAGE_EXCEPTION_GET_ORDERS = 
        "Problem in fetching Orders from the Database: Try Again Later ";

    public static final String MESSAGE_UPDATE_SUCCESS = "Successfully Updated";    

    public static final String MESSAGE_UPDATE_FAIL = "Updation Failed";

    public static final String MESSAGE_USERNAME_ALREADY_TAKEN = 
        "UserName Already Taken";

    public static final String MESSAGE_UNAUTHORISED_ACCESS = 
        "You are not authorized to access that page";

    /**
     * <p>
     * Page names are listed below
     * </p>
     *
     */
    public static final String ADDRESS_FORM = "AddressForm";    

    public static final String ADMIN_HOME = "AdminHome";    

    public static final String CUSTOMER_FORM = "CustomerForm";    

    public static final String VIEW_CUSTOMER = "ViewCustomer";    

    public static final String CUSTOMER_HOME = "CustomerHome";   

    public static final String INDEX_PAGE = "index";    

    public static final String PURCHASE_DVD = "PurchaseDvd";

    public static final String REDIRECT = "redirect:";

    public static final String CATEGORY_PAGE = "Category";

    public static final String DISPLAY_ORDERS = "ViewOrders";

    public static final String DVD_FORM = "DvdForm";

    public static final String DISPLAY_DVDS = "DisplayDvds";

}

