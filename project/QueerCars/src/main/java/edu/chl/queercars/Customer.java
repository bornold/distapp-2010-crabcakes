package edu.chl.queercars;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Customer is an Entity class that represents a customer.
 * @author flipmo
 */
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id; //Personnummer.
    @NotNull
    private String fname;
    @NotNull
    private String email;

    /**
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email sets the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return gets the first name
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
     * creates empty customer
     */
    public Customer() {
    }

    /**
     *
     * @param id personal ID-number
     * @param fname first name
     * @param email mailadress
     */
    public Customer(String id, String fname, String email) {
        this.id = id;
        this.fname = fname;
        this.email = email;
    }

    /**
     *
     * @return the personal ID-number
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id personal ID-number
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
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