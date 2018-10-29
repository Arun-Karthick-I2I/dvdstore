package com.ideas2it.dvdstore.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.utils.DateUtils;

/**
 * <p>
 * A Category Model class to represent the Category of the DVD entity
 * It contains all category related attributes.
 * </p>
 *
 * @author Arun Karthick.J
 */
public class Category {
    private Integer id;
    private String name;
    private List<Dvd> dvds;
    private Boolean isAvailable;
 
    public Category() {
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name, Boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public Category(Integer id, String name, 
            Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dvd> getDvds() {
        return dvds;
    }

    public void setDvds(List<Dvd> dvds) {
        this.dvds = new ArrayList<Dvd>(dvds);
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Boolean setIsAvailable(Boolean isAvailable) {
        return this.isAvailable = isAvailable;
    }

    /** 
     * <p>
     * Compares whether two Categories are similar. It Checks for  
     * similarity in Id.
     * </p>
     * @param Category
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar Category is present
     *         false  When the two categories are not similar
     *
     */
    @Override
    public boolean equals(Object Category) {
        if (null == Category) {
            return Boolean.FALSE;
        }

        if (!(Category instanceof Category)) {
            return Boolean.FALSE;
        }

        if (this == (Category) Category) {
            return Boolean.TRUE;
        }

        return (this.id).equals(((Category) Category).id);
    }
}
