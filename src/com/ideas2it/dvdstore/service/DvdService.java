package com.ideas2it.dvdstore.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Category;

/**
 * <p>
 * The {@code DvdService} interface provides declarations for basic 
 * functions that can be performed on DVDs such as create, read, update and
 * delete operations.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Dvd
 * @see    com.ideas2it.dvdstore.model.Category
 *
 */
public interface DvdService {

    /**
     * <p>
     * Creates a new DVD 
     * i.e Fetches all necessary details such as Title, Copies, Rating,
     * Cost, Language, Category as a DVD from user and stores them. 
     * </p>
     *
     * @param  dvd
     *         DVD with details which has to stored in the database
     *
     * @return true   If the Dvd is successfully created 
     *         false  If there is any problem in creating the Dvd
     */
    Boolean createDvd(Dvd dvd) throws DvdStoreException;

    /**
     * <p>
     * Requests the findDvd function to find DVD with the specific title,
     * language and type
     * </p>
     * 
     * @param  dvd
     *         Dvd with details which needs to be searched
     *
     * @return dvd    
     *         Returns the DVD with the desired criteria if it exist
     *         Returns null if no such DVD exist.
     */
    Dvd searchDvd(Dvd dvd) throws DvdStoreException;

    /**
     * <p>
     * Searches through the list for the specific DVD ID.
     * </p>
     * 
     * @param dvdId
     *        ID of the DVD which has to be searched for in the list.
     * @param  status
     *         Status of the Dvd to be searched. i.e(Is Available or Not)
     *
     * @return dvd    
     *         Returns the DVD with the desired ID if it exist
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
     * Fetches the DVD title, language and type and 
     * deletes the DVD with that criteria if it exists.
     * </p>
     *
     * @param dvdId
     *        Dvd which need to be deleted
     *
     * @return true   If the Dvd is successfully deleted 
     *         false  If there is any problem in deleting the Dvd
     */
    Boolean deleteDvd(Integer dvdId) throws DvdStoreException;

    /**
     * <p>
     * Restores a deleted DVD 
     * Returns Success Message if DVD is successfully retrieved else returns
     * a failure message.
     * </p>
     *
     * @param  dvdId
     *         DVD which has to be restored from the database
     *
     * @return true   If the Dvd is successfully restored 
     *         false  If there is any problem in restoring the Dvd
     */
    Boolean restoreDvd(Integer dvdId) throws DvdStoreException;
     
    /**
     * <p>
     * It fetches the DVD List based on the status.
     * </p>
     *
     * @param  status
     *         Provides status find which set of records to return 
     *
     * @return dvds
     *         Returns the entire DVD List
     */
    List<Dvd> fetchDvds(Boolean status) throws DvdStoreException;
    
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
     * Updates the contents of the corresponding DVD.
     * </p>
     *
     * @param dvd
     *        DVD with details which has to be updated.
     *
     * @return true   If the Dvd is successfully updated 
     *         false  If there is any problem in updating the Dvd
     */
    Boolean updateDvd(Dvd dvd) throws DvdStoreException;

    /**
     * <p>
     * Updates the number of copies of the list of Dvds.
     * </p>
     *
     * @param dvds
     *        DVDs whose copies needs to be updated
     *
     * @return dvds List of dvds which are out of stock;
     */
    List<Dvd> updateDvdCopies(List<Dvd> dvds) throws DvdStoreException;

    /**
     * <p>
     * Provides the corresponding list of movie categories for the DVD.
     * </p>
     *
     * @param  categoryIds
     *         User's choice for Categories of the DVD
     *
     * @return categories  List of categories           
     *         null        When no such category is found
     */
    List<Category> fetchCategories(List<Integer> categoryIds) throws 
        DvdStoreException;

    /**
     * <p>
     * It requests the CategoryService to provide the list of categories 
     * available in the database and then returns that value.
     * </p>
     *
     * @param  status
     *         Status of the Categories which have to retrieved
     *
     * @return categoryMenu
     *         Returns the list of available categories
     */
    List<Category> getCategories(Boolean status) throws DvdStoreException; 
}
