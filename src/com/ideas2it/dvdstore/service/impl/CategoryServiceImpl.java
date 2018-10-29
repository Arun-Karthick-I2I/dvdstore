package com.ideas2it.dvdstore.service.impl;

import java.util.List;

import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.dao.impl.CategoryDaoImpl;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.service.CategoryService;

/**
 * <p>
 * The {@code CategoryServiceImpl} implements the CategoryService 
 * interface. It provides defenitions for the functions declared in the
 * CategoryService Interface.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.dao.CategoryDao
 * @see    com.ideas2it.dvdstore.dao.impl.CategoryDaoImpl
 * @see    com.ideas2it.dvdstore.model.Category
 * @see    com.ideas2it.dvdstore.service.CategoryService
 *
 */
public class CategoryServiceImpl implements CategoryService{
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * @{inheritDoc}
     */
    public Boolean createCategory(Category category) throws DvdStoreException {
        return categoryDao.addCategory(category);    
    }

    /**
     * @{inheritDoc}
     */
    public Boolean deleteCategory(Integer categoryId) throws 
            DvdStoreException {
        return categoryDao.removeCategory(categoryId);
    }

    /**
     * @{inheritDoc}
     */
    public Boolean restoreCategory(Integer categoryId) throws 
            DvdStoreException {
        return categoryDao.restoreCategory(categoryId);        
    }

    /**
     * @{inheritDoc}
     */
    public Boolean updateCategory(Category category) throws DvdStoreException {
        return categoryDao.updateCategory(category);
    }

    /**
     * @{inheritDoc}
     */
    public Category searchCategory(Integer categoryId, Boolean status) throws
            DvdStoreException {
        return categoryDao.findCategory(categoryId, status);
    }

    /**
     * @{inheritDoc}
     */
    public Category searchCategoryByName(String categoryName) throws
            DvdStoreException {
        return categoryDao.findCategoryByName(categoryName);
    }

    /**
     * @{inheritDoc}
     */
    public List<Category> getCategories(List<Integer> categoryIds, 
            Boolean status) throws DvdStoreException {
        return categoryDao.getCategories(categoryIds, status);
    }

    /**
     * @{inheritDoc}
     */
    public List<Category> getCategories(Boolean status) throws
            DvdStoreException {
        return categoryDao.getCategories(status);
    }
}
