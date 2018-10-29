package com.ideas2it.dvdstore.utils;

import com.ideas2it.dvdstore.common.Constants;

/**
 * <p>
 * The {@code CommonUtils} class provides functions to perform validations on
 * String Object
 * </p>
 *
 */
public class CommonUtils {

    /**
     * <p>
     * Validates whether the String provided by the user contains only letters
     * and doesnot contain any numbers or symbols
     * </p>
     *
     * @param  value
     *         String which needs to be validated
     *
     * @return true    If the string contains only letters  
     *         false   If it contains any other characters other than letters
     */
    public static Boolean validateName(String value) {
        return value.matches("[a-zA-Z]+");
    }

    /**
     * <p>
     * Validates whether the String provided by the user doesn't exceed
     * maximum length or doesn't contain a single character
     * </p>
     *
     * @param  value
     *         String which needs to be validated
     *
     * @return true    If the length is either less than Minimum Length or
                       exceeds Maximum Length
     *         false   Else returns false
     */
    public static Boolean validateLength(String value) {
        return (Constants.MINIMUM_LENGTH > value.length() ||
            value.length() > Constants.MAXIMUM_LENGTH);
    }

    /**
     * <p>
     * Checks whether the address either doesn't contain anything or doesn't 
     * exceed the limit
     * </p>
     *
     * @return true   If address length is within the limits
     *         false  If it either doesn't contain a single letter or exceeds 
     *                the limit
     */
    public static Boolean validateAddress(String address) {
        int length = address.length();
        return (Constants.MINIMUM_ADDRESS_LENGTH <= length && 
            length <= Constants.MAXIMUM_ADDRESS_LENGTH);
    }

    /**
     * <p>
     * Validates whether the String provided by the user is in the form of a 
     * mobile number
     * </p>
     *
     * @param  value
     *         String which needs to be validated 
     *
     * @return true    If the string is a mobile number
     *         false   If it is not a mobile number
     */
    public static Boolean validateMobileNumber(String value) {
        return value.matches("[6-9][0-9]{9}");
    }

    /**
     * <p>
     * Validates whether the String provided by the user is in the form of an 
     * email id
     * </p>
     *
     * @param  value
     *         String which needs to be validated 
     *
     * @return true    If the string is an email id
     *         false   If it is not an email id
     */
    public static Boolean validateEmailId(String value) {
        return value.matches(
            "[a-zA-Z0-9.]{1,25}[@][a-zA-Z0-9]{1,10}[.][a-z]{2,3}");
    }
}
