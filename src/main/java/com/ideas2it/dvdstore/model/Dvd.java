package com.ideas2it.dvdstore.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.model.Category;

/**
 * <p>
 * A DVD Model class to represent the DVD entity
 * It contains all the DVD Attributes such as ID, Title, Copies available,
 * Rating, Cost, Language, Type and Category
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class Dvd {

    private Integer id;
    private String title;
    private Integer copies;
    private Float rating;
    private Float price;
    private String language;
    private String type;
    private Date releaseDate;
    private List<Category> categories;
    private Boolean isAvailable;
    
    public Dvd() {
    }

    public Dvd(String title, Integer copies, Float rating, Float price, 
        String language, Date releaseDate, String type, 
        List<Category> categories, Boolean isAvailable) {
        this.title = title;
        this.copies = copies;
        this.rating = rating; 
        this.price = price;
        this.language = language;
        this.releaseDate = releaseDate;
        this.type = type;
        this.categories = categories;
        this.isAvailable = isAvailable;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
       this.id = id;
    }
 
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getCopies() {
        return copies;
    }
    
    public void setCopies(Integer copies) {  
        this.copies = copies;
    }

    public Float getRating() {
        return rating;
    }
    
    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getPrice() {
        return price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }
   
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Boolean setIsAvailable(Boolean isAvailable) {
        return this.isAvailable = isAvailable;
    }

    /** 
     * <p>
     * Compares whether two DVD's are similar. It Checks for similarity based 
     * on the Dvd ID.
     * </p>
     *
     * @param dvd
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar DVD is present
     *         false  When DVDs are not similar
     *
     */
    @Override
    public boolean equals(Object dvd) {
        if (null == dvd) {
            return Boolean.FALSE;
        }

        if (!(dvd instanceof Dvd)) {
            return Boolean.FALSE;
        }

        if (this == (Dvd) dvd) {
            return Boolean.TRUE;
        }

        return (this.id).equals(((Dvd) dvd).id);
    }
}
