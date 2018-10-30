package com.ideas2it.dvdstore.service;

import java.util.List;

import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Category;

/**
 * <p>
 * The {@code CategoryService} interface provides the operations
 * that can be done on a Category. It declares the basic functions that must
 * be implemented inorder to use category such as create, delete, find and 
 * update.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.exception.DvdStoreException
 * @see    com.ideas2it.dvdstore.model.Category
 *
 */
public interface CategoryService {
 
    /**
     * <p>
     * Creates a new DVD Category
     * Fetches the new category name and then returns whether the category
     * is successfully created or not.
     * </p>
     *
     * @param  category   Category with name that needs to be created
     *
     * @return true   If the category is successfully created 
     *         false  If there is any problem in creating the category
     */
    Boolean createCategory(Category category) throws DvdStoreException;

    /**
     * <p>
     * Deletes a DVD Category
     * Returns whether the category is successfully deleted or not.
     * </p>
     *
     * @param categoryId
     *        Category which has to be removed from the store.
     * 
     * @return true   If the category is successfully deleted 
     *         false  If there is any problem in deleting the category
     */
    Boolean deleteCategory(Integer categoryId) throws DvdStoreException;

    /**
     * <p>
     * Restores a deleted Category 
     * Returns Success Message if Category is successfully retrieved 
     * else returns a failure message.
     * </p>
     *
     * @param  categoryId
     *         Category which has to be restored from the database
     *
     * @return true   If the category is successfully restored
     *         false  If there is any problem in restoring the category
     */
    Boolean restoreCategory(Integer categoryId) throws DvdStoreException;

    /**
     * <p>
     * Updates a DVD Category
     * Fetches the Category ID and then returns whether the category is
     * successfully updated or not.
     * </p>
     *
     * @param categoryId
     *        ID of the Category which has to be updated in the store.
     * 
     * @return true   If the category is successfully updated 
     *         false  If there is any problem in updating the category
     */
    Boolean updateCategory(Category category) throws DvdStoreException;

    /**
     * <p>
     * Requests the findCategory function to find category with the specific ID.
     * </p>
     * 
     * @param categoryID
     *        Category ID whose details have to be found.
     * @param  status
     *         Status of the Category which has to be found
     *
     * @return Category    
     *         Returns the Category with the desired ID if it exist
     *         Returns null if no such category exist.
     */
    Category searchCategory(Integer categoryId, Boolean status) throws 
        DvdStoreException;

    /**
     * <p>
     * Finds the category with specified name if it exists.
     * </p>
     * 
     * @param categoryName
     *        Category Name whose details have to be found.
     *
     * @return Category    
     *         Returns the Category with the desired name if it exist
     *         Returns null if no such category exist.
     */
    Category searchCategoryByName(String categoryName) throws DvdStoreException;

    /**
     * <p>
     * Fetches the list of dvdCategories 
     * </p>
     *
     * @param  categoryIds
     *         IDs of the Categories which have to retrieved
     * @param  status
     *         Status of the Categories which have to retrieved
     *
     * @return categories
     *         Returns the list of available categories
     */
    List<Category> getCategories(List<Integer> categoryIds, Boolean status) 
        throws DvdStoreException;

    /**
     * <p>
     * Fetches the list of dvdCategories 
     * </p>
     *
     * @param  status
     *         Status of the Categories which have to retrieved
     *
     * @return categories
     *         Returns the list of available categories
     */
    List<Category> getCategories(Boolean status) throws DvdStoreException;
}
