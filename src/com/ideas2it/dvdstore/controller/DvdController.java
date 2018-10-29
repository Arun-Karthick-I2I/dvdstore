package com.ideas2it.dvdstore.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DvdStoreException;
import com.ideas2it.dvdstore.logger.DvdStoreLogger;
import com.ideas2it.dvdstore.service.DvdService;
import com.ideas2it.dvdstore.service.impl.DvdServiceImpl;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;

/**
 * <p>
 * The {@code DvdController} class represents a Controller that provides 
 * functionality related to dvds. It interacts with the user and satisfies
 * the user needs accordingly.
 * </p>
 *
 */
@Controller
@RequestMapping("dvd")
public class DvdController {  
    private DvdService dvdService;

    public void setDvdService(DvdService dvdService) {
        this.dvdService = dvdService;
    }

    /**
     * <p>
     * Provides an empty Dvd object to Create New Dvd Page in which the details 
     * need to be filled out.
     * </p>
     *
     */
    @GetMapping("newDvdForm")
    public ModelAndView showDvdForm() {
        ModelAndView modelAndView = new ModelAndView(Constants.DVD_FORM);
        try {
            getCategories(modelAndView);
            modelAndView.addObject(Constants.LABEL_DVD, new Dvd());
            modelAndView.addObject(Constants.LABEL_TODAY, LocalDate.now());
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(Constants.ADMIN_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the list of categories from the Dvd Store and stores it in a
     * session attribute for use.
     * </p>
     *
     */
    private void getCategories(ModelAndView modelAndView) throws 
            DvdStoreException {
        List<Category> categories = new ArrayList<Category>();
        categories = dvdService.getCategories(Boolean.TRUE);
        modelAndView.addObject(Constants.LABEL_CATEGORIES, categories);
    }

    /**
     * <p>
     * Fetches the list of categories from the Dvd Store and assigns the 
     * categories chosen by user in a list
     * </p>
     *
     * @return categories  List of categories chosen by the user.
     *
     */
    private List<Category> assignCategories(HttpServletRequest request) throws 
            DvdStoreException {
        Category category;
        List<Category> categories = new ArrayList<Category>();
        List<Integer> categoryIds = new ArrayList<Integer>();
        for (String categoryId : request.getParameterValues(
                Constants.LABEL_CATEGORY)) {
            categoryIds.add(Integer.parseInt(categoryId)); 
        }
        categories = dvdService.fetchCategories(categoryIds);
        return categories;
    }

    /**
     * <p>
     * Checks for the presence of the DVD object and then creates a new dvd
     * if no such dvd exists
     * </p>
     *
     */
    @PostMapping("create")
    public ModelAndView createDvd(HttpServletRequest request, 
            @ModelAttribute(Constants.LABEL_DVD) Dvd dvd) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);  
        try {
            dvd.setType(request.getParameter(Constants.LABEL_TYPE));
            dvd.setCategories(assignCategories(request));
            dvd.setIsAvailable(Boolean.TRUE);
            Dvd existingDvd = dvdService.searchDvd(dvd);
            if (null == existingDvd) {
                if (dvdService.createDvd(dvd)) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_CREATE_SUCCESS);
                }
            } else {
                Boolean presence = existingDvd.getIsAvailable();
                Integer dvdId = existingDvd.getId();
                if (presence) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_DVD_ALREADY_PRESENT + dvdId);
                } else {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_DVD_ALREADY_PRESENT_DELETED + dvdId);
                }
            }
            getAllDvds(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(Constants.ADMIN_HOME);
        } 
        return modelAndView;        
    }

    /**
     * <p>
     * Searches for the Dvd with the specified ID from the DVD Store.
     * </p>
     *
     * @param  status  Status of the DVD to be searched (i.e Available or Not)
     *
     * @return dvd     If the Dvd object is found
     *         null    If no such Dvd is found
     * 
     */
    private Dvd searchDvdById(HttpServletRequest request, 
            ModelAndView modelAndView, Boolean status) {
        Dvd dvd = null;
        try {
           dvd = dvdService.searchDvdById(
                Integer.parseInt(request.getParameter(Constants.LABEL_DVD_ID)), 
                status);
            if (null == dvd) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_SEARCH_FAIL);
            }
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return dvd;
    }

    /**
     * <p>
     * Deletes the DVD with the corresponding details from the DVD Store.
     * </p>
     *
     * 
     */
    @PostMapping("delete")
    public ModelAndView deleteDvd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);
        try {
           if (dvdService.deleteDvd(Integer.parseInt(request.getParameter(
                    Constants.LABEL_DVD_ID)))) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_DELETE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_DELETE_FAIL);
            }  
            getAllDvds(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(Constants.ADMIN_HOME);
        }
        return modelAndView;
    }
   
    /**
     * <p>
     * Restores deleted DVD with the corresponding details from the DVD Store.
     * </p>
     *
     */
    @PostMapping("restore")
    public ModelAndView restoreDvd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);
        try {
            if (dvdService.restoreDvd(Integer.parseInt(request.getParameter(
                    Constants.LABEL_DVD_ID)))) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_RESTORE_SUCCESS);
            } else {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_RESTORE_FAIL);
            }  
            getAllDvds(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(Constants.ADMIN_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the DVD that needs to be edited and passes it to the update form
     * page for further processing.
     * </p>
     *
     */
    @PostMapping("edit")
    public ModelAndView editDvd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.DVD_FORM);
        Dvd dvd = searchDvdById(request, modelAndView, Boolean.TRUE);
        if (null != dvd) {
            modelAndView.addObject(Constants.LABEL_DVD, dvd);
            modelAndView.addObject(Constants.LABEL_TODAY, LocalDate.now());
            try {
                getCategories(modelAndView);
            } catch (DvdStoreException e) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
                modelAndView.setViewName(Constants.ADMIN_HOME);
            }
        } else {
            getAllDvds(modelAndView, Boolean.TRUE);
            modelAndView.setViewName(Constants.ADMIN_HOME);
        }
        return modelAndView;
    } 

    /**
     * <p>
     * Updates the contents of the DVD based on the input from the update page.
     * </p>
     *
     */
    @PostMapping("update")
    public ModelAndView updateDvd(HttpServletRequest request, 
            @ModelAttribute(Constants.LABEL_DVD) Dvd dvd) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);  
        try {
            dvd.setType(request.getParameter(Constants.LABEL_TYPE));
            dvd.setCategories(assignCategories(request));
            dvd.setIsAvailable(Boolean.TRUE);
            Dvd existingDvd = dvdService.searchDvd(dvd);
            if (null == existingDvd) {
                if (dvdService.updateDvd(dvd)) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_UPDATE_SUCCESS);
                }
            } else if ((dvd.getId() == existingDvd.getId()) &&
                    (dvdService.updateDvd(dvd))) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_UPDATE_SUCCESS);
            } else {
                Boolean presence = existingDvd.getIsAvailable();
                Integer dvdId = existingDvd.getId();
                if (presence) {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_DVD_ALREADY_PRESENT + dvdId);
                } else {
                    modelAndView.addObject(Constants.LABEL_MESSAGE, 
                        Constants.MESSAGE_DVD_ALREADY_PRESENT_DELETED + dvdId);
                }
            }
            getAllDvds(modelAndView, Boolean.TRUE);
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(Constants.ADMIN_HOME);
        }
        return modelAndView;
    }

    /**
     * <p>
     * Searches for the Dvd with the specified ID from the DVD Store and then
     * displays the DVD if it exists or displays a message.
     * </p>
     *
     */
    @GetMapping("displayDvdById")
    public ModelAndView displayDvdById(HttpServletRequest request) { 
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);
        Dvd dvd = searchDvdById(request, modelAndView, Boolean.TRUE);
        List<Dvd> dvds = new ArrayList<Dvd>();
        if (null != dvd) {
            dvds.add(dvd);
            modelAndView.addObject(Constants.LABEL_RESET_SEARCH, 
                Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_STATUS, Boolean.TRUE);
            modelAndView.addObject(Constants.LABEL_DVDS, dvds);
        } else { 
            modelAndView.addObject(Constants.LABEL_MESSAGE,
                Constants.MESSAGE_SEARCH_FAIL);        
            getAllDvds(modelAndView, Boolean.TRUE);
        }
        return modelAndView;
    }

    /**
     * <p>
     * Searches for the list of Dvds with the specified title from the 
     * DVD Store and then displays the DVDs if it exist or displays a message.
     * </p>
     *
     */
    @GetMapping("displayDvdByTitle")
    public ModelAndView displayDvdByTitle(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);
        try {
            String titlePattern = new StringBuilder(Constants.PERCENT_SYMBOL).
                append(request.getParameter(Constants.LABEL_TITLE)).
                append(Constants.PERCENT_SYMBOL).toString();
            List<Dvd> dvds = dvdService.searchDvdByTitle(titlePattern);
            if (dvds.isEmpty()) {
                modelAndView.addObject(Constants.LABEL_MESSAGE, 
                    Constants.MESSAGE_SEARCH_FAIL);
                getAllDvds(modelAndView, Boolean.TRUE);
            } else {
                modelAndView.addObject(Constants.LABEL_RESET_SEARCH, 
                    Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_STATUS, Boolean.TRUE);
                modelAndView.addObject(Constants.LABEL_DVDS, dvds);
            }
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
            modelAndView.setViewName(Constants.ADMIN_HOME);
        }
        return modelAndView;
    }
    
    /**
     * <p>
     * Displays the list of available DVDs in the DVD Store.
     * </p>
     * 
     */
    @GetMapping("displayAllDvds")
    public ModelAndView displayAllDvds() {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);
        getAllDvds(modelAndView, Boolean.TRUE);
        return modelAndView;
    }

    /**
     * <p>
     * Displays the list of deleted DVDs in the DVD Store.
     * </p>
     * 
     */
    @GetMapping("displayDeletedDvds")
    public ModelAndView displayDeletedDvds() {
        ModelAndView modelAndView = new ModelAndView(Constants.DISPLAY_DVDS);
        getAllDvds(modelAndView, Boolean.FALSE);
        return modelAndView;
    }

    /**
     * <p>
     * Fetches the list of DVDs available in the DVD Store based on their
     * status.
     * </p>
     * 
     */
    private void getAllDvds(ModelAndView modelAndView, Boolean status)  {
        try {
            List<Dvd> dvds = dvdService.fetchDvds(status);	
            modelAndView.addObject(Constants.LABEL_STATUS, status);
            modelAndView.addObject(Constants.LABEL_DVDS, dvds);
        } catch (DvdStoreException e) {
            modelAndView.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
    }
}  
