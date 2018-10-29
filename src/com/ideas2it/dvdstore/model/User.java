package com.ideas2it.dvdstore.model;

import com.ideas2it.dvdstore.common.enums.Role.USER_ROLES;

/**
 * <p>
 * A User Model class to represent the User Entity. It contains the
 * id, credentials and role of the user. 
 * </p>
 *
 * @author Arun Karthick.J
 *
 */
public class User {
    private Integer id;
    private String userName;
    private String password;
    private USER_ROLES role;

    public Integer getId() {
        return id;
    }    

    public void setId(Integer id) {
        this.id = id;
    } 

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public USER_ROLES getRole() {
        return role;
    }

    public void setRole(USER_ROLES role) {
        this.role = role;
    }

    /** 
     * <p>
     * Compares whether two users are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param user
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When the two users are similar
     *         false  When the users are not similar
     *
     */
    @Override
    public boolean equals(Object user) {
        if (null == user) {
            return Boolean.FALSE;
        }

        if (!(user instanceof User)) {
            return Boolean.FALSE;
        }

        if (this == (User) user) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((User) user).id));
    }
}
