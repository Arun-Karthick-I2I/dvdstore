package com.ideas2it.dvdstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * The {@code CategoryController} class represents a Controller that provides 
 * functionality related to categories or genres. It interacts with the user
 * and satisfies the user needs accordingly.
 * </p>
 *
 */
@Controller
@RequestMapping("category")
public class CategoryController {  
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * <p>
     * Fetches the new category name and checks for it's presence
     * then creates the Category and returns the success or failure message.
     * </p>
     *
     */
    @PostMapping("create")
    public ModelAndView createCategory(HttpServletRequest request) {  
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        try {
            String categoryName = 
                request.getParameter(Constants.LABEL_CATEGORY_NAME);
            Category category = new Category(categoryName, Boolean.TRUE);
            Category existingCategory = 
                categoryService.searchCategoryByName(categoryName);
            if (null == existingCategory) {
                if (categoryService.createCategory(category)) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_ADD_SUCCESS);
                }
            } else {
                Boolean isAvailable = existingCategory.getIsAvailable();
                if (isAvailable) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_ALREADY_PRESENT + 
                        existingCategory.getId());
                } else {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_ALREADY_PRESENT_DELETED + 
                        existingCategory.getId());
                }
            }  
            getAllCategories(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the new category name and checks for it's presence
     * then updates the Category and returns the success or failure message.
     * </p>
     *
     */
    @PostMapping("update")
    public ModelAndView updateCategory(HttpServletRequest request) {  
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        try {
            Category category = searchCategory(request, modelAndView, 
                Boolean.TRUE);
            String categoryName = request.getParameter(
                Constants.LABEL_CATEGORY_NAME);
            category.setName(categoryName);
            Category existingCategory = 
                categoryService.searchCategoryByName(categoryName);
            if (null == existingCategory) {
                if (categoryService.updateCategory(category)) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_UPDATE_SUCCESS);
                }
            } else {
                Boolean presence = existingCategory.getIsAvailable();
                if (presence) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_ALREADY_PRESENT + 
                        existingCategory.getId());
                } else {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_ALREADY_PRESENT_DELETED + 
                        existingCategory.getId());
                }
            }  
            getAllCategories(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Searches for the Category with the specified ID from the DVD Store.
     * </p>
     *
     * @param  status         Status of the Category to be found (i.e Available 
     *                        or Not)
     *
     * @return category    If the Category object is found
     *         null        If no such Dvd is found
     * 
     */
    private Category searchCategory(HttpServletRequest request, ModelAndView 
            modelAndView, Boolean status) throws DvdStoreException {
        Category category = null;
        category = categoryService.searchCategory(Integer.parseInt(
            request.getParameter(Constants.LABEL_CATEGORY_ID)), status);
        if (null == category) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, 
                Constants.MESSAGE_SEARCH_CATEGORY_FAIL);
        }
        return category;
    }

    /**
     * <p>
     * Deletes the Category with the specified ID from the DVD Store.
     * </p>
     *
     */
    @PostMapping("delete")
    public ModelAndView deleteCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        try {
            if (categoryService.deleteCategory(Integer.parseInt(
                        request.getParameter(Constants.LABEL_CATEGORY_ID)))) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_DELETE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_DELETE_FAIL);
            }  
            getAllCategories(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
   
    /**
     * <p>
     * Restores deleted Category with the specified ID from the DVD Store.
     * </p>
     *
     */
    @PostMapping("restore")
    public ModelAndView restoreCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        try {
            if (categoryService.restoreCategory(Integer.parseInt(
                        request.getParameter(Constants.LABEL_CATEGORY_ID)))) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_CATEGORY_RESTORE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CATEGORY_RESTORE_FAIL);
            }  
            getAllCategories(modelAndView, Boolean.FALSE);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    
    /**
     * <p>
     * Searches for the Category with the specified ID from the DVD Store and 
     * then displays the Category contents if it exists or displays a message.
     * </p>
     *
     */
    @GetMapping("display")
    public ModelAndView displayCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        try {
            Category category = searchCategory(request, modelAndView, 
                Boolean.TRUE);
            if (null != category) {
                modelAndView.addObject(Constants.LABEL_VIEW_ALL, Boolean.FALSE);
                modelAndView.addObject(Constants.LABEL_CATEGORY, category);
            } 
        } catch(DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Displays the list of available categories to the user.
     * </p>
     *
     */
    @GetMapping("displayAllCategories")
    public ModelAndView displayAllCategories() {
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        try {
            getAllCategories(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Displays the list of deleted categories to the user.
     * </p>
     *
     */
    @GetMapping("displayDeletedCategories")
    public ModelAndView displayDeletedCategories() {
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        try {
            getAllCategories(modelAndView, Boolean.FALSE);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the entire list of Categories available in the DVD Store based
     * on the status.
     * </p>
     *
     * @param  status         Status of the categories to be displayed
     *  
     */
    private void getAllCategories(ModelAndView modelAndView, Boolean status) 
            throws DvdStoreException {
        List<Category> categories = categoryService.getCategories(status);
        modelAndView.addObject(Constants.LABEL_VIEW_ALL, Boolean.TRUE);
        modelAndView.addObject(Constants.LABEL_STATUS, status);
        modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
    }

    /**
     * <p>
     * Removes the Dvd from that particular category.
     * </p>
     *
     */
    @PostMapping("removeDvd")
    public ModelAndView removeDvdFromCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.CATEGORY_PAGE);
        Integer dvdId = Integer.parseInt(
                request.getParameter(Constants.LABEL_DVD_ID));
        try {
            Category category = searchCategory(request, modelAndView, 
                Boolean.TRUE);
            List<Dvd> dvds = category.getDvds();
            for (Dvd dvd : dvds) {
                if (dvdId == dvd.getId()) {
                    dvds.remove(dvd);
                    break;
                }
            }
            category.setDvds(dvds);
            if (categoryService.updateCategory(category)) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_DVD_REMOVED_FROM_CATEGORY_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_DVD_REMOVED_FROM_CATEGORY_FAIL);
            }
            modelAndView.addObject(Constants.LABEL_VIEW_ALL, Boolean.FALSE);
            modelAndView.addObject(Constants.LABEL_CATEGORY, category);
        } catch (DvdStoreException e) {
            modelAndView = new ModelAndView(Constants.ADMIN_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return modelAndView;
    }
}  
