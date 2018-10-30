package com.ideas2it.dvdstore.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.dao.DvdDao;
import com.ideas2it.dvdstore.dao.impl.DvdDaoImpl;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.DvdService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.utils.DateUtils;

/**
 * <p>
 * The {@code DvdStoreServiceImpl} implements the DvdStoreService interface.
 * It provides defenitions for the functions declared in the DvdStoreService 
 * interface and for some other functions needed for the implementation of
 * business logic.
 * </p>
 *
 * @author Arun Karthick.J
 *
 * @see    com.ideas2it.dvdstore.dao.DvdDao
 * @see    com.ideas2it.dvdstore.dao.impl.DvdDaoImpl
 * @see    com.ideas2it.dvdstore.model.Dvd
 * @see    com.ideas2it.dvdstore.model.Category
 * @see    com.ideas2it.dvdstore.service.CategoryService
 * @see    com.ideas2it.dvdstore.service.DvdService
 * @see    com.ideas2it.dvdstore.service.impl.CategoryServiceImpl
 * @see    com.ideas2it.dvdstore.utils.DateUtils
 * 
 */
public class DvdServiceImpl implements DvdService { 
  
    private DvdDao dvdDao;
    private CategoryService categoryService;

    public void setDvdDao(DvdDao dvdDao) {
        this.dvdDao = dvdDao;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * @{inheritDoc}
     */
    public List<Category> fetchCategories(List<Integer> categoryIds) throws 
            DvdStoreException {
        return categoryService.getCategories(categoryIds, Boolean.TRUE);
    }

    /**
     * @{inheritDoc}
     */
    public List<Category> getCategories(Boolean status) throws 
            DvdStoreException {
        return categoryService.getCategories(status);
    }

    /**
     * @{inheritDoc}
     */
    public Boolean createDvd(Dvd dvd) throws DvdStoreException {
        return dvdDao.addDvd(dvd);
    }

    /**
     * @{inheritDoc}
     */
    public Dvd searchDvd(Dvd dvd) throws DvdStoreException {
        return dvdDao.searchDvd(dvd);
    }

    /**
     * @{inheritDoc}
     */
    public Dvd searchDvdById(Integer dvdId, Boolean status) throws 
            DvdStoreException {
        return dvdDao.searchDvdById(dvdId, status);
    }
 
    /**
     * @{inheritDoc}
     */
    public List<Dvd> getDvdsById(List<Integer> dvdIds, Boolean status) throws 
            DvdStoreException {
        return dvdDao.getDvdsById(dvdIds, status);
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> searchDvdByTitle(String title) throws DvdStoreException {
        return dvdDao.searchDvdByTitle(title);
    }

    /**
     * @{inheritDoc}
     */
    public Boolean deleteDvd(Integer dvdId) throws DvdStoreException {
        return dvdDao.removeDvd(dvdId); 
    }
     
    /**
     * @{inheritDoc}
     */
    public Boolean restoreDvd(Integer dvdId) throws DvdStoreException {
        return dvdDao.restoreDvd(dvdId);
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> fetchDvds(Boolean status) throws DvdStoreException {
        return dvdDao.getDvds(status);
    }
    
    /**
     * @{inheritDoc}
     */
    public Boolean updateDvd(Dvd dvd) throws DvdStoreException {    
        return dvdDao.updateDvd(dvd);                  
    }

    /**
     * @{inheritDoc}
     */
    public List<Dvd> updateDvdCopies(List<Dvd> dvds) throws DvdStoreException {
        List<Dvd> outOfStockDvds = new ArrayList<Dvd>();
        Integer copies;
        for (Dvd dvd : dvds) {
            copies = dvd.getCopies(); 
            if (0 != copies) {
               dvd.setCopies(--copies);
               dvdDao.updateDvd(dvd);
            } else {    
               outOfStockDvds.add(dvd);
            }
        }
        return outOfStockDvds;
    }

}
