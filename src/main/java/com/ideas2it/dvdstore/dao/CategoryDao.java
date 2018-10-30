package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Category;

/**
 * <p>
 * The {@code CategoryDao} interface provides the operations that can
 * be performed on the categories to store into or retrieve from a database.
 * It provides the basic operations such as add, remove and find a category
 * to or from the database.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Category
 *
 */
public interface CategoryDao {

    /**
     * <p>
     * Adds a Category into the Database
     * </p>
     *
     * @param category
     *        New Category with name which has to be added to the Database
     *
     * @return message
     *         Returns true if category is successfully added
     *         to the database else Returns false. 
     */
    Boolean addCategory(Category category) throws DvdStoreException;

    /**
     * <p>
     * Removes a Category from the Database
     * </p>
     *
     * @param categoryId
     *        Category which has to be deleted from the Database
     *
     * @return message
     *         Returns true if category is successfully deleted
     *         from the database else Returns false. 
     */
    Boolean removeCategory(Integer categoryId) throws DvdStoreException;

    /**
     * <p>
     * Updates a Category in the Database
     * </p>
     *
     * @param category
     *        Category with details which has to be updated in the Database
     *
     * @return message
     *         Returns true if category is successfully updated
     *         in the database else Returns false. 
     */
    Boolean updateCategory(Category category) throws 
        DvdStoreException;

    /**
     * <p>
     * Restores a deleted Category detail
     * </p>
     *
     * @param categoryId
     *        Category which has to be retrieved if deleted
     *
     * @return Integer
     *         Returns true if category is successfully retrieved  
     *         from the database else Returns false. 
     */
    Boolean restoreCategory(Integer categoryId) throws DvdStoreException; 

    /**
     * <p>
     * Returns a Category from the Database
     * </p>
     *
     * @param categoryID
     *        ID of the Category which has to be searched from the Database
     * @param  status
     *         Status of the Category which has to be found
     *
     * @return Category
     *         Returns the category details if it's present in the 
     *         database else returns null. 
     */
    Category findCategory(Integer categoryId, Boolean status) throws 
        DvdStoreException;

    /**
     * <p>
     * Returns a Category with the specified name from the Database
     * </p>
     *
     * @param categoryName
     *        Name of the Category which has to be searched from the Database
     *
     * @return Category
     *         Returns the category details if it's present in the 
     *         database else returns null. 
     */
    Category findCategoryByName(String categoryName) throws 
        DvdStoreException;

    /**
     * <p>
     * Returns the list of available categories from the Database
     * </p>
     *
     * @param  status
     *         Status of the Categories which have to retrieved
     *
     * @return categories
     *         Returns the list of categories for the corresponding list of Ids
     */
    List<Category> getCategories(List<Integer> categoryIds, Boolean status) 
        throws DvdStoreException;

    /**
     * <p>
     * Returns the list of available categories from the Database
     * </p>
     *
     * @param  status
     *         Status of the Categories which have to retrieved
     *
     * @return categories
     *         Returns the list of categories present in the
     *         database. 
     */
    List<Category> getCategories(Boolean status) throws DvdStoreException;
}
