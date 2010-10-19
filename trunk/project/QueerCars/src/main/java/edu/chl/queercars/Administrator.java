/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author flipmo, also vixen!!!!!!
 */
@Entity
public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id; //Username.
    @NotNull
    private String fname;

    /**
     *
     * @return gets first name
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @param fname sets the first name
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * creates an empty administrator
     */
    public Administrator() {
    }

    /**
     *
     * @param id username
     * @param fname firstname
     */
    public Administrator(String id, String fname) {
        this.id = id;
        this.fname = fname;
    }

    /**
     *
     * @return the usernameID
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id username
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + fname + "]";
    }
}