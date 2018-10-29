package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * The {@code DvdDao} interface provides the operations that can
 * be performed to a Store. It provides the basic operations such as add, 
 * remove and find dvds from the dvdstore.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Dvd
 *
 */
public interface DvdDao {   

    /**
     * <p>
     * Adds a DVD into the Database
     * </p>
     *
     * @param dvd
     *        DVD which has to be added to the Database
     *
     * @return message
     *         Returns true if DVD is successfully created  
     *         and added to the database else Returns false. 
     */
    Boolean addDvd(Dvd dvd) throws DvdStoreException;

    /**
     * <p>
     * Restores a deleted DVD detail
     * </p>
     *
     * @param dvdId
     *        DVD which has to be retrieved if deleted
     *
     * @return Integer
     *         Returns true if DVD is successfully retrieved  
     *         from the database else Returns false. 
     */
    Boolean restoreDvd(Integer dvdId) throws DvdStoreException; 
 
    /**
     * <p>
     * Removes a DVD from the Database
     * </p>
     *
     * @param dvdId
     *        DVD which has to be removed from the Database
     *
     * @return message
     *         Returns true if DVD is successfully deleted  
     *         from the database else Returns false. 
     */
    Boolean removeDvd(Integer dvdId) throws DvdStoreException;

    /**
     * <p>
     * Updates the DVD details in the Database
     * </p>
     *
     * @param dvd
     *        DVD which has to be updated in the Database
     *
     * @return message
     *         Returns true if DVD is successfully updated  
     *         in the database else Returns false. 
     */
    Boolean updateDvd(Dvd dvd) throws DvdStoreException;

    /**
     * <p>
     * Searches through the database based on DVD Title, Language and Type.
     * It compares the DVD with those DVDs in the Database.
     * </p>
     * 
     * @param dvd
     *        DVD with the title, language and type which has to be searched.
     *
     * @return resultDvd    
     *         Returns the DVD with the desired criteria if it exist
     *         Returns null if no such DVD exist.
     */
    Dvd searchDvd(Dvd dvd) throws DvdStoreException;

    /**
     * <p>
     * Searches through the database based on Dvd ID.
     * It compares the DVD with those DVDs in the Database.
     * </p>
     * 
     * @param  dvdId
     *         Id of the Dvd which has to be searched.
     * @param  status
     *         Status of the Dvd to be searched. i.e(Is Available or Not)
     *
     * @return resultDvd    
     *         Returns the DVD with the desired criteria if it exist
     *         Returns null if no such DVD exist.
     */
    Dvd searchDvdById(Integer dvdId, Boolean status) throws DvdStoreException;

    /**
     * <p>
     * Searches for the list of DVDs with the specific title
     * </p>
     * 
     * @param title
     *        Title whose details are being searched for.
     *
     * @return dvds    
     *         Returns the list of DVDs with the desired criteria if they exist
     *         Returns null if no such DVDs exist.
     */
    List<Dvd> searchDvdByTitle(String title) throws DvdStoreException;

    /**
     * <p>
     * It fetches the DVD List based on the list of ids and status
     * </p>
     *
     * @param  dvdIds
     *         List of DVDs to be fetched.
     * @param  status
     *         Provides status find which set of records to return. 
     *
     * @return dvds
     *         Returns the list of dvds corresponding to the ids provided.
     */
    List<Dvd> getDvdsById(List<Integer> dvdIds, Boolean status) throws 
        DvdStoreException;

    /**
     * <p>
     * Returns the list of DVDs available in the store i.e Database
     * i.e Copies the the DVDs from database to a list and then returns it.
     * </p>
     *
     * @param status
     *        Status of records which have to be fetched i.e Active or Inactive
     *
     * @return dvds
     *         Returns the entire DVD collection from the database as a list.
     */    
    List<Dvd> getDvds(Boolean status) throws DvdStoreException;
} 
