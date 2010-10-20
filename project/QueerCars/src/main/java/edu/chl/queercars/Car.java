/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author flipmo
 */
@Entity
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id; //Registration number
    @ManyToOne
    private Model model;
    private int odometer; // in km

    /**
     * creates empty car
     */
    public Car() {
    }

    /**
     *
     * @param id registration number
     * @param model the model
     */
    public Car(String id, Model model) {
        this.id = id;
        this.model = model;
        odometer = 0;
    }
    
    /**
     *
     * @return gets odometer (as km)
     */
    public int getOdometer() {
        return odometer;
    }

    /**
     *
     * @param odometer sets odometer (km)
     */
    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    /**
     *
     * @return returns the model
     */
    public Model getModel() {
        return model;
    }

    /**
     *
     * @param model the model to set
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     *
     * @return registration number
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id registration number
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
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + model + "]";
    }
}
